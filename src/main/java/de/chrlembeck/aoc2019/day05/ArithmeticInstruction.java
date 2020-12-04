package de.chrlembeck.aoc2019.day05;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;

public class ArithmeticInstruction extends AbstractInstruction {

    private final BiFunction<BigInteger, BigInteger, BigInteger> function;

    ArithmeticInstruction(final List<BigInteger> program, final State state, final BiFunction<BigInteger, BigInteger, BigInteger> function) {
        super(program, state);
        this.function = Objects.requireNonNull(function);
    }

    @Override
    public final void exec(final List<BigInteger> program, final State state) {
        final BigInteger operand3 = program.get(state.getProgCount() + 3);
        final BigInteger op1 = getAndEvaluateOperand1();
        final BigInteger op2 = getAndEvaluateOperand2();
        final BigInteger result = function.apply(op1, op2);
        program.set(operand3.intValueExact(), result);
        state.inc(4);
    }
}