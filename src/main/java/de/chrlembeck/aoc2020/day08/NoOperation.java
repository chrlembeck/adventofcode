package de.chrlembeck.aoc2020.day08;

public class NoOperation extends AbstractInstruction{

    public NoOperation(final int value) {
        super(value);
    }

    @Override
    public void execute(final Program program) {
        program.incProgramCounter(1);
    }
}
