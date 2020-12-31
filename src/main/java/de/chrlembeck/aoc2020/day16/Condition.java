package de.chrlembeck.aoc2020.day16;

class Condition {

    private final String name;

    private final int fromA;

    private final int toA;

    private final int fromB;

    private final int toB;

    public Condition(String name, int fromA, int toA, int fromB, int toB) {
        this.name = name;
        this.fromA = fromA;
        this.toA = toA;
        this.fromB = fromB;
        this.toB = toB;
    }

    public boolean contains(final int value) {
        return (fromA <= value && value <= toA) || (fromB <= value && value <= toB);
    }

    public boolean startsWith(String prefix) {
        return name.startsWith(prefix);
    }
}