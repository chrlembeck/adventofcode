package de.chrlembeck.aoc2017.day18;

import java.math.BigInteger;

public class RecoverInstruction implements Instruction {

    private Token value;

    public RecoverInstruction(final String value) {
        this.value = Aoc2017Day18.createToken(value);
    }

    @Override
    public void execute(final State state) {
        final BigInteger intValue = value.intValue(state);
        if (intValue.signum() != 0) {
            final BigInteger currentSound = state.getCurrentSound();
            state.recoverSound(currentSound);
        }
    }
}