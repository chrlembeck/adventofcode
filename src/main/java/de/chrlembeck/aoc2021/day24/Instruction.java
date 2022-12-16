package de.chrlembeck.aoc2021.day24;

import java.util.Collection;

public interface Instruction {

    Collection<State> executeSymbolic(State state);
}