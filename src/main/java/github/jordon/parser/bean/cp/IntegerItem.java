package github.jordon.parser.bean.cp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class IntegerItem extends BaselItem {
    private long bytes;

    public IntegerItem() {
        this.itemSize = 5;
    }
}