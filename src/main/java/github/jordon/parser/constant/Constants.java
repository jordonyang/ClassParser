package github.jordon.parser.constant;

/**
 * Defines additional JVM opcodes, access flags and constants which are not part of the ASM public
 * API.
 */
public final class Constants {

    // Long.parseLong("CAFEBABE", 16) 889275714
    public static final long MAGIC = 3405691582L;

    private Constants() {}

    // The ClassFile attribute names, in the order they are defined in
    // https://docs.oracle.com/javase/specs/jvms/se11/html/jvms-4.html#jvms-4.7-300.

    static final String CONSTANT_VALUE = "ConstantValue";
    static final String CODE = "Code";
    static final String STACK_MAP_TABLE = "StackMapTable";
    static final String EXCEPTIONS = "Exceptions";
    static final String INNER_CLASSES = "InnerClasses";
    static final String ENCLOSING_METHOD = "EnclosingMethod";
    static final String SYNTHETIC = "Synthetic";
    static final String SIGNATURE = "Signature";
    static final String SOURCE_FILE = "SourceFile";
    static final String SOURCE_DEBUG_EXTENSION = "SourceDebugExtension";
    static final String LINE_NUMBER_TABLE = "LineNumberTable";
    static final String LOCAL_VARIABLE_TABLE = "LocalVariableTable";
    static final String LOCAL_VARIABLE_TYPE_TABLE = "LocalVariableTypeTable";
    static final String DEPRECATED = "Deprecated";
    static final String RUNTIME_VISIBLE_ANNOTATIONS = "RuntimeVisibleAnnotations";
    static final String RUNTIME_INVISIBLE_ANNOTATIONS = "RuntimeInvisibleAnnotations";
    static final String RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS = "RuntimeVisibleParameterAnnotations";
    static final String RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS =
            "RuntimeInvisibleParameterAnnotations";
    static final String RUNTIME_VISIBLE_TYPE_ANNOTATIONS = "RuntimeVisibleTypeAnnotations";
    static final String RUNTIME_INVISIBLE_TYPE_ANNOTATIONS = "RuntimeInvisibleTypeAnnotations";
    static final String ANNOTATION_DEFAULT = "AnnotationDefault";
    static final String BOOTSTRAP_METHODS = "BootstrapMethods";
    static final String METHOD_PARAMETERS = "MethodParameters";
    static final String MODULE = "Module";
    static final String MODULE_PACKAGES = "ModulePackages";
    static final String MODULE_MAIN_CLASS = "ModuleMainClass";
    static final String NEST_HOST = "NestHost";
    static final String NEST_MEMBERS = "NestMembers";

    // ASM specific access flags.
    // WARNING: the 16 least significant bits must NOT be used, to avoid conflicts with standard
    // access flags, and also to make sure that these flags are automatically filtered out when
    // written in class files (because access flags are stored using 16 bits only).

    static final int ACC_CONSTRUCTOR = 0x40000; // method access flag.

    // ASM specific stack map frame types, used in {@link ClassVisitor#visitFrame}.

    static final int F_INSERT = 256;

    // The JVM opcode values which are not part of the ASM public API.
    // See https://docs.oracle.com/javase/specs/jvms/se9/html/jvms-6.html.

    static final int LDC_W = 19;
    static final int LDC2_W = 20;
    static final int ILOAD_0 = 26;
    static final int ILOAD_1 = 27;
    static final int ILOAD_2 = 28;
    static final int ILOAD_3 = 29;
    static final int LLOAD_0 = 30;
    static final int LLOAD_1 = 31;
    static final int LLOAD_2 = 32;
    static final int LLOAD_3 = 33;
    static final int FLOAD_0 = 34;
    static final int FLOAD_1 = 35;
    static final int FLOAD_2 = 36;
    static final int FLOAD_3 = 37;
    static final int DLOAD_0 = 38;
    static final int DLOAD_1 = 39;
    static final int DLOAD_2 = 40;
    static final int DLOAD_3 = 41;
    static final int ALOAD_0 = 42;
    static final int ALOAD_1 = 43;
    static final int ALOAD_2 = 44;
    static final int ALOAD_3 = 45;
    static final int ISTORE_0 = 59;
    static final int ISTORE_1 = 60;
    static final int ISTORE_2 = 61;
    static final int ISTORE_3 = 62;
    static final int LSTORE_0 = 63;
    static final int LSTORE_1 = 64;
    static final int LSTORE_2 = 65;
    static final int LSTORE_3 = 66;
    static final int FSTORE_0 = 67;
    static final int FSTORE_1 = 68;
    static final int FSTORE_2 = 69;
    static final int FSTORE_3 = 70;
    static final int DSTORE_0 = 71;
    static final int DSTORE_1 = 72;
    static final int DSTORE_2 = 73;
    static final int DSTORE_3 = 74;
    static final int ASTORE_0 = 75;
    static final int ASTORE_1 = 76;
    static final int ASTORE_2 = 77;
    static final int ASTORE_3 = 78;
    static final int WIDE = 196;
    static final int GOTO_W = 200;
    static final int JSR_W = 201;
}
