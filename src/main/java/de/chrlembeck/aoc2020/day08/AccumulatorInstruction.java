package de.chrlembeck.aoc2020.day08;

public class AccumulatorInstruction extends AbstractInstruction {

    public AccumulatorInstruction(final int argument) {
        super(argument);
    }

    @Override
    public void execute(final Program program) {
        program.incAccumulator(getArgument());
        program.incProgramCounter(1);
    }
}