package github.jordon.parser.bean.cp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class DoubleItem extends BaselItem {
    private long lowBytes;
    private long highBytes;

    public DoubleItem() {
        this.itemSize = 9;
    }
}
