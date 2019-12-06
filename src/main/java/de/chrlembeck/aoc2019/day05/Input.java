package de.chrlembeck.aoc2019.day05;

import java.math.BigInteger;
import java.util.List;

public class Input extends Instruction {

    Input(List<BigInteger> program, State state) {
        super(program, state);
    }

    @Override
    public void exec(List<BigInteger> program, State state) {
        BigInteger operand1 = program.get(state.getProgCount() + 1);
        program.set(operand1.intValueExact(), state.getInput());
        state.inc(2);
    }
}
