package de.chrlembeck.aoc2019.day05;

import java.math.BigInteger;
import java.util.List;
import java.util.function.BiPredicate;

public class CompareInstruction extends Instruction {

    private final BiPredicate<BigInteger, BigInteger> predicate;

    CompareInstruction(List<BigInteger> program, State state, BiPredicate<BigInteger, BigInteger> predicate) {
        super(program, state);
        this.predicate = predicate;
    }

    @Override
    public final void exec(List<BigInteger> program, State state) {
        BigInteger operand3 = program.get(state.getProgCount() + 3);
        if (predicate.test(getAndEvaluateOperand1(), getAndEvaluateOperand2())) {
            program.set(operand3.intValueExact(), BigInteger.ONE);
        } else {
            program.set(operand3.intValueExact(), BigInteger.ZERO);
        }
        state.inc(4);
    }
}