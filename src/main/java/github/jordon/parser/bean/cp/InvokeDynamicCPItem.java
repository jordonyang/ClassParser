package github.jordon.parser.bean.cp;

public class InvokeDynamicCPItem extends BaseCPItem {
    private int bootstrapMethodAttrIndex;
    private int nameAndTypeIndex;

    public InvokeDynamicCPItem() {
        this.itemSize = 5;
    }
}
