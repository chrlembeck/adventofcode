package de.chrlembeck.aoc2019.day11;

import java.util.Comparator;
import java.util.Objects;

public class Position implements Comparable<Position> {

    public static final Comparator<Position> X_Y_COMPARATOR = Comparator
            .nullsFirst(Comparator.comparing(Position::getPosY).thenComparing(Position::getPosX));

    private final int posX;

    private final int posY;

    public Position(final int posX, final int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
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
        return posX == otherPosition.posX && posY == otherPosition.posY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(posX, posY);
    }

    @Override
    public String toString() {
        return "(" + posX + "; " + posY + ")";
    }

    public Position leftNeighbour() {
        return new Position(posX - 1, posY);
    }

    public Position rightNeighbour() {
        return new Position(posX + 1, posY);
    }

    public Position topNeighbour() {
        return new Position(posX, posY - 1);
    }

    public Position bottomNeighbour() {
        return new Position(posX, posY + 1);
    }
}