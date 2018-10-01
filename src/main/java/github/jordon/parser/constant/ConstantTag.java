package github.jordon.parser.constant;

/**
 * Tag values for the constant pool entries
 * (using the same order as in the JVMS).
 */
public interface ConstantTag {

    /** The tag value of CONSTANT_Utf8_info JVMS structures. */
    short CONSTANT_UTF8_TAG = 1;

    /** The tag value of CONSTANT_Integer_info JVMS structures. */
    short CONSTANT_INTEGER_TAG = 3;

    /** The tag value of CONSTANT_Float_info JVMS structures. */
    short CONSTANT_FLOAT_TAG = 4;

    /** The tag value of CONSTANT_Long_info JVMS structures. */
    short CONSTANT_LONG_TAG = 5;

    /** The tag value of CONSTANT_Double_info JVMS structures. */
    short CONSTANT_DOUBLE_TAG = 6;

    /** The tag value of CONSTANT_Class_info JVMS structures. */
    short CONSTANT_CLASS_TAG = 7;

    /** The tag value of CONSTANT_String_info JVMS structures. */
    short CONSTANT_STRING_TAG = 8;

    /** The tag value of CONSTANT_Fieldref_info JVMS structures. */
    short CONSTANT_FIELD_REF_TAG = 9;

    /** The tag value of CONSTANT_Methodref_info JVMS structures. */
    short CONSTANT_METHOD_REF_TAG = 10;

    /** The tag value of CONSTANT_InterfaceMethodref_info JVMS structures. */
    short CONSTANT_INTERFACE_METHOD_REF_TAG = 11;

    /** The tag value of CONSTANT_NameAndType_info JVMS structures. */
    short CONSTANT_NAME_AND_TYPE_TAG = 12;

    /** The tag value of CONSTANT_MethodHandle JVMS structures. */
    short CONSTANT_METHOD_HANDLE_TAG = 15;

    /** The tag value of CONSTANT_MethodType_info JVMS structures. */
    short CONSTANT_METHOD_TYPE_TAG = 16;

    /** The tag value of CONSTANT_Dynamic_info JVMS structures. */
    short CONSTANT_DYNAMIC_TAG = 17;

    /** The tag value of CONSTANT_InvokeDynamic_info JVMS structures. */
    short CONSTANT_INVOKE_DYNAMIC_TAG = 18;

    /** The tag value of CONSTANT_Module_info JVMS structures. */
    short CONSTANT_MODULE_TAG = 19;

    /** The tag value of CONSTANT_Package_info JVMS structures. */
    short CONSTANT_PACKAGE_TAG = 20;
}
