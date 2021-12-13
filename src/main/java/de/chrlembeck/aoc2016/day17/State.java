package de.chrlembeck.aoc2016.day17;

public class State {

    private final byte x;

    private final byte y;

    private final String path;

    private final String input;

    public State(String input, byte x, byte y, String path) {
        this.x = x;
        this.y = y;
        this.path = path;
        this.input = input;
    }

    public String getPath() {
        return path;
    }

    public byte getX() {
        return x;
    }

    public byte getY() {
        return y;
    }
}