package de.chrlembeck.aoc2019.day05;

import java.math.BigInteger;
import java.util.Objects;
import java.util.function.BiFunction;

public class ArithmeticInstruction extends AbstractInstruction {

    private final BiFunction<BigInteger, BigInteger, BigInteger> function;

    ArithmeticInstruction(final IntcodeProgram program, final State state, final BiFunction<BigInteger, BigInteger, BigInteger> function) {
        super(program, state);
        this.function = Objects.requireNonNull(function);
    }

    @Override
    public final void exec(final IntcodeProgram program, final State state) {
        final BigInteger operand3 = program.get(state.getProgCount() + 3);
        final BigInteger op1 = getAndEvaluateOperand1();
        final BigInteger op2 = getAndEvaluateOperand2();
        final BigInteger result = function.apply(op1, op2);

        switch(getThirdParameter()) {
            case PARAMETER_POSITION_MODE:
                program.set(operand3.intValueExact(), result);
                break;
            case PARAMETER_RELATIVE_MODE:
                program.set(operand3.intValueExact() + state.getRelativeBase(), result);
                break;
            default:
                throw new IllegalArgumentException("not supported parameter mode " + getThirdParameter());
        }
        state.inc(4);
    }
}