package de.chrlembeck.aoc2019.day05;

import java.math.BigInteger;
import java.util.List;

public class Output extends AbstractInstruction {

    Output(final List<BigInteger> program, final State state) {
        super(program, state);
    }

    @Override
    public void exec(final List<BigInteger> program, final State state) {
        state.output(getAndEvaluateOperand1());
        state.inc(2);
    }
}