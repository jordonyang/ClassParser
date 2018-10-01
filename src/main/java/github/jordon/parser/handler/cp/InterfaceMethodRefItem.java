package github.jordon.parser.handler.cp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class InterfaceMethodRefItem extends BaselItem {
    private int cpClassItemIndex;
    private int cpNameAndTypeItemIndex;

    public InterfaceMethodRefItem() {
        this.itemSize = 5;
    }
}
