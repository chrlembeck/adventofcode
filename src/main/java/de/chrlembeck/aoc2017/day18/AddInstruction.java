package de.chrlembeck.aoc2017.day18;

import java.math.BigInteger;

public class AddInstruction implements Instruction {

    private Token addend;

    private String varName;

    public AddInstruction(final String varName, final String value) {
        this.varName = varName;
        this.addend = Aoc2017Day18.createToken(value);
    }

    @Override
    public void execute(final State state) {
        BigInteger currentValue = state.getValue(varName);
        currentValue = currentValue.add(addend.intValue(state));
        state.setValue(varName, currentValue);
    }
}