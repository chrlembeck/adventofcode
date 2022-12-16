package de.chrlembeck.aoc2021.day24;

import java.math.BigInteger;
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
    public boolean isDividableBy(BigInteger i) {
        return false;
    }

    @Override
    public Expression divideSpecBy(BigInteger denominator) {
        throw new RuntimeException();
    }

    @Override
    public BigInteger evaluate(Map<Variable, BigInteger> values) {
        return Objects.requireNonNull(values.get(this));
    }

    @Override
    public void collectVariables(Set<Variable> variables) {
        variables.add(this);
    }
}