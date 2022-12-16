package de.chrlembeck.aoc2021.day24;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Product implements Expression {

    private final List<Expression> factors;

    Product(List<Expression> factors) {
        this.factors = factors;
    }

    @Override
    public String toString() {
        return "(" + factors.stream().map(Object::toString).collect(Collectors.joining("*")) + ")";
    }

    public List<Expression> getFactors() {
        return factors;
    }

    @Override
    public Optional<Range> getRange() {
        if (factors.size() == 2 && factors.get(0).getRange().isPresent() && factors.get(1).getRange().isPresent()) {
            Range range1 = factors.get(0).getRange().get();
            Range range2 = factors.get(1).getRange().get();
            BigInteger[] values = new BigInteger[4];
            values[0] = range1.lower().multiply(range2.lower());
            values[1] = range1.lower().multiply(range2.upper());
            values[2] = range1.upper().multiply(range2.lower());
            values[3] = range1.upper().multiply(range2.upper());
            Arrays.sort(values);
            return Optional.of(new Range(values[0], values[3]));
        }
        System.out.println(" NO RANGE FOR " + this);
        return Optional.empty();
    }

    @Override
    public boolean isDividableBy(BigInteger i) {
        return factors.stream().anyMatch(f -> f.isDividableBy(i));
    }

    @Override
    public Expression divideSpecBy(BigInteger denominator) {
        List<Expression> newFactors = new ArrayList<>(factors);
        return newFactors.stream()
                .filter(f -> f.isDividableBy(denominator))
                .findFirst()
                .map(f -> {
                    newFactors.remove(f);
                    newFactors.add(f.divideSpecBy(denominator));
                    return Expression.createMul(newFactors.toArray(Expression[]::new));
                }).orElseThrow();
    }

    @Override
    public BigInteger evaluate(Map<Variable, BigInteger> values) {
        return factors.stream().map(f -> f.evaluate(values)).reduce(BigInteger::multiply).orElseThrow();
    }

    @Override
    public void collectVariables(Set<Variable> variables) {
        factors.forEach(f -> f.collectVariables(variables));
    }
}