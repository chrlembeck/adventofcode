package de.chrlembeck.aoc2016.day01;

public enum Direction {

    NORTH,
    EAST,
    SOUTH,
    WEST;

    public Direction left() {
        return switch (this) {
            case NORTH -> WEST;
            case EAST -> NORTH;
            case SOUTH -> EAST;
            case WEST -> SOUTH;
            default -> throw new IllegalStateException();
        };
    }

    public Direction right() {
        return switch (this) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
            default -> throw new IllegalStateException();
        };
    }
}