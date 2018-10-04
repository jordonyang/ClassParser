package github.jordon.parser.bean.access;

import java.util.List;

public class FieldAccessFlags extends AccessFlags {

    private static final int ACC_VOLATILE = 0x0040;
    private static final int ACC_TRANSIENT = 0x0080;
    private static final int ACC_ENUM = 0x4000;

    public FieldAccessFlags(int accessFlags) {
        super(accessFlags);
    }

    @Override
    String[] partition(int accessFlags) {
        List<String> flags = super.commonFormat(accessFlags);
        if ((accessFlags & ACC_VOLATILE) != 0) {
            flags.add("ACC_VOLATILE");
        }
        if ((accessFlags & ACC_TRANSIENT) != 0) {
            flags.add("ACC_TRANSIENT");
        }
        if ((accessFlags & ACC_ENUM) != 0) {
            flags.add("ACC_ENUM");
        }
        return flags.toArray(new String[0]);
    }
}
