package de.chrlembeck.aoc2020.day12;

public class Position {

    private final long x;

    private final long y;

    public Position(final long x, final long y) {
        this.x = x;
        this.y = y;
    }

    public Position left(final long value) {
        return new Position(x, y);
    }

    public Position right(final long value) {
        return new Position(x, y);
    }

    public Position north(final long steps) {
        return new Position(x, y - steps);
    }

    public Position south(final long steps) {
        return new Position(x, y + steps);
    }

    public Position east(final long steps) {
        return new Position(x + steps, y);
    }

    public Position west(final long steps) {
        return new Position(x - steps, y);
    }

    public long getY() {
        return y;
    }

    public long getX() {
        return x;
    }

    public long getManhattan() {
        return Math.abs(x) + Math.abs(y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public Position translate(final long dx, final long dy) {
        return new Position(x + dx, y + dy);
    }

    public Position rotateRight(final int value) {
        return new Position(x * cos(value) - y * sin(value), x * sin(value) + y * cos(value));
    }

    public Position rotateLeft(final int degree) {
        return rotateRight(-degree);
    }

    public static int sin(int degree) {
        degree = degree % 360;
        if (degree < 0) {
            degree = (360 + degree) % 360;
        }
        return switch (degree) {
            case 0 -> 0;
            case 90 -> 1;
            case 180 -> 0;
            case 270 -> -1;
            default -> throw new IllegalArgumentException();
        };
    }

    public static int cos(final int degree) {
        return sin(degree + 90);
    }
}