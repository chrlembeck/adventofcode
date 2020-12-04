package de.chrlembeck.aoc2019.day05;

import java.math.BigInteger;
import java.util.List;

public class Input extends AbstractInstruction {

    Input(final List<BigInteger> program, final State state) {
        super(program, state);
    }

    @Override
    public void exec(final List<BigInteger> program, final State state) {
        final BigInteger operand1 = program.get(state.getProgCount() + 1);
        program.set(operand1.intValueExact(), state.getInput());
        state.inc(2);
    }
}
