package de.chrlembeck.aoc2017.day19;

public class Position {

    private final int posX;

    private final int posY;

    private final Direction direction;

    public Position(final int posX, final int posY, final Direction direction) {
        this.posX = posX;
        this.posY = posY;
        this.direction = direction;
    }

    public Position forward() {
        return switch (direction) {
            case UP -> new Position(posX, posY - 1, direction);
            case DOWN -> new Position(posX, posY + 1, direction);
            case LEFT -> new Position(posX - 1, posY, direction);
            case RIGHT -> new Position(posX + 1, posY, direction);
        };
    }

    public Position left() {
        return new Position(posX, posY, direction.left());
    }

    public Position right() {
        return  new Position(posX, posY, direction.right());
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    @Override
    public String toString() {
        return "[x=" + posX + ", y=" + posY + ", direction=" + direction + "]";
    }
}