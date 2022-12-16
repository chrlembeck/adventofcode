package de.chrlembeck.aoc2021.day24;

public record EqualCondition(Expression left, Expression right) implements BooleanCondition {

    @Override
    public String toString() {
        return "(" + left + "=" + right + ")";
    }
}