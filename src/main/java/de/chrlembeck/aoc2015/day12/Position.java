package de.chrlembeck.aoc2015.day12;

public class Position {

    private int pos;

    public int getPos() {
        return pos;
    }

    public void inc(int quantity) {
        pos += quantity;
    }
}