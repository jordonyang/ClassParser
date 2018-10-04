package github.jordon.parser.bean.attribute;

public abstract class Attribute {
    int nameIndex;
    long length;

    public abstract void analyze(byte[] classFileBytes, int offset);

    public static Attribute getAttribute(String name) {
        switch (name) {
            case "Code":
                return new Code();
        }
        return null;
    }

    public Attribute setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
        return this;
    }

    public void setLength(long length) {
        this.length = length;
    }
}
