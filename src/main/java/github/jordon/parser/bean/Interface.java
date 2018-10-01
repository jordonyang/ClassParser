package github.jordon.parser.bean;

import lombok.Setter;

@Setter
public class Interface {
    private int cpClassItemIndex;

    public int getCpClassItemIndex() {
        return cpClassItemIndex;
    }

    public Interface setCpClassItemIndex(int cpClassItemIndex) {
        this.cpClassItemIndex = cpClassItemIndex;
        return this;
    }
}
