package github.jordon.parser.bean.cp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class FloatCPItem extends BaseCPItem {
    private long bytes;

    public FloatCPItem() {
        this.itemSize = 5;
    }
}