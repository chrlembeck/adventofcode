package de.chrlembeck.aoc2021.day24;

public record Range(long lower, long upper) {

    public static final Range VARIABLE_RANGE = new Range(1, 9);

    public boolean isOutsideVariableRange() {
        return lower > 9 || upper < 1;
    }

    public boolean contains(long value) {
        return lower <= value && value <= upper;
    }

    @Override
    public String toString() {
        return "[" + lower + ".." + upper + "]";
    }
}
