package github.jordon.parser.bean.cp;

import github.jordon.parser.util.ByteUnitReader;

public class MethodRefItem extends BaselItem {

    private int cpClassItemIndex;
    private int cpNameAndTypeItemIndex;

    public MethodRefItem() {
        this.itemSize = 5;
    }

    @Override
    public void handle(int itemOffset, int order) {
        this.order = order;
        this.cpClassItemIndex = ByteUnitReader.read2Bytes(classFileBytes, itemOffset + 1);
        this.cpNameAndTypeItemIndex = ByteUnitReader.read2Bytes(classFileBytes, itemOffset + 3);
//        System.out.println("MethodRefItem " + itemOffset+ " cpClassItemIndex " + cpClassItemIndex +
//                " cpNameAndTypeItemIndex " + cpNameAndTypeItemIndex);
        super.handle(itemOffset + itemSize, order + 1);
    }

    @Override
    public void print() {
        String firstColumn = order + " = Methodref";
        String secondColumn = "#" + cpClassItemIndex + ", #" + cpNameAndTypeItemIndex;
        String thirdColumn = "// " + super.getByteString(cpClassItemIndex) + ", " + super.getByteString(cpNameAndTypeItemIndex);
        System.out.println(String.format("  #%-20s%-10s%-5s", firstColumn, secondColumn, thirdColumn));
        super.print();
    }

    @Override
    public String getByteString(int index) {
        if (index == this.order) {
            return super.getByteString(cpClassItemIndex) + ", " +
                    super.getByteString(cpNameAndTypeItemIndex);
        }
        return super.getByteString(index);
    }
}