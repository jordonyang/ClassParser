package github.jordon.parser.bean;

import github.jordon.parser.bean.access.AccessFlags;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Field {

    private AccessFlags accessFlags;
    private int nameIndex;
    private int descriptorIndex;
    private int attrCount;

    /** parse result*/
    private String fieldName;
    private String descriptorName;

    public Field setAccessFlags(AccessFlags accessFlags) {
        this.accessFlags = accessFlags;
        return this;
    }

    public Field setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
        return this;
    }

    public Field setDescriptorIndex(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
        return this;
    }

    public Field setAttrCount(int attrCount) {
        this.attrCount = attrCount;
        return this;
    }

    public Field setFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    public Field setDescriptorName(String descriptorName) {
        this.descriptorName = descriptorName;
        return this;
    }
}
