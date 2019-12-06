package de.chrlembeck.aoc2019.day05;

import java.math.BigInteger;
import java.util.List;
import java.util.function.Predicate;

public class JumpInstruction extends Instruction{

    private final Predicate<BigInteger> predicate;

    JumpInstruction(List<BigInteger> program, State state, Predicate<BigInteger> predicate) {
        super(program, state);
        this.predicate = predicate;
    }

    @Override
    public final void exec(List<BigInteger> program, State state) {
        if (predicate.test(getAndEvaluateOperand1())) {
            state.setProgCount(getAndEvaluateOperand2().intValueExact());
        } else {
            state.inc(3);
        }
    }
}
