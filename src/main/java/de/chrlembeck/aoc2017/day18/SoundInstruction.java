package de.chrlembeck.aoc2017.day18;

public class SoundInstruction implements Instruction {

    private final String varName;

    public SoundInstruction(final String varName) {
        this.varName = varName;
    }

    @Override
    public void execute(final State state) {
        state.playSound(state.getValue(varName));
    }
}