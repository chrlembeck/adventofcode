package de.chrlembeck.aoc2021.day24;

import java.util.List;
import java.util.stream.Collectors;

public class OrCondition implements BooleanCondition {

    private final List<BooleanCondition> conditions;

    OrCondition(List<BooleanCondition> newConditions) {
        this.conditions = newConditions;
    }

    @Override
    public String toString() {
        return "(" + conditions.stream().map(Object::toString).collect(Collectors.joining(" OR ")) + ")";
    }

    public List<BooleanCondition> getConditions() {
        return conditions;
    }
}
