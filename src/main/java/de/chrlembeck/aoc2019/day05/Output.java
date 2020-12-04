package de.chrlembeck.aoc2019.day05;

public class Output extends AbstractInstruction {

    Output(final IntcodeProgram program, final State state) {
        super(program, state);
    }

    @Override
    public void exec(final IntcodeProgram program, final State state) {
        state.output(getAndEvaluateOperand1());
        state.inc(2);
    }
}