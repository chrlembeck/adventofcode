package de.chrlembeck.aoc2017.day22;

import de.chrlembeck.aoc2017.day19.Position;
import de.chrlembeck.aoc2017.day22.Aoc2017Day22.State;
import de.chrlembeck.util.collections.BidirectionalGrowingArray;

@FunctionalInterface
public interface VirusFunction {

    Position apply(Position currentPosition, State currentState, BidirectionalGrowingArray<State> currentRow,
            Counter counter);
}