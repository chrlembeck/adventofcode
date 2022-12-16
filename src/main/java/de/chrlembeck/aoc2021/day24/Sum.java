package de.chrlembeck.aoc2021.day24;

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

    public boolean isGreaterOrEqualToZeroAndLessThan(long value) {
        return value > 0 && addends.size() == 2
                && addends.get(0) instanceof Variable
                && addends.get(1) instanceof IntValue c
                && c.value() + 9 < 0;
    }

    public Optional<Range> getRange() {
        if (addends.stream().anyMatch(a -> a.getRange().isEmpty())) {
            return Optional.empty();
        } else {
            return Optional.of(new Range(
                    addends.stream().map(Expression::getRange).map(Optional::get).map(Range::lower).reduce(0l, (a, b) -> a + b),
                    addends.stream().map(Expression::getRange).map(Optional::get).map(Range::upper).reduce(0l, (a, b) -> a + b)));
        }
    }

    @Override
    public boolean isDividableBy(long i) {
        return addends.stream().allMatch(a -> a.isDividableBy(i));
    }

    @Override
    public Expression divideSpecBy(long denominator) {
        if (isDividableBy(denominator)) {
            return Expression.createAdd(addends.stream().map(a -> a.divideSpecBy(denominator)).toArray(Expression[]::new));
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public long evaluate(Map<Variable, Long> values) {
        return addends.stream().map(f -> f.evaluate(values)).reduce((a, b) -> a + b).orElseThrow();
    }

    @Override
    public void collectVariables(Set<Variable> variables) {
        addends.forEach(a -> a.collectVariables(variables));
    }
}