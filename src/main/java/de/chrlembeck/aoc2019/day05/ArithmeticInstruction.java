package de.chrlembeck.aoc2019.day05;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;

public class ArithmeticInstruction extends Instruction{

    private final BiFunction<BigInteger, BigInteger, BigInteger> function;

    ArithmeticInstruction(List<BigInteger> program, State state, BiFunction<BigInteger, BigInteger, BigInteger> function) {
        super(program, state);
        this.function = Objects.requireNonNull(function);
    }

    @Override
    public final void exec(List<BigInteger> program, State state) {
        BigInteger operand3 = program.get(state.getProgCount() + 3);
        BigInteger op1 = getAndEvaluateOperand1();
        BigInteger op2 = getAndEvaluateOperand2();
        BigInteger result = function.apply(op1, op2);
        program.set(operand3.intValueExact(), result);
        state.inc(4);
    }
}