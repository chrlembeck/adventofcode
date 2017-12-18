package de.chrlembeck.aoc2017.day18;

import java.math.BigInteger;

public class MulInstruction implements Instruction {

    private Token factor;

    private String varName;

    public MulInstruction(final String varName, final String value) {
        this.varName = varName;
        this.factor = Aoc2017Day18.createToken(value);
    }

    @Override
    public void execute(final State state) {
        BigInteger currentValue = state.getValue(varName);
        currentValue = currentValue.multiply(factor.intValue(state));
        state.setValue(varName, currentValue);
    }
}