package github.jordon.parser.handler.cp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LongItem extends BaselItem {
    private long lowBytes;
    private long highBytes;

    public LongItem() {
        this.itemSize = 9;
    }
}