package github.jordon.parser.bean.cp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LongItem extends BaseCPItem {
    private long lowBytes;
    private long highBytes;

    public LongItem() {
        this.itemSize = 9;
    }
}