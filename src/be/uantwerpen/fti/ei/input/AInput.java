package be.uantwerpen.fti.ei.input;

import be.uantwerpen.fti.ei.enums.InputType;

import java.util.LinkedList;

/**
 * Base input class that must be inherited by other input classes<br>
 * A class to get and store input data
 */
public abstract class AInput {

    /**
     * List of all inputs
     * @see     InputType
     */
    protected final LinkedList<InputType> keyInputs = new LinkedList<>();

    /**
     * Returns boolean based on if there are inputs or not.
     * @return  a boolean that shows the emptiness of the input list
     */
    public boolean inputAvailable() { return keyInputs.size() > 0; }
    /**
     * Returns an input.
     * @return  an InputType
     * @see     InputType
     */
    public InputType getInput() { return keyInputs.poll(); }
}
