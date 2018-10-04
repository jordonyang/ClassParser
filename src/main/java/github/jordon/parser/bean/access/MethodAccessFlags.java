package github.jordon.parser.bean.access;

import java.util.List;

public class MethodAccessFlags extends AccessFlags {

    private static final int ACC_SYNCHRONIZED = 0x0020;
    private static final int ACC_BRIDGE = 0x0040;
    private static final int ACC_VARARGS = 0x0080;
    private static final int ACC_NATIVE = 0x0100;
    private static final int ACC_STRICTFP = 0x0800;

    public MethodAccessFlags(int accessFlags) {
        super(accessFlags);
    }

    @Override
    String[] partition(int accessFlags) {
        List<String> flags = super.commonFormat(accessFlags);
        if ((accessFlags & ACC_SYNCHRONIZED) != 0) {
            flags.add("ACC_SYNCHRONIZED");
        }
        if ((accessFlags & ACC_BRIDGE) != 0) {
            flags.add("ACC_BRIDGE");
        }
        if ((accessFlags & ACC_VARARGS) != 0) {
            flags.add("ACC_VARARGS");
        }
        if ((accessFlags & ACC_NATIVE) != 0) {
            flags.add("ACC_NATIVE");
        }
        if ((accessFlags & ACC_STRICTFP) != 0) {
            flags.add("ACC_STRICTFP");
        }
        return flags.toArray(new String[0]);
    }
}
