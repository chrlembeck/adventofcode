package de.chrlembeck.aoc2021.day24;

import java.math.BigInteger;

public record Range(BigInteger lower, BigInteger upper) {

    public static final Range VARIABLE_RANGE = new Range(BigInteger.ONE, BigInteger.valueOf(9));

    public boolean isOutsideVariableRange() {
        return lower.compareTo(BigInteger.valueOf(9)) > 0 || upper.compareTo(BigInteger.ONE) < 0;
    }

    public boolean contains(BigInteger value) {
        return lower.compareTo(value) <= 0 && value.compareTo(upper) <= 0;
    }

    public Range merge(Range range) {
        Range newRange = new Range(lower.max(range.lower), upper.min(range.upper));
        if (newRange.lower.compareTo(newRange.upper)> 0) {
            throw new RuntimeException();
        }
        return newRange;
    }

    @Override
    public String toString() {
        return "[" +lower + ".." + upper + "]";
    }
}
