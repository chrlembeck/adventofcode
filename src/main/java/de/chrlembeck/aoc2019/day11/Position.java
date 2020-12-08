package de.chrlembeck.aoc2019.day11;

import java.util.Comparator;
import java.util.Objects;

public class Position implements Comparable<Position> {

    public static final Comparator<Position> X_Y_COMPARATOR = Comparator
            .nullsFirst(Comparator.comparing(Position::getyPos).thenComparing(Position::getxPos));

    private int xPos;

    private int yPos;

    public Position(final int xPos, final int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    @Override
    public int compareTo(final Position other) {
        return X_Y_COMPARATOR.compare(this, other);
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        final Position otherPosition = (Position) other;
        return xPos == otherPosition.xPos && yPos == otherPosition.yPos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xPos, yPos);
    }

    @Override
    public String toString() {
        return "(" + xPos + "; " + yPos + ")";
    }
}