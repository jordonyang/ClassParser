package github.jordon.parser.bean.cp;

import github.jordon.parser.util.ByteUnitReader;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Utf8CPItem extends BaseCPItem {
    private int length;
    private String bytesString;

    public Utf8CPItem() {
        this.itemSize = (short) (3 + length);
    }

    @Override
    public short getItemSize() {
        return  (short) (3 + length);
    }

    @Override
    public void handle(int itemOffset, int order) {
        this.order = order;
        this.length = ByteUnitReader.read2Bytes(classFileBytes, itemOffset + 1);
        this.bytesString = ByteUnitReader.byteToString(classFileBytes, itemOffset + 3, length);
//        System.out.println("Utf8CPItem " + itemOffset + " length " + length +
//                            " bytesString " + bytesString);
        super.handle(itemOffset + getItemSize(), order + 1);
    }

    @Override
    public void print() {
        String firstColumn = order + " = Utf8";
        System.out.println(String.format("  #%-20s%-10s", firstColumn, bytesString));
        super.print();
    }

    @Override
    public String getByteString(int index) {
        if (index == this.order) {
            return bytesString;
        }
        return super.getByteString(index);
    }
}
