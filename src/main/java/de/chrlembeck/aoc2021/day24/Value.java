package de.chrlembeck.aoc2021.day24;

public interface Value {

    Expression eval(State state);
}