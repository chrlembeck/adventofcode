package de.chrlembeck.aoc2016.day10;

import java.util.Optional;

public class OptionalResult {

    private final Optional<Integer> optional;

    public final static OptionalResult EMPTY = new OptionalResult(Optional.empty());

    private <T> OptionalResult(Optional<Integer> optional) {
        this.optional = optional;
    }

    public OptionalResult(Integer value) {
        this.optional = Optional.of(value);
    }

    public boolean isPresent() {
        return optional.isPresent();
    }

    public OptionalResult or(OptionalResult other) {
        return isPresent() ? this : other;
    }

    public int getValue() {
        return optional.get();
    }
}