package github.jordon.parser.handler.cp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class StringItem extends BaselItem {
    private int index;

    public StringItem() {
        this.itemSize = 3;
    }
}
