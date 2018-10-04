package github.jordon.parser.bean;

import github.jordon.parser.bean.access.AccessFlags;
import github.jordon.parser.bean.attribute.Attribute;

import java.util.List;

public class Method {
    private AccessFlags accessFlag;
    private int nameIndex;
    private int descriptorIndex;
    private int attrCount;
    private List<Attribute> attributes;

    /** parse result */
    private String methodName;
    private String  descriptor;

    public Method setAccessFlag(AccessFlags accessFlag) {
        this.accessFlag = accessFlag;
        return this;
    }

    public Method setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
        return this;
    }

    public Method setDescriptorIndex(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
        return this;
    }

    public Method setAttrCount(int attrCount) {
        this.attrCount = attrCount;
        return this;
    }

    public Method setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
        return this;
    }

    public Method setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public Method setDescriptor(String descriptor) {
        this.descriptor = descriptor;
        return this;
    }

    @Override
    public String toString() {
        return "Method{" +
                "accessFlag=" + accessFlag +
                ", nameIndex=" + nameIndex +
                ", descriptorIndex=" + descriptorIndex +
                ", attrCount=" + attrCount +
                ", attributes=" + attributes +
                ", methodName='" + methodName + '\'' +
                ", descriptor='" + descriptor + '\'' +
                '}';
    }
}
