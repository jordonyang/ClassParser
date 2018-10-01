package github.jordon.parser.util;

import java.util.Arrays;

public class BytesReader {
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
