package github.jordon.parser.bean.cp;

import lombok.Getter;
import lombok.Setter;

import static github.jordon.parser.constant.ConstantTags.*;

/**
 * base constant pool item
 * linking all items by designing with Chain of Responsibility Pattern
 */
@Setter
@Getter
public class BaseCPItem {

    // tag of the cp item, common attribute of cp items
    short tag;

    // byte array of the bytecode file, target to be read
    byte[] classFileBytes;

    // size of the item, connotative index for reading bytecode file
    short itemSize;

    // next item of current processing item
    public BaseCPItem next;

    // previous item of current processing item
    public BaseCPItem previous;

    // the first item of the processing train
    public BaseCPItem head;


    // the last item of the processing train
    public BaseCPItem tail;

    // the position where the current item is linked in chain
    int order;


    public short getItemSize() {
        return itemSize;
    }

    public void handle(int itemOffset, int order) {
        if (this.next != null) {
            next.handle(itemOffset, order);
        }
    }

    public void print() {
        if (this.next != null) {
            next.print();
        }
    }

    public String getByteString(int index) {
        if (index > this.order) {
            if (this.next != null) {
                return next.getByteString(index);
            }
        }else {
            if (this.previous != null) {
                return previous.getByteString(index);
            }
        }
        return null;
    }

    /**
     * obtain a constant pool item according to its tag
     * @param tag a tag to differentiate the type of the CP item
     * @return a object of the CP item that the tag mapped to
     */
    public static BaseCPItem getConstantPoolItem(short tag) {
        switch (tag) {
            case CONSTANT_UTF8_TAG:
                return new Utf8CPItem();

            case CONSTANT_INTEGER_TAG:
                return new IntegerCPItem();

            case CONSTANT_FLOAT_TAG:
                return new FloatCPItem();

            case CONSTANT_LONG_TAG:
                return new LongItem();

            case CONSTANT_DOUBLE_TAG:
                return new DoubleCPItem();

            case CONSTANT_CLASS_TAG:
                return new ClassCPItem();

            case CONSTANT_STRING_TAG:
                return new StringCPItem();

            case CONSTANT_FIELD_REF_TAG:
                return new FiledRefCPItem();

            case CONSTANT_METHOD_REF_TAG:
                return new MethodRefItem();

            case CONSTANT_INTERFACE_METHOD_REF_TAG:
                return new InterfaceMethodRefCPItem();

            case CONSTANT_NAME_AND_TYPE_TAG:
                return new NameAndTypeItem();

            case CONSTANT_METHOD_HANDLE_TAG:
                return new MethodHandleItem();

            case CONSTANT_METHOD_TYPE_TAG:
                return new MethodTypeItem();

            case CONSTANT_DYNAMIC_TAG:
                return new DynamicCPItem();

            case CONSTANT_INVOKE_DYNAMIC_TAG:
                return new InvokeDynamicCPItem();

            case CONSTANT_MODULE_TAG:
                return new ModuleItem();

            case CONSTANT_PACKAGE_TAG:
                return new PackageCPItem();
        }
        return null;
    }
}