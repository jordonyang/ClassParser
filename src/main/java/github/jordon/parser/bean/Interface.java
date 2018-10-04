package github.jordon.parser.bean;

import lombok.Setter;

public class Interface {
    private int cpClassItemIndex;

    /** parse result */
    private String interfaceName;

    public int getCpClassItemIndex() {
        return cpClassItemIndex;
    }

    public Interface setCpClassItemIndex(int cpClassItemIndex) {
        this.cpClassItemIndex = cpClassItemIndex;
        return this;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName.replaceAll("/", ".");
    }

    @Override
    public String toString() {
        return "Interface{" +
                "cpClassItemIndex=" + cpClassItemIndex +
                ", interfaceName='" + interfaceName + '\'' +
                '}';
    }
}
