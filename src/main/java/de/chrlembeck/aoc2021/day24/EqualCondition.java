package de.chrlembeck.aoc2021.day24;

import java.util.Map;

import static de.chrlembeck.aoc2021.day24.Range.VARIABLE_RANGE;
import static java.lang.Math.max;
import static java.lang.Math.min;

public record EqualCondition(Expression left, Expression right) implements BooleanCondition {

    @Override
    public String toString() {
        return "(" + left + "=" + right + ")";
    }

    public void collectRanges(Map<Integer, Range> ranges) {
        if (right instanceof Variable y
                && left instanceof Sum add
                && add.getAddends().size() == 2
                && add.getAddends().get(0) instanceof Variable x
                && add.getAddends().get(1) instanceof IntValue i) {
            ranges.put(x.index(), new Range(max(1 - i.value(), 1), min(9 - i.value(), 9)));
            ranges.put(y.index(), new Range(max(1 + i.value(), 1), min(9 + i.value(), 9)));
        } else if (left instanceof Variable x && right instanceof Variable y) {
            ranges.put(x.index(), VARIABLE_RANGE);
            ranges.put(y.index(), VARIABLE_RANGE);
        } else {
            throw new RuntimeException();
        }
    }
}