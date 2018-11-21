package github.jordon.parser.bean.cp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class StringCPItem extends BaseCPItem {
    private int index;

    public StringCPItem() {
        this.itemSize = 3;
    }
}
