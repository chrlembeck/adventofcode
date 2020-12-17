package de.chrlembeck.aoc2017.day18;

import java.math.BigInteger;

public class SubInstruction implements Instruction {

    private final String varName;

    private final Token subtrahend;

    public SubInstruction(final String varName, final String value) {
        this.varName = varName;
        this.subtrahend = Aoc2017Day18.createToken(value);
    }

    @Override
    public void execute(final State state) {
        BigInteger currentValue = state.getValue(varName);
        currentValue = currentValue.subtract(subtrahend.intValue(state));
        state.setValue(varName, currentValue);
    }
}