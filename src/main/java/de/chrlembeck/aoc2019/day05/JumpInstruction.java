package de.chrlembeck.aoc2019.day05;

import java.math.BigInteger;
import java.util.function.Predicate;

public class JumpInstruction extends AbstractInstruction {

    private final Predicate<BigInteger> predicate;

    JumpInstruction(final IntcodeProgram program, final State state, final Predicate<BigInteger> predicate) {
        super(program, state);
        this.predicate = predicate;
    }

    @Override
    public final void exec(final IntcodeProgram program, final State state) {
        if (predicate.test(getAndEvaluateOperand1())) {
            state.setProgCount(getAndEvaluateOperand2().intValueExact());
        } else {
            state.inc(3);
        }
    }
}