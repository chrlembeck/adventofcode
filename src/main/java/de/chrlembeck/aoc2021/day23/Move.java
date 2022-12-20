package de.chrlembeck.aoc2021.day23;

public record Move(Position from, Position to, AmphipodType amphipodType, int steps) {

    @Override
    public String toString() {
        return "Move " + amphipodType + " from " + from + " to " + to + " in " + steps + " steps.";
    }
}