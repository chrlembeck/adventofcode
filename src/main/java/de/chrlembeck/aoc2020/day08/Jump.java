package de.chrlembeck.aoc2020.day08;

public class Jump extends AbstractInstruction{

    public Jump(final int argument) {
        super(argument);
    }

    @Override
    public void execute(final Program program) {
        program.incProgramCounter(getArgument());
    }
}
