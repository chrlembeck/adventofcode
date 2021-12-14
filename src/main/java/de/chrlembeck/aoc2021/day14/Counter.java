package de.chrlembeck.aoc2021.day14;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Counter {

    private Map<String, Long> state = new TreeMap<>();

    public void add(String part, long quantity) {
        state.compute(part, (p, q) -> q == null ? quantity : q + quantity);
    }

    public Set<Map.Entry<String, Long>> entrySet() {
        return state.entrySet();
    }

    public Map<Character, Long> countCharacters(char firstCharacter) {
        Map<Character, Long> result = new TreeMap<>();
        result.put(firstCharacter, 1l);
        for (Map.Entry<String, Long> entry : state.entrySet()) {
            result.compute(entry.getKey().charAt(1), (k, v) -> v == null ? entry.getValue() : v + entry.getValue());
        }
        return result;
    }
}