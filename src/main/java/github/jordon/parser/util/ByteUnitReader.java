package github.jordon.parser.util;

public class ByteUnitReader {

    public static int read2Bytes(byte[] bytes, int offset, int end) {
        //bytes作为缓冲数组存储两个字节
        //class文件中字符以U-16编码
        //将缓冲数组中的两个字节解析成字符。
        int num = 0;
        for (int i = offset; i <= end; i++) {
            num <<= 8;   //num=num*2^8,左位移表示乘，右位移表示/,>>>表示无符号右移
            num |= (bytes[i] & 0xff);   // | 表示按位或运算    &表示按位与运算
        }
        return num;
    }


    public static int read2Bytes(byte[] bytes, int offset) {
        //bytes作为缓冲数组存储两个字节
        //class文件中字符以U-16编码
        //将缓冲数组中的两个字节解析成字符。
        int num = 0;
        for (int i = offset; i <= offset + 1; i++) {
            num <<= 8;   //num=num*2^8,左位移表示乘，右位移表示/,>>>表示无符号右移
            num |= (bytes[i] & 0xff);   // | 表示按位或运算    &表示按位与运算
        }
        return num;
    }


    public static long read4Bytes(byte[] bytes, int offset, int end) {
        long num = 0;
        for (int i = offset; i <= end; i++) {
            num <<= 8;
            num |= (bytes[i] & 0xff);
        }
        return num;
    }

    public static long read4Bytes(byte[] bytes, int offset) {
        long num = 0;
        for (int i = offset; i <= offset + 3; i++) {
            num <<= 8;
            num |= (bytes[i] & 0xff);
        }
        return num;
    }

    public static String byteToString(byte[] bytes, int offset, int length) {
        byte[] newBytes = new byte[length];
        System.arraycopy(bytes, offset, newBytes, 0, length);
        return new String(newBytes);
    }
}
