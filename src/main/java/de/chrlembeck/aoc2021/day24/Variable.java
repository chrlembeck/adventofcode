package de.chrlembeck.aoc2021.day24;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static de.chrlembeck.aoc2021.day24.Range.VARIABLE_RANGE;

public record Variable (int index) implements Expression {

    @Override
    public String toString() {
        return "I[" + index + "]";
    }

    public Optional<Range> getRange() {
        return Optional.of(VARIABLE_RANGE);
    }

    @Override
    public boolean isDividableBy(long i) {
        return false;
    }

    @Override
    public Expression divideSpecBy(long denominator) {
        throw new RuntimeException();
    }

    @Override
    public long evaluate(Map<Variable, Long> values) {
        return Objects.requireNonNull(values.get(this));
    }

    @Override
    public void collectVariables(Set<Variable> variables) {
        variables.add(this);
    }
}