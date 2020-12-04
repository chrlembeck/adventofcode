package de.chrlembeck.aoc2019.day05;

import java.math.BigInteger;

public class Input extends AbstractInstruction {

    Input(final IntcodeProgram program, final State state) {
        super(program, state);
    }

    @Override
    public void exec(final IntcodeProgram program, final State state) {
        final BigInteger operand1 = program.get(state.getProgCount() + 1);
        switch(getFirstParameter()) {
            case PARAMETER_POSITION_MODE:
                program.set(operand1.intValueExact(), state.getInput());
                break;
            case PARAMETER_RELATIVE_MODE:
                program.set(operand1.intValueExact() + state.getRelativeBase(), state.getInput());
                break;
            default:
                throw new IllegalArgumentException("not supported parameter mode " + getFirstParameter());
        }
        state.inc(2);
    }
}