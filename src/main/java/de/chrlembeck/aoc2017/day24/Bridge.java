package de.chrlembeck.aoc2017.day24;

public class Bridge {

    int length;

    int strength;

    public Bridge(final int length, final int strength) {
        super();
        this.length = length;
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

    public int getLength() {
        return length;
    }
}