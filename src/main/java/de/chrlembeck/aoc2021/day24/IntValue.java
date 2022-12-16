package de.chrlembeck.aoc2021.day24;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public record IntValue(long value) implements Value, Expression {

    public static final IntValue ZERO = new IntValue(0);

    public static final IntValue ONE = new IntValue(1);

    @Override
    public Expression eval(State state) {
        return this;
    }

    @Override
    public String toString() {
        return Long.toString(value);
    }

    public Optional<Range> getRange() {
        return Optional.of(new Range(value, value));
    }

    @Override
    public boolean isDividableBy(long i) {
        return value % i == 0;
    }

    @Override
    public Expression divideSpecBy(long denominator) {
        if (isDividableBy(denominator)) {
            return new IntValue(value / denominator);
        } else {
            throw new RuntimeException();
        }
    }

    public IntValue add(IntValue addend) {
        return new IntValue(value + addend.value());
    }

    public IntValue mul(IntValue factor) {
        return new IntValue(value * factor.value());
    }

    @Override
    public long evaluate(Map<Variable, Long> values) {
        return value;
    }

    @Override
    public void collectVariables(Set<Variable> variables) {
    }
}