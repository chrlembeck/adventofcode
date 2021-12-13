package de.chrlembeck.aoc2016.day22;

import java.awt.*;

public class Move {

    private final Point src;

    private final Point dest;

    public Move(Point src, Point dest) {
        this.src = src;
        this.dest = dest;
    }

    public Point getSrc() {
        return src;
    }

    public Point getDest() {
        return dest;
    }
}
