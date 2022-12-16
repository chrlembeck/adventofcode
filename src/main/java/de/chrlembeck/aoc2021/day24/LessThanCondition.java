package de.chrlembeck.aoc2021.day24;

public class LessThanCondition implements BooleanCondition {

    private final Expression left;

    private final Expression right;

    public LessThanCondition(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "(" + left + "<" + right + ")";
    }
}