package github.jordon.parser.bean.cp;

import github.jordon.parser.util.ByteUnitReader;

public class NameAndTypeItem extends BaseCPItem {
    private int cpItemIndex;
    private int cpDescriptorIndex;

    public NameAndTypeItem() {
        this.itemSize = 5;
    }

    @Override
    public void handle(int itemOffset, int order) {
        this.order = order;
        this.cpItemIndex = ByteUnitReader.read2Bytes(classFileBytes, itemOffset + 1);
        this.cpDescriptorIndex = ByteUnitReader.read2Bytes(classFileBytes, itemOffset + 3);
//        System.out.println("NameAndTypeItem " + itemOffset + " cpItemIndex " + cpItemIndex
//                            + " cpDescriptorIndex " + cpDescriptorIndex);
        super.handle(itemOffset + itemSize, order + 1);
    }

    @Override
    public void print() {
        String firstColumn = order + " = NameAndType";
        String secondColumn = "#" + cpItemIndex + ", #" + cpDescriptorIndex;
        String thirdColumn = "// " + super.getByteString(cpItemIndex) + ":" + super.getByteString(cpDescriptorIndex);
        System.out.println(String.format("  #%-20s%-10s%-5s", firstColumn, secondColumn, thirdColumn));
        super.print();
    }

    @Override
    public String getByteString(int index) {
        if (index == this.order) {
            return super.getByteString(cpItemIndex) + ":" +
                    super.getByteString(cpDescriptorIndex);
        }
        return super.getByteString(index);
    }
}
