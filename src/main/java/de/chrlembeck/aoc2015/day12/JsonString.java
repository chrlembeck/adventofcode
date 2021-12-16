package de.chrlembeck.aoc2015.day12;

public class JsonString implements JsonElement{

    private String value;

    public JsonString(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "\"" + value + "\"";
    }

    @Override
    public long sumNumbers(boolean excludeRedValues) {
        return 0;
    }
}
