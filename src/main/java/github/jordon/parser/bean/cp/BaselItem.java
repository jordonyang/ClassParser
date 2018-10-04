package github.jordon.parser.bean.cp;

import lombok.Getter;
import lombok.Setter;

import static github.jordon.parser.constant.ConstantTags.*;

@Setter
@Getter
public class BaselItem {

    short tag;
    short itemSize;
    byte[] classFileBytes;
    public BaselItem next;
    public BaselItem last;
    public BaselItem head;
    public BaselItem tail;
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
            if (this.last != null) {
                return last.getByteString(index);
            }
        }
        return null;
    }

    public static BaselItem getConstantPoolItem(short tag) {
        switch (tag) {
            case CONSTANT_UTF8_TAG:
                return new Utf8Item();

            case CONSTANT_INTEGER_TAG:
                return new IntegerItem();

            case CONSTANT_FLOAT_TAG:
                return new FloatItem();

            case CONSTANT_LONG_TAG:
                return new LongItem();

            case CONSTANT_DOUBLE_TAG:
                return new DoubleItem();

            case CONSTANT_CLASS_TAG:
                return new ClassItem();

            case CONSTANT_STRING_TAG:
                return new StringItem();

            case CONSTANT_FIELD_REF_TAG:
                return new FiledRefItem();

            case CONSTANT_METHOD_REF_TAG:
                return new MethodRefItem();

            case CONSTANT_INTERFACE_METHOD_REF_TAG:
                return new InterfaceMethodRefItem();

            case CONSTANT_NAME_AND_TYPE_TAG:
                return new NameAndTypeItem();

            case CONSTANT_METHOD_HANDLE_TAG:
                return new MethodHandleItem();

            case CONSTANT_METHOD_TYPE_TAG:
                return new MethodTypeItem();

            case CONSTANT_DYNAMIC_TAG:
                return new DynamicItem();

            case CONSTANT_INVOKE_DYNAMIC_TAG:
                return new InvokeDynamicItem();

            case CONSTANT_MODULE_TAG:
                return new ModuleItem();

            case CONSTANT_PACKAGE_TAG:
                return new PackageItem();
        }
        return null;
    }
}