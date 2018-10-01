package github.jordon.parser.bean;

import github.jordon.parser.handler.cp.BaselItem;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class ClassStructure {

    private long magic;

    private int minorVersion;

    private int majorVersion;

    private int cpCount;

    private List<BaselItem> cpItems;

    private int accessFlags;

    private int thisClass;

    private int superClass;

    private int interfacesCount;

    private List<Interface> interfaces;

    private int fieldsCount;

    private List<Field> fields;

    private int methodCount;

    public void setMagic(long magic) {
        this.magic = magic;
    }


    public ClassStructure setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
        return this;
    }

    public ClassStructure setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
        return this;
    }

    public ClassStructure setCpCount(int cpCount) {
        this.cpCount = cpCount;
        return this;
    }

    public ClassStructure setCpItems(List<BaselItem> cpItems) {
        this.cpItems = cpItems;
        return this;
    }

    public ClassStructure setAccessFlags(int accessFlags) {
        this.accessFlags = accessFlags;
        return this;
    }

    public ClassStructure setThisClass(int thisClass) {
        this.thisClass = thisClass;
        return this;
    }

    public ClassStructure setSuperClass(int superClass) {
        this.superClass = superClass;
        return this;
    }

    public ClassStructure setInterfacesCount(int interfacesCount) {
        this.interfacesCount = interfacesCount;
        return this;
    }

    public ClassStructure setInterfaces(List<Interface> interfaces) {
        this.interfaces = interfaces;
        return this;
    }

    public ClassStructure setFieldsCount(int fieldsCount) {
        this.fieldsCount = fieldsCount;
        return this;
    }

    public ClassStructure setFields(List<Field> fields) {
        this.fields = fields;
        return this;
    }

    public ClassStructure setMethodCount(int methodCount) {
        this.methodCount = methodCount;
        return this;
    }
}
