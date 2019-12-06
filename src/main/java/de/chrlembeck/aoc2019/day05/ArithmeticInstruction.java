package de.chrlembeck.aoc2019.day05;

import java.math.BigInteger;
import java.util.List;
import java.util.function.BiFunction;

public class ArithmeticInstruction extends Instruction{

    private final BiFunction<BigInteger, BigInteger, BigInteger> function;

    ArithmeticInstruction(List<BigInteger> program, State state, BiFunction<BigInteger, BigInteger, BigInteger> function) {
        super(program, state);
        this.function = function;
    }

    @Override
    public final void exec(List<BigInteger> program, State state) {
        BigInteger operand3 = program.get(state.getProgCount()+3);
        BigInteger result = function.apply(getAndEvaluateOperand1(), getAndEvaluateOperand2());
        program.set(operand3.intValueExact(), result);
        state.inc(4);
    }
}