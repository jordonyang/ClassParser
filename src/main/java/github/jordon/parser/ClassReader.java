package github.jordon.parser;

import github.jordon.parser.bean.*;
import github.jordon.parser.bean.access.AccessFlags;
import github.jordon.parser.bean.access.ClassAccessFlags;
import github.jordon.parser.bean.access.FieldAccessFlags;
import github.jordon.parser.bean.access.MethodAccessFlags;
import github.jordon.parser.bean.attribute.Attribute;
import github.jordon.parser.bean.attribute.Code;
import github.jordon.parser.bean.cp.BaseCPItem;
import github.jordon.parser.bean.cp.Utf8CPItem;
import github.jordon.parser.constant.Constants;
import github.jordon.parser.util.ByteUnitReader;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ClassReader {

    private static final int _4_BYTES = 4;

    private static final int _2_BYTES = 2;

    private static final int INPUT_STREAM_DATA_CHUNK_SIZE = 4096;

    private final byte[] classFileBytes;

    private int byteCursor;

    private boolean moveCursor = true;

    private int cpOffset;

    private List<BaseCPItem> cpItems;


    public ClassFile parse() {
        ClassFile classFile = new ClassFile();
        long magic = read4Bytes();
        if (magic == Constants.MAGIC) {
            classFile.setMagic(magic);
            int minorVersion = read2Bytes();
            int majorVersion = read2Bytes();

            int cpCount = read2Bytes();
            List<BaseCPItem> cpItems = getConstantPoolItems(cpCount);
            BaseCPItem cpEntry = cpItems.get(0);
            cpEntry.handle(cpOffset, 1);
//            cpEntry.print();
            this.cpItems = cpItems;

            AccessFlags accessFlags = new ClassAccessFlags(read2Bytes());
            int thisClass = read2Bytes();
            int superClass = read2Bytes();
            int interfacesCount = read2Bytes();
            List<Interface> interfaces = getInterfaces(interfacesCount);
            int fieldsCount = read2Bytes();
            List<Field> fields = getFields(fieldsCount);
            int methodsCount = read2Bytes();
            List<Method> methods = getMethods(methodsCount);

            classFile.setMajorVersion(majorVersion)
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
                    .setMethodsCount(methodsCount)
                    .setMethods(methods)
                    .setThisClassStr(cpItems.get(thisClass - 1)
                            .getByteString(thisClass))
                    .setSuperClassStr(cpItems.get(superClass - 1)
                            .getByteString(superClass));
        }else {
            throw new IllegalArgumentException("not a class file");
        }
        return classFile;
    }

    private List<Field> getFields(int fieldsCount) {
        if (fieldsCount > 0) {
            List<Field> fields = new ArrayList<>(fieldsCount);
            for (int i = 0; i < fieldsCount; i++) {
                Field field = new Field();
                AccessFlags accessFlags = new FieldAccessFlags(read2Bytes());
                int nameIndex = read2Bytes();
                int descriptorIndex = read2Bytes();

                field.setAccessFlags(accessFlags)
                        .setNameIndex(nameIndex)
                        .setDescriptorIndex(descriptorIndex)
                        .setAttrCount(read2Bytes())
                        .setFieldName(cpItems.get(nameIndex - 1)
                                .getByteString(nameIndex))
                        .setDescriptorName(cpItems.get(descriptorIndex - 1)
                                .getByteString(descriptorIndex));
                fields.add(field);
            }
            return fields;
        }
        return null;
    }

    private List<Method> getMethods(int methodsCount) {
        if (methodsCount > 0) {
            List<Method> methods = new ArrayList<>(methodsCount);
            for (int i = 0; i < methodsCount; i++) {
                Method method = new Method();
                AccessFlags accessFlags = new MethodAccessFlags(read2Bytes());
                int nameIndex = read2Bytes();
                int descriptorIndex = read2Bytes();
                int attrCount = read2Bytes();
                System.out.println(attrCount);
                List<Attribute> attributes = getAttributes(attrCount, byteCursor);

                method.setAccessFlag(accessFlags)
                      .setNameIndex(nameIndex)
                      .setDescriptorIndex(descriptorIndex)
                      .setAttrCount(attrCount)
                      .setMethodName(cpItems.get(nameIndex - 1)
                                .getByteString(nameIndex))
                      .setDescriptor(cpItems.get(descriptorIndex - 1)
                                .getByteString(descriptorIndex));

                System.out.println(method);
                methods.add(method);
            }
            return methods;
        }
        return null;
    }

    private List<Attribute> getAttributes(int attributesCount, int offset) {
        if (attributesCount > 0) {
            List<Attribute> attributes = new ArrayList<>(attributesCount);
            for (int i = 0; i < attributesCount; i++) {
                int nameIndex = ByteUnitReader.read2Bytes(classFileBytes, offset);
                long length = ByteUnitReader.read4Bytes(classFileBytes, offset + 2);
                offset += 6;
                String attrName = cpItems.get(nameIndex - 1).getByteString(nameIndex);
                System.out.println("offset: " + offset + " nameIndex: " + nameIndex + " length: " + length + " attrName: " + attrName);
                Attribute attribute = Attribute.getAttribute(attrName);

                // specially process Code attribute
                if (attribute != null) {
                    attribute.setNameIndex(nameIndex).setLength(length);
                    System.out.println(byteCursor);
                    attribute.analyze(classFileBytes, offset);

                    if (attribute instanceof Code) {
                        ((Code) attribute).setAttributes(getAttributes(((Code) attribute).getAttributesCount(),
                                attribute.getEndPoint()));
                    }
                    attributes.add(attribute);
                }
            }
            return attributes;
        }
        return null;
    }


    private List<Attribute> getAttributes1(int attributesCount) {
        if (attributesCount > 0) {
            List<Attribute> attributes = new ArrayList<>(attributesCount);
            for (int i = 0; i < attributesCount; i++) {
                int nameIndex = read2Bytes();
                long length = read4Bytes();

                String attrName = cpItems.get(nameIndex - 1).getByteString(nameIndex);
                System.out.println("byteCursor: " + byteCursor + " nameIndex: " + nameIndex + " length: " + length + " attrName: " + attrName);
                Attribute attribute = Attribute.getAttribute(attrName);

                // specially process Code attribute
                if (attribute != null) {
                    attribute.setNameIndex(nameIndex)
                             .setLength(length);
                    System.out.println(byteCursor);
                    attribute.analyze(classFileBytes, byteCursor);

//                    if (attribute instanceof Code) {
//                        ((Code) attribute).setAttributes(getAttributes(((Code) attribute).getAttributesCount(),
//                                (int) (attrOffset + 8 + ((Code) attribute).getCodeLength())));
//                    }
                    attributes.add(attribute);
                }
            }
            return attributes;
        }
        return null;
    }

    private List<BaseCPItem> getConstantPoolItems(int cpCount) {
        List<BaseCPItem> cpItems = new LinkedList<>();
        BaseCPItem head = null;
        cpOffset = byteCursor;
        for (int i = 0; i < cpCount - 1; i++) {
            short tag = (short) (classFileBytes[byteCursor] & 0xff);
            BaseCPItem cpItem = BaseCPItem.getConstantPoolItem(tag);
            if (cpItem != null) {
                if (cpItem instanceof Utf8CPItem) {
                    // skip tag, read length
                    byteCursor ++;
                    moveCursor = false;
                    int length = read2Bytes();
                    ((Utf8CPItem) cpItem).setLength(length);
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
                    BaseCPItem currentTail = head.tail;
                    currentTail.next = cpItem;
                    head.tail = cpItem;
                    head.tail.previous = currentTail;
                }
                cpItems.add(cpItem);
            }else {
                throw new IllegalArgumentException("class file internal data exception");
            }
        }

        return cpItems;
    }

    /**
     * read interfaces of the class according to its count
     * @param interfacesCount count of interfaces
     * @return list of interfaces
     */
    private List<Interface> getInterfaces(int interfacesCount) {
        if (interfacesCount > 0) {
            List<Interface> interfaces = new ArrayList<>(interfacesCount);
            for (int i = 0; i < interfacesCount; i++) {
                int cpClassItemIndex = read2Bytes();
                Interface inter = new Interface();
                inter.setCpClassItemIndex(cpClassItemIndex)
                     .setInterfaceName(cpItems.get(cpClassItemIndex - 1)
                         .getByteString(cpClassItemIndex));
                interfaces.add(inter);
            }
            return interfaces;
        }
        return null;
    }

    /**
     * format binary array of a class file into
     * hexadecimal string and print it
     */
    public void printHexArr() {
        String[] strings = ByteUnitReader.binaryToHexString(classFileBytes);
        for (int i = 1; i <= strings.length;i++) {
            String symbol = (i % 16 == 0 || i == strings.length) ? "\n" : ", ";
            System.out.print(strings[i - 1] + symbol);
        }
    }

    public ClassReader(String filePath) {
        classFileBytes = readStream(getClassFileStream(filePath));
    }

    private long read4Bytes() {
        long value = 0;
        for (int i = byteCursor; i <= byteCursor + 3; i++) {
            value <<= 8;
            value |= (classFileBytes[i] & 0xff);
        }
        byteCursor += _4_BYTES;
        return value;
    }

    private int read2Bytes() {
        int value = 0;
        for (int i = byteCursor; i <= byteCursor + 1; i++) {
            value <<= 8;
            value |= (classFileBytes[i] & 0xff);
        }
        if (moveCursor){
            byteCursor += _2_BYTES;
        }
        return value;
    }

    /**
     * transfer input stream of a bytecode file into byte array
     * @param inputStream input stream of a bytecode file
     * @return byte array of the bytecode file
     */
    private byte[] readStream(final InputStream inputStream) {
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


    /**
     * get input stream of a bytecode file
     * @param filePath path of the bytecode file
     * @return  input stream of the bytecode file
     */
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
}
