package be.uantwerpen.fti.ei.input;

import be.uantwerpen.fti.ei.enums.InputType;

import java.util.LinkedList;

public class AInput {
    protected LinkedList<InputType> keyInputs = new LinkedList<>();;

    public boolean inputAvailable() {
        return keyInputs.size() > 0;
    }
    public InputType getInput() {
        return keyInputs.poll();
    }
}
