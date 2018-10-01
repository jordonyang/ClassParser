package github.jordon.parser;

import github.jordon.parser.bean.ClassStructure;
import github.jordon.parser.bean.Interface;
import github.jordon.parser.handler.cp.BaselItem;
import github.jordon.parser.constant.Constants;
import github.jordon.parser.bean.Field;
import github.jordon.parser.handler.cp.Utf8Item;
import github.jordon.parser.util.BytesReader;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ClassReader {

    private static final int _4_BYTES = 4;
    private static final int _2_BYTES = 2;

    private final byte[] classFileBytes;

    private static final int INPUT_STREAM_DATA_CHUNK_SIZE = 4096;

    private int byteCursor = 0;

    private boolean moveCursor = true;

    private int cpOffset = 0;

    public List<BaselItem> cpItems;

    public void print() {
        String[] strings = BytesReader.binaryToHexString(classFileBytes);
        for (int i = 1; i <= strings.length;i++) {
            String symbol = (i % 16 == 0 || i == strings.length) ? "\n" : ", ";
            System.out.print(strings[i - 1] + symbol);
        }
        System.out.println();
    }

    public ClassReader(String filePath) {
        InputStream is = getClassFileStream(filePath);
        classFileBytes = readStream(is);
    }

    private static byte[] readStream(final InputStream inputStream) {
        try{
            if (inputStream == null) {
                throw new IOException("Class not found");
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] data = new byte[INPUT_STREAM_DATA_CHUNK_SIZE];
            int bytesRead;
            while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
                outputStream.write(data, 0, bytesRead);
            }
            outputStream.flush();
            return outputStream.toByteArray();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new byte[0];
    }

    private InputStream getClassFileStream(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            try {
                return new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            throw new IllegalArgumentException("class file not found");
        }
        return null;
    }

    private long read4Bytes() {
        long num = 0;
        for (int i = byteCursor; i <= byteCursor + 3; i++) {
            num <<= 8;
            num |= (classFileBytes[i] & 0xff);
        }
        byteCursor += _4_BYTES;
        return num;
    }

    private int read2Bytes() {
        //bytes作为缓冲数组存储两个字节
        //class文件中字符以U-16编码
        //将缓冲数组中的两个字节解析成字符。
        int num = 0;
        for (int i = byteCursor; i <= byteCursor + 1; i++) {
            num <<= 8;   //num=num*2^8,左位移表示乘，右位移表示/,>>>表示无符号右移
            num |= (classFileBytes[i] & 0xff);   // | 表示按位或运算    &表示按位与运算
        }
        if (moveCursor){
            byteCursor += _2_BYTES;
        }
        return num;
    }

    private List<BaselItem> getConstantPoolItems(int cpCount) {
        List<BaselItem> cpItems = new LinkedList<>();
        BaselItem head = null;
        cpOffset = byteCursor;
        for (int i = 0; i < cpCount - 1; i++) {
            short tag = (short) (classFileBytes[byteCursor] & 0xff);
            BaselItem cpItem = BaselItem.getConstantPoolItem(tag);
            if (cpItem != null) {
                if (cpItem instanceof Utf8Item) {
                    // skip tag, read length
                    byteCursor ++;
                    moveCursor = false;
                    int length = read2Bytes();
                    ((Utf8Item) cpItem).setLength(length);
                    moveCursor = true;
                    // move back to the entry of the item
                    byteCursor --;
                }

                int itemSize = cpItem.getItemSize();
                cpItem.setClassFileBytes(classFileBytes);
                byteCursor += itemSize;

                if (head == null) {
                    head = cpItem;
                    head.head = head.tail = head;
                }else {
                    BaselItem currentTail = head.tail;
                    currentTail.next = cpItem;
                    head.tail = cpItem;
                    head.tail.last = currentTail;
                }
                cpItems.add(cpItem);
            }else {
                throw new IllegalArgumentException("class file internal data exception");
            }
        }

        return cpItems;
    }

    private List<Interface> getInterfaces(int interfacesCount) {
        if (interfacesCount > 0) {
            List<Interface> interfaces = new ArrayList<>(interfacesCount);
            for (int i = 0; i < interfacesCount; i++) {
                int cpClassItemIndex = read2Bytes();
                interfaces.add(new Interface().setCpClassItemIndex(cpClassItemIndex));
            }
            return interfaces;
        }
        return null;
    }

    private List<Field> getFields(int fieldsCount) {
        if (fieldsCount > 0) {
            List<Field> fields = new ArrayList<>(fieldsCount);
            for (int i = 0; i < fieldsCount; i++) {
                Field field = new Field();
                field.setAccessFlag(read2Bytes());
                field.setNameIndex(read2Bytes());
                field.setDescriptorIndex(read2Bytes());
                field.setAttrCount(read2Bytes());
                fields.add(field);
            }
            return fields;
        }
        return null;
    }

    public ClassStructure parse() {
        ClassStructure classStructure = new ClassStructure();
        long magic = read4Bytes();
        if (magic == Constants.MAGIC) {
            classStructure.setMagic(magic);
            int minorVersion = read2Bytes();

            int majorVersion = read2Bytes();
            int cpCount = read2Bytes();
            List<BaselItem> cpItems = getConstantPoolItems(cpCount);
            int accessFlags = read2Bytes();
            int thisClass = read2Bytes();
            int superClass = read2Bytes();
            int interfacesCount = read2Bytes();
            List<Interface> interfaces = getInterfaces(interfacesCount);
            int fieldsCount = read2Bytes();
            List<Field> fields = getFields(fieldsCount);
            int methodCount = read2Bytes();

            classStructure.setMajorVersion(majorVersion)
                          .setMinorVersion(minorVersion)
                          .setCpCount(cpCount)
                          .setCpItems(cpItems)
                          .setAccessFlags(accessFlags)
                          .setThisClass(thisClass)
                          .setSuperClass(superClass)
                          .setInterfacesCount(interfacesCount)
                          .setInterfaces(interfaces)
                          .setFieldsCount(fieldsCount)
                          .setFields(fields)
                          .setMethodCount(methodCount);

            BaselItem cpEntry = cpItems.get(0);
            cpEntry.handle(cpOffset, 1);
            cpEntry.print();
            this.cpItems = cpItems;

        }else {
            throw new IllegalArgumentException("not a class file");
        }
        return classStructure;
    }

    public static void main(String[] args) {
        main();
    }

    public static void main() {
        ClassReader classReader = new ClassReader("E:\\projects\\cappuccino\\out\\production\\cappuccino\\decompile\\IterableClass.class");
        classReader.print();
        ClassStructure classStructure = classReader.parse();
        System.out.println(classStructure);
    }
}
