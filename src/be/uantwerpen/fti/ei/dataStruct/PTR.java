package be.uantwerpen.fti.ei.dataStruct;

public class PTR<T> {
    private T value;
    public PTR(T value) {
        setValue(value);
    }
    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }
}
