package be.uantwerpen.fti.ei.dataStruct;

/**
 * Deze klasse is gemaakt om een integer waarde als pointer op te kunnen slagen
 * @author      Thomas Kramp
 */
public class IntPtr {
    private int value;

    public IntPtr() { setValue(0); }
    public IntPtr(int value) {
        setValue(value);
    }

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }

    public void add(int value) {
        setValue(getValue() + value);
    }
    public void mul(int value) {
        setValue(getValue() * value);
    }
    public void mod(int value) {
        setValue(getValue() % value);
    }
    /**
     * @author      Specifies author
     * @deprecated  Specifies deprecation
     * @exception   Specifies exception thrown
     * @link        Creates an in line hyperlink
     * @param       Method parameter
     * @return      Return value
     * @see         Creates a hyperlink
     * @since       Specifies version introduced
     * @throws      Same as @exception
     * @version     States current version
     */
}
