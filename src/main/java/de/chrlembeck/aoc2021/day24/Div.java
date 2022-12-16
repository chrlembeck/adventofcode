package de.chrlembeck.aoc2021.day24;

import java.math.BigInteger;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Div implements Expression {

    private final Expression numerator;

    private final Expression denominator;

    Div(Expression numerator, Expression denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public String toString() {
        return "(" + numerator + "/" + denominator + ")";
    }

    @Override
    public Optional<Range> getRange() {
        if (denominator instanceof IntValue i && i.getValue().compareTo(BigInteger.ZERO) > 0 && numerator.getRange().isPresent()) {
            Range range = numerator.getRange().get();
            return Optional.of(new Range(range.lower().divide(i.getValue()), range.upper().divide(i.getValue())));
        }
        System.out.println(" NO RANGE FOR " + this);
        return Optional.empty();
    }

    @Override
    public Expression divideSpecBy(BigInteger value) {
        throw new RuntimeException();
    }

    @Override
    public boolean isDividableBy(BigInteger value) {
        return false;
    }

    @Override
    public BigInteger evaluate(Map<Variable, BigInteger> values) {
        return numerator.evaluate(values).divide(denominator.evaluate(values));
    }

    @Override
    public void collectVariables(Set<Variable> variables) {
        numerator.collectVariables(variables);
        denominator.collectVariables(variables);
    }
}