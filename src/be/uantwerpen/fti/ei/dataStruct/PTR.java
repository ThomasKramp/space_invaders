package be.uantwerpen.fti.ei.dataStruct;

/**
 * Pointer class to a data value, since data types don't use pointers.<br>
 * The datatype of this pointer is decided in the declaration of the class, via a template class.
 */
public class PTR<T> {
    private T value;

    /**
     * Class constructor specifying the value that must be stored in the pointer.
     * @param   value the value this class is a pointer for
     */
    public PTR(T value) { setValue(value); }

    /**
     * Returns the value to which this pointer class is pointing to.
     * <br>The datatype of this value is decided in the declaration of the class.
     * @return  the value this class is a pointer for
     */
    public T getValue() { return value; }
    /**
     * Sets the value to which this pointer class is pointing to.
     * <br>The datatype of this value is decided in the declaration of the class.
     * @param   value the value this class is a pointer for
     */
    public void setValue(T value) { this.value = value; }
}
