package github.jordon.parser.util;

public class ByteUnitReader {

    public static int readBytes(byte[] bytes, int offset, int end) {
        int value = 0;
        for (int i = offset; i <= end; i++) {
            value <<= 8;
            value |= (bytes[i] & 0xff);
        }
        return value;
    }


    public static int read2Bytes(byte[] bytes, int offset) {
        int value = 0;
        for (int i = offset; i <= offset + 1; i++) {
            value <<= 8;
            value |= (bytes[i] & 0xff);
        }
        return value;
    }


    public static long read4Bytes(byte[] bytes, int offset, int end) {
        long value = 0;
        for (int i = offset; i <= end; i++) {
            value <<= 8;
            value |= (bytes[i] & 0xff);
        }
        return value;
    }

    public static long read4Bytes(byte[] bytes, int offset) {
        long value = 0;
        for (int i = offset; i <= offset + 3; i++) {
            value <<= 8;
            value |= (bytes[i] & 0xff);
        }
        return value;
    }

    public static String byteToString(byte[] bytes, int offset, int length) {
        byte[] newBytes = new byte[length];
        System.arraycopy(bytes, offset, newBytes, 0, length);
        return new String(newBytes);
    }

    public static String[] binaryToHexString(byte[] bytes){
        String hexStr =  "0123456789ABCDEF";
        String hex;
        String[] hexStrings = new String[bytes.length];
        for (int i=0; i < bytes.length; i++) {
            // 字节高4位
            hex = String.valueOf(hexStr.charAt((bytes[i] & 0xF0) >> 4));
            // 字节低4位
            hex += String.valueOf(hexStr.charAt(bytes[i] & 0x0F));
            hexStrings[i] = hex;
        }
        return hexStrings;
    }
}
