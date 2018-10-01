package github.jordon.parser.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalVariableTable {
    private int start;
    private int length;
    private int nameIndex;
    private int descriptorIndex;
    private int index;
}
