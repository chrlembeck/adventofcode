package de.chrlembeck.aoc2020.day12;

public enum Direction {

    NORTH(0),

    EAST(90),

    SOUTH(180),

    WEST(270);

    private long degree;

    Direction(final long degree) {
        this.degree = degree;
    }

    public Direction left(final long value) {
        return byDegree(degree - value);
    }

    public Direction right(final long value) {
        return byDegree(degree + value);
    }

    public static Direction byDegree(long degree) {
        degree = degree % 360;
        if (degree < 0) {
            degree = (degree + 360) % 360;
        }
        return switch ((int)degree) {
            case 0 -> NORTH;
            case 90 -> EAST;
            case 180 -> SOUTH;
            case 270 -> WEST;
            default -> throw new IllegalArgumentException();
        };
    }
}