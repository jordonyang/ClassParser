package github.jordon.parser.bean.cp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class IntegerCPItem extends BaseCPItem {
    private long bytes;

    public IntegerCPItem() {
        this.itemSize = 5;
    }
}