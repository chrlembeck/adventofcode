package de.chrlembeck.aoc2021.day24;

import java.math.BigInteger;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class IntValue implements Value, Expression {

    public static final IntValue MINUS_ONE = new IntValue(BigInteger.valueOf(-1));

    public static final IntValue ZERO = new IntValue(BigInteger.ZERO);

    public static final IntValue ONE = new IntValue(BigInteger.ONE);

    public static final IntValue NINE = new IntValue(BigInteger.valueOf(9));


    private final BigInteger value;

    public IntValue(BigInteger value) {
        this.value = value;
    }

    @Override
    public Expression eval(State state) {
        return this;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public BigInteger getValue() {
        return value;
    }

    public Optional<Range> getRange() {
        return Optional.of(new Range(value, value));
    }

    @Override
    public boolean isDividableBy(BigInteger i) {
        return value.mod(i).compareTo(BigInteger.ZERO) == 0;
    }

    @Override
    public Expression divideSpecBy(BigInteger denominator) {
        if (isDividableBy(denominator)) {
            return new IntValue(value.divide(denominator));
        } else {
            throw new RuntimeException();
        }
    }

    public IntValue add(IntValue addend) {
        return new IntValue(value.add(addend.getValue()));
    }

    public IntValue mul(IntValue factor) {
        return new IntValue(value.multiply(factor.getValue()));
    }

    @Override
    public BigInteger evaluate(Map<Variable, BigInteger> values) {
        return value;
    }

    @Override
    public void collectVariables(Set<Variable> variables) {
    }
}