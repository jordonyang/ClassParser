package github.jordon.parser.bean.cp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class DoubleCPItem extends BaseCPItem {
    private long lowBytes;
    private long highBytes;

    public DoubleCPItem() {
        this.itemSize = 9;
    }
}
