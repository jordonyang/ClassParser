package github.jordon.parser.bean.access;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AccessFlags {

    private static final int ACC_PUBLIC = 0x0001;
    private static final int ACC_PRIVATE = 0x0002;
    private static final int ACC_PROTECTED = 0x004;
    private static final int ACC_STATIC = 0x0008;
    private static final int ACC_FINAL = 0x0010;
    private static final int ACC_ABSTRACT = 0x0400;
    private static final int ACC_SYNTHETIC = 0x1000;
    private String[] flags;

    AccessFlags(int accessFlags) {
       this.flags = this.partition(accessFlags);
    }

    List<String> commonFormat(int accessFlags) {
        List<String> flags = new ArrayList<>();
        if ((accessFlags & ACC_PUBLIC) != 0) {
            flags.add("ACC_PUBLIC");
        }
        if ((accessFlags & ACC_PRIVATE) != 0) {
            flags.add("ACC_PRIVATE");
        }
        if ((accessFlags & ACC_PROTECTED) != 0) {
            flags.add("ACC_PROTECTED");
        }
        // not in class_access_flags, to be discussed
        if ((accessFlags & ACC_STATIC) != 0) {
            flags.add("ACC_STATIC");
        }
        if ((accessFlags & ACC_FINAL) != 0) {
            flags.add("ACC_FINAL");
        }
        // not in field_access_flags, to be discussed
        if ((accessFlags & ACC_ABSTRACT) != 0) {
            flags.add("ACC_ABSTRACT");
        }
        if ((accessFlags & ACC_SYNTHETIC) != 0) {
            flags.add("ACC_SYNTHETIC");
        }
        return flags;
    }

    abstract String[] partition(int accessFlags);

    public void showFlags() {
        int length = this.flags.length;
        System.out.print("flags: ");
        for (int i = 0; i < length; i++) {
            String symbol = (i != length - 1) ? ", " : "\n";
            System.out.print(this.flags[i] + symbol);
        }
    }

    @Override
    public String toString() {
        return "AccessFlags{" +
                "flags=" + Arrays.toString(flags) +
                '}';
    }
}
