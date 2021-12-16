package de.chrlembeck.aoc2015.day12;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonArray implements JsonElement {

    private List<JsonElement> elements;

    public JsonArray(List<JsonElement> elements) {
        this.elements = new ArrayList<>(elements);
    }

    public List<JsonElement> getElements() {
        return elements;
    }

    @Override
    public String toString() {
        return "[" + elements.stream().map(Object::toString).collect(Collectors.joining(",")) + "]";
    }

    @Override
    public long sumNumbers(boolean excludeRedValues) {
        return elements.stream().mapToLong(e -> e.sumNumbers(excludeRedValues)).sum();
    }
}
