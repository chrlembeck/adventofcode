package de.chrlembeck.aoc2019.day05;

import java.math.BigInteger;
import java.util.function.BiPredicate;

public class CompareInstruction extends AbstractInstruction {

    private final BiPredicate<BigInteger, BigInteger> predicate;

    CompareInstruction(final IntcodeProgram program, final State state, final BiPredicate<BigInteger, BigInteger> predicate) {
        super(program, state);
        this.predicate = predicate;
    }

    @Override
    public final void exec(final IntcodeProgram program, final State state) {
        final BigInteger operand3 = program.get(state.getProgCount() + 3);
        BigInteger result = predicate.test(getAndEvaluateOperand1(), getAndEvaluateOperand2()) ?            BigInteger.ONE: BigInteger.ZERO;

        switch(getThirdParameter()) {
            case PARAMETER_POSITION_MODE:
                program.set(operand3.intValueExact(), result);
                break;
            case PARAMETER_RELATIVE_MODE:
                program.set(operand3.intValueExact() + state.getRelativeBase(), result);
                break;
            default:
                throw new IllegalArgumentException("not supported parameter mode " + getFirstParameter());
        }

        state.inc(4);
    }
}