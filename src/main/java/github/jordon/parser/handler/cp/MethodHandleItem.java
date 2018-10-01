package github.jordon.parser.handler.cp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MethodHandleItem extends BaselItem {
    private short refKind;
    private int refIndex;

    public MethodHandleItem() {
        this.itemSize = 4;
    }
}
