package be.uantwerpen.fti.ei.input;

import java.util.LinkedList;

public class AInput {
    protected LinkedList<Inputs> keyInputs = new LinkedList<>();;

    public boolean inputAvailable() {
        return keyInputs.size() > 0;
    }
    public Inputs getInput() {
        return keyInputs.poll();
    }
}
