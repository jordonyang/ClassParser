package github.jordon.parser;


import github.jordon.parser.bean.ClassFile;

/**
 * app entry
 */
public class App {
    public static void main( String[] args ) {
        ClassReader classReader = new ClassReader("E:\\projects\\cappuccino\\out\\production\\cappuccino\\decompile\\IterableClass.class");
        classReader.printHexArr();
        ClassFile result = classReader.parse();
        System.out.println(result);
    }
}
