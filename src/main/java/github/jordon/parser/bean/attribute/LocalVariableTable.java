package github.jordon.parser.bean.attribute;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalVariableTable {
    private int localVariableTableLength;
    private LocalVariable[] localVariableTable;

    private static class LocalVariable{
        private int startPC;
        private int length;
        private int nameIndex;
        private int descriptorIndex;
        private int index;
    }
}

