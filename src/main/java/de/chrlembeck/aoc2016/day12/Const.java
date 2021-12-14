package de.chrlembeck.aoc2016.day12;

public class Const implements Expression {

    private int value;

    public Const(int value) {
        this.value = value;
    }

    @Override
    public int evaluate(Environment env) {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
