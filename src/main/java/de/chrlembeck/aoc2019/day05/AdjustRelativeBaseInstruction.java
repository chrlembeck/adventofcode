package de.chrlembeck.aoc2019.day05;

import java.math.BigInteger;

public class AdjustRelativeBaseInstruction extends AbstractInstruction {

    public AdjustRelativeBaseInstruction(final IntcodeProgram program, final State state) {
        super(program, state);
    }

    @Override
    public void exec(final IntcodeProgram program, final State state) {
        final BigInteger operand = getAndEvaluateOperand1();
        state.incRelativeBase(operand.intValueExact());
        state.inc(2);
    }
}