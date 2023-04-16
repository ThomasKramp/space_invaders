package be.uantwerpen.fti.ei.dataStruct;

public class BoolPtr {
    boolean value;

    public BoolPtr() {}
    public BoolPtr(boolean value) {
        this.value = value;
    }

    public boolean isValue() {
        return value;
    }
    public void setValue(boolean value) {
        this.value = value;
    }
}
