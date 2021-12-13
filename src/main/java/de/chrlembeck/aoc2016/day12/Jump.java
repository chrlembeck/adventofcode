package de.chrlembeck.aoc2016.day12;

public class Jump implements Operation {

    private int offset;

    private int value;

    public Jump(int value, int offset) {
        this.value = value;
        this.offset = offset;
    }

    @Override
    public void execute(Environment env) {
        if (value != 0) {
            env.incPC(offset);
        } else {
            env.incPC();
        }
    }
}