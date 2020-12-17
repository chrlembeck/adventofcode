package de.chrlembeck.aoc2017.day18;

import java.math.BigInteger;

public class ReceiveInstruction implements Instruction {

    private final String varName;

    public ReceiveInstruction(final String value) {
        varName = value;
    }

    @Override
    public void execute(final State state) {
        final BigInteger value = state.receiveValue();
        if (value == null) {
            state.setRunning(false);
        } else {
            state.setValue(varName, value);
        }
    }
}