package github.jordon.parser.bean;

import github.jordon.parser.bean.access.AccessFlags;
import github.jordon.parser.bean.attribute.Attribute;
import github.jordon.parser.bean.cp.BaseCPItem;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class ClassFile {

    private long magic;

    private int minorVersion;

    private int majorVersion;

    private int cpCount;

    private List<BaseCPItem> cpItems;

    private AccessFlags accessFlags;

    private int thisClass;

    private int superClass;

    private int interfacesCount;

    private List<Interface> interfaces;

    private int fieldsCount;

    private List<Field> fields;

    private int methodsCount;

    private List<Method> methods;

    private int attributeCount;

    private List<Attribute> attributes;

    /** parse result */
    private String thisClassStr;
    private String superClassStr;

    public void setMagic(long magic) {
        this.magic = magic;
    }

    public ClassFile setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
        return this;
    }

    public ClassFile setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
        return this;
    }

    public ClassFile setCpCount(int cpCount) {
        this.cpCount = cpCount;
        return this;
    }

    public ClassFile setCpItems(List<BaseCPItem> cpItems) {
        this.cpItems = cpItems;
        return this;
    }

    public ClassFile setAccessFlags(AccessFlags accessFlags) {
        this.accessFlags = accessFlags;
        return this;
    }

    public ClassFile setThisClass(int thisClass) {
        this.thisClass = thisClass;
        return this;
    }

    public ClassFile setSuperClass(int superClass) {
        this.superClass = superClass;
        return this;
    }

    public ClassFile setInterfacesCount(int interfacesCount) {
        this.interfacesCount = interfacesCount;
        return this;
    }

    public ClassFile setInterfaces(List<Interface> interfaces) {
        this.interfaces = interfaces;
        return this;
    }

    public ClassFile setFieldsCount(int fieldsCount) {
        this.fieldsCount = fieldsCount;
        return this;
    }

    public ClassFile setFields(List<Field> fields) {
        this.fields = fields;
        return this;
    }

    public ClassFile setMethodsCount(int methodsCount) {
        this.methodsCount = methodsCount;
        return this;
    }

    public ClassFile setMethods(List<Method> methods) {
        this.methods = methods;
        return this;
    }

    public ClassFile setAttributeCount(int attributeCount) {
        this.attributeCount = attributeCount;
        return this;
    }

    public ClassFile setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
        return this;
    }

    public ClassFile setThisClassStr(String thisClassStr) {
        this.thisClassStr = thisClassStr.replaceAll("/", ".");
        return this;
    }

    public ClassFile setSuperClassStr(String superClassStr) {
        this.superClassStr = superClassStr.replaceAll("/", ".");
        return this;
    }
}
