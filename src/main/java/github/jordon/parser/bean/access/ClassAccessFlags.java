package github.jordon.parser.bean.access;

import java.util.List;

public class ClassAccessFlags extends AccessFlags {

    private static final int ACC_SUPER = 0x0020;
    private static final int ACC_INTERFACE = 0x0200;
    private static final int ACC_ANNOTATION = 0x2000;
    private static final int ACC_ENUM = 0x4000;

    public ClassAccessFlags(int accessFlags) {
        super(accessFlags);
    }

    @Override
    String[] partition(int accessFlags) {
        List<String> flags= super.commonFormat(accessFlags);
        if ((accessFlags & ACC_SUPER) != 0) {
            flags.add("ACC_SUPER");
        }
        if ((accessFlags & ACC_INTERFACE) != 0) {
            flags.add("ACC_INTERFACE");
        }
        if ((accessFlags & ACC_ANNOTATION) != 0) {
            flags.add("ACC_ANNOTATION");
        }
        if ((accessFlags & ACC_ENUM) != 0) {
            flags.add("ACC_ENUM");
        }
        return flags.toArray(new String[0]);
    }
}
