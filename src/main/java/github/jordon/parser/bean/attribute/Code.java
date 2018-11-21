package github.jordon.parser.bean.attribute;

import github.jordon.parser.constant.BytecodeInstruction;
import github.jordon.parser.util.ByteUnitReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code extends Attribute {

    private int maxStack;
    private int maxLocals;
    private long codeLength;
    private String[] code;
    private int exceptionTableLength;
    private List exceptionTable;
    private int attributesCount;
    private List<Attribute> attributes;

    @Override
    public void analyze(byte[] classFileBytes, int offset) {
        this.byteOffset = offset;
        this.maxStack = ByteUnitReader.read2Bytes(classFileBytes, offset);
        this.maxLocals = ByteUnitReader.read2Bytes(classFileBytes, offset + 2);
        this.codeLength = ByteUnitReader.read4Bytes(classFileBytes, offset + 4);
        // long code to be discussed

        this.code = new String[(int)codeLength];

        for (int i = 0; i < codeLength; i++) {
            code[i] = BytecodeInstruction.getCmd(classFileBytes[offset + 8 + i] & 0xff);
        }

        System.out.println(Arrays.toString(code));

        this.exceptionTableLength = ByteUnitReader.read2Bytes(classFileBytes,
                                    offset + 8 + (int)codeLength);
        this.attributesCount = ByteUnitReader.read2Bytes(classFileBytes,
                                offset + 10 + (int)codeLength);

        for (int i = 0; i < attributesCount; i++) {

        }

        System.out.println("maxStack: " + maxStack + " maxLocals: " + maxLocals + " codeLengthb: " + codeLength + " " +
                exceptionTableLength + " " + attributesCount);


    }

    @Override
    public int getEndPoint() {
        return byteOffset + 12 + (int) codeLength + exceptionTableLength;
    }

    public int getAttributesCount() {
        return attributesCount;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public long getCodeLength() {
        return codeLength;
    }
}
