package be.uantwerpen.fti.ei.dataStruct;

public class BoolPtr {
    boolean value;

    public BoolPtr() { setValue(false); }
    public BoolPtr(boolean value) {
        setValue(value);
    }

    public boolean isValue() {
        return value;
    }
    public void setValue(boolean value) {
        this.value = value;
    }
}
