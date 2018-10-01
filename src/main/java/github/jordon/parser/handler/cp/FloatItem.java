package github.jordon.parser.handler.cp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class FloatItem extends BaselItem {
    private long bytes;

    public FloatItem() {
        this.itemSize = 5;
    }
}