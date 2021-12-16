package de.chrlembeck.aoc2015.day12;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class JsonObject implements JsonElement {

    private Map<String, JsonElement> attributes = new TreeMap<>();

    public JsonObject(Map<String, JsonElement> attributes) {
        this.attributes = new TreeMap<>(attributes);
    }

    @Override
    public String toString() {
        return "{" + attributes.entrySet().stream().map(e -> e.getKey() + ":" + e.getValue()).collect(Collectors.joining(",")) + "}";
    }

    @Override
    public long sumNumbers(boolean excludeRedValues) {
        return (excludeRedValues && attributes.values().stream().filter(e -> e instanceof JsonString).map(e -> (JsonString) e)
                .anyMatch(s -> s.getValue().equals("red"))) ? 0 :
                attributes.values().stream().mapToLong(e -> e.sumNumbers(excludeRedValues)).sum();
    }
}