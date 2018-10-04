package github.jordon.parser.bean.attribute;

import github.jordon.parser.util.ByteUnitReader;

import java.util.List;

public class Code extends Attribute{

    private int maxStack;
    private int maxLocals;
    private long codeLength;
    private String code;
    private int exceptionTableLength;
    private List exceptionTable;
    private int attributesCount;
    private List attributes;

    @Override
    public void analyze(byte[] classFileBytes, int offset) {
        this.maxStack = ByteUnitReader.read2Bytes(classFileBytes, offset);
        this.maxLocals = ByteUnitReader.read2Bytes(classFileBytes, offset + 2);
        this.codeLength = ByteUnitReader.read4Bytes(classFileBytes, offset + 4);
        // long code to be discussed
        this.code = ByteUnitReader.byteToString(classFileBytes, offset + 8, (int)codeLength);

        this.exceptionTableLength = ByteUnitReader.read2Bytes(classFileBytes,
                        offset + 8 + (int)codeLength);
        this.attributesCount = ByteUnitReader.read2Bytes(classFileBytes,
                        offset + 10 + (int)codeLength);

        System.out.println(maxStack + " " + maxLocals + " " + codeLength + " " + code + " " +
                exceptionTableLength + " " + attributesCount);
    }
}
