package de.chrlembeck.aoc2021.day24;

import java.util.List;
import java.util.stream.Collectors;

public class AndCondition implements BooleanCondition {

    private final List<BooleanCondition> conditions;

    AndCondition(List<BooleanCondition> conditions) {
        this.conditions = conditions;
    }

    public List<BooleanCondition> getConditions() {
        return conditions;
    }

    @Override
    public String toString() {
        return "(" + conditions.stream().map(Object::toString).collect(Collectors.joining(" AND ")) + ")";
    }
}
