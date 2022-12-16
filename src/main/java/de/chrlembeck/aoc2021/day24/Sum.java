package de.chrlembeck.aoc2021.day24;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Sum implements Expression {

    private final List<Expression> addends;

    Sum(List<Expression> addends) {
        this.addends = addends;
    }

    @Override
    public String toString() {
        return "(" + addends.stream().map(Object::toString).collect(Collectors.joining("+")) + ")";
    }

    public List<Expression> getAddends() {
        return addends;
    }

    public boolean isGreaterOrEqualToZeroAndLessThan(BigInteger value) {
        return value.compareTo(BigInteger.ZERO) > 0 && addends.size() == 2
                && addends.get(0) instanceof Variable
                && addends.get(1) instanceof IntValue c
                && c.getValue().add(BigInteger.valueOf(9)).compareTo(value) < 0;
    }

    public Optional<Range> getRange() {
        if (addends.stream().anyMatch(a -> a.getRange().isEmpty())) {
            return Optional.empty();
        } else {
            return Optional.of(new Range(
                    addends.stream().map(Expression::getRange).map(Optional::get).map(Range::lower).reduce(BigInteger.ZERO, BigInteger::add),
                    addends.stream().map(Expression::getRange).map(Optional::get).map(Range::upper).reduce(BigInteger.ZERO, BigInteger::add)));
        }
    }

    @Override
    public boolean isDividableBy(BigInteger i) {
        return addends.stream().allMatch(a -> a.isDividableBy(i));
    }

    @Override
    public Expression divideSpecBy(BigInteger denominator) {
        if (isDividableBy(denominator)) {
            return Expression.createAdd(addends.stream().map(a -> a.divideSpecBy(denominator)).toArray(Expression[]::new));
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public BigInteger evaluate(Map<Variable, BigInteger> values) {
        return addends.stream().map(f -> f.evaluate(values)).reduce(BigInteger::add).orElseThrow();
    }

    @Override
    public void collectVariables(Set<Variable> variables) {
        addends.forEach(a -> a.collectVariables(variables));
    }
}