package de.chrlembeck.aoc2015.day12;

public class JsonConst implements JsonElement{

    private int value;

    public JsonConst(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public long sumNumbers(boolean excludeRedValues) {
        return value;
    }
}