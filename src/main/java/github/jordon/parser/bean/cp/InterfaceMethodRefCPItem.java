package github.jordon.parser.bean.cp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class InterfaceMethodRefCPItem extends BaseCPItem {
    private int cpClassItemIndex;
    private int cpNameAndTypeItemIndex;

    public InterfaceMethodRefCPItem() {
        this.itemSize = 5;
    }
}
