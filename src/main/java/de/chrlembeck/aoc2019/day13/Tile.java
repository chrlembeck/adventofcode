package de.chrlembeck.aoc2019.day13;

import java.util.Arrays;

public enum Tile {

    EMPTY(0),
    WALL(1),
    BLOCK(2),
    HORIZONTAL_PADDLE(3),
    BALL(4);

    private final int identifier;

    Tile(final int identifier) {
        this.identifier = identifier;
    }

    public static Tile fromId(final int identifier) {
        return Arrays.stream(values())
                .filter(tile -> tile.identifier == identifier)
                .findFirst()
                .orElseThrow();
    }
}