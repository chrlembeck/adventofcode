package de.chrlembeck.aoc2017.day18;

import java.math.BigInteger;

public class ModInstruction implements Instruction {

    private Token modulus;

    private String varName;

    public ModInstruction(final String varName, final String value) {
        this.varName = varName;
        this.modulus = Aoc2017Day18.createToken(value);
    }

    @Override
    public void execute(final State state) {
        BigInteger currentValue = state.getValue(varName);
        currentValue = currentValue.mod(modulus.intValue(state));
        state.setValue(varName, currentValue);
    }
}