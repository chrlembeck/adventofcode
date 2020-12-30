package de.chrlembeck.aoc2020.day12;

public class Position {

    private final long posX;

    private final long posY;

    public Position(final long posX, final long posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public Position left(final long value) {
        return new Position(posX, posY);
    }

    public Position right(final long value) {
        return new Position(posX, posY);
    }

    public Position north(final long steps) {
        return new Position(posX, posY - steps);
    }

    public Position south(final long steps) {
        return new Position(posX, posY + steps);
    }

    public Position east(final long steps) {
        return new Position(posX + steps, posY);
    }

    public Position west(final long steps) {
        return new Position(posX - steps, posY);
    }

    public long getPosY() {
        return posY;
    }

    public long getPosX() {
        return posX;
    }

    public long getManhattan() {
        return Math.abs(posX) + Math.abs(posY);
    }

    @Override
    public String toString() {
        return "(" + posX + ", " + posY + ")";
    }

    public Position translate(final long deltaX, final long deltaY) {
        return new Position(posX + deltaX, posY + deltaY);
    }

    public Position rotateRight(final int value) {
        return new Position(posX * cos(value) - posY * sin(value), posX * sin(value) + posY * cos(value));
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