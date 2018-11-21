package github.jordon.parser.bean.attribute;

public abstract class Attribute {

    // common attribute in all Attributes
    int nameIndex;

    // common attribute in all Attributes
    long length;

    int byteOffset;

    int endPoint;

    public abstract void analyze(byte[] classFileBytes, int offset);

    public static Attribute getAttribute(String name) {
        switch (name) {
            case "Code":
                return new Code();
            case "LineNumberTable":
                return new LineNumberTable();
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

    public int getByteOffset() {
        return byteOffset;
    }

    public int getEndPoint() {
        return endPoint;
    }
}
