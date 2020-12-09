package de.chrlembeck.aoc2019.day15;

public enum Status {
    WALL,
    EMPTY,
    OXYGEN_SYSTEM;

    public static Status fromResult(final int result) {
        return switch (result) {
            case 0 -> WALL;
            case 1 -> EMPTY;
            case 2 -> OXYGEN_SYSTEM;
            default -> throw new IllegalArgumentException();
        };
    }
}
