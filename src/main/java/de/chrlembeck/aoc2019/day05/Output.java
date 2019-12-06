package de.chrlembeck.aoc2019.day05;

import java.math.BigInteger;
import java.util.List;

public class Output extends Instruction {

    Output(List<BigInteger> program, State state) {
        super(program, state);
    }

    @Override
    public void exec(List<BigInteger> program, State state) {
        state.output(getAndEvaluateOperand1());
        state.inc(2);
    }
}