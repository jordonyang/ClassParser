package github.jordon.parser.bean.cp;

import github.jordon.parser.util.ByteUnitReader;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ClassItem extends BaselItem {
    private int nameIndex;

    public ClassItem() {
        this.itemSize = 3;
    }

    @Override
    public void handle(int itemOffset, int order) {
        this.order = order;
        this.nameIndex = ByteUnitReader.read2Bytes(classFileBytes, itemOffset + 1);
//        System.out.println("ClassItem " + itemOffset + " nameIndex " + nameIndex);
        super.handle(itemOffset + itemSize, order + 1);
    }

    @Override
    public void print() {
        String firstColumn = order + " = Class";
        String secondColumn = "#" + nameIndex;
        String thirdColumn = "// " + super.getByteString(nameIndex);
        System.out.println(String.format("  #%-20s%-10s%-5s", firstColumn, secondColumn, thirdColumn));
        super.print();
    }



    @Override
    public String getByteString(int index) {
        if (index == this.order) {
            return super.getByteString(nameIndex);
        }
        return super.getByteString(index);
    }
}