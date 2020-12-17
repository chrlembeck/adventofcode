package de.chrlembeck.aoc2015.day07;

import java.util.Map;

public class Constant implements Gate {

    private final int value;

    public Constant(final int value) {
        this.value = value;
    }

    @Override
    public int execute(final Map<String, Gate> program) {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}