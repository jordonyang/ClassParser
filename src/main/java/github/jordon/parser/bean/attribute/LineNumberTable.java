package github.jordon.parser.bean.attribute;

public class LineNumberTable extends Attribute{

    private int tableLength;
    private String[] lineNumberTable;

    @Override
    public void analyze(byte[] classFileBytes, int offset) {
        

    }
}
