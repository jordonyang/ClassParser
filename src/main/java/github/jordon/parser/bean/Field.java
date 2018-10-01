package github.jordon.parser.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Field {

    private int accessFlag;
    private int nameIndex;
    private int descriptorIndex;
    private int attrCount;
}
