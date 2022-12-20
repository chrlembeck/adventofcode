package de.chrlembeck.aoc2021.day23;

import static de.chrlembeck.aoc2021.day23.Position.*;

public enum AmphipodType {

    AMBER(1),

    BRONZE(10),

    COPPER(100),

    DESERT(1000);

    private final int energy;

    AmphipodType(int energy) {
        this.energy = energy;
    }

    public static AmphipodType of(char ch) {
        return switch (ch) {
            case 'A' -> AMBER;
            case 'B' -> BRONZE;
            case 'C' -> COPPER;
            case 'D' -> DESERT;
            default -> throw new RuntimeException("" + ch);
        };
    }

    public int getEnergy() {
        return energy;
    }

    public boolean isHome(Position pos) {
        return switch (this) {
            case AMBER -> pos == A1 || pos == A2;
            case BRONZE -> pos == B1 || pos == B2;
            case COPPER -> pos == C1 || pos == C2;
            case DESERT -> pos == D1 || pos == D2;
        };
    }
}