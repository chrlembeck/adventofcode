package de.chrlembeck.aoc2019.day05;

public class Exit extends AbstractInstruction {

    Exit(final IntcodeProgram program, final State state) {
        super(program, state);
    }

    @Override
    public void exec(final IntcodeProgram program, final State state) {
        throw new RuntimeException("programm already halted.");
    }
}