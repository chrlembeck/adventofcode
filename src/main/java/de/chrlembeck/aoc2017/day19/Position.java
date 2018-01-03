package de.chrlembeck.aoc2017.day19;

public class Position {

    int posX;

    int posY;

    Direction direction;

    public Position(final int posX, final int posY, final Direction direction) {
        this.posX = posX;
        this.posY = posY;
        this.direction = direction;
    }

    public Position forward() {
        switch (direction) {
            case UP:
                return new Position(posX, posY - 1, direction);
            case DOWN:
                return new Position(posX, posY + 1, direction);
            case LEFT:
                return new Position(posX - 1, posY, direction);
            case RIGHT:
                return new Position(posX + 1, posY, direction);
            default:
                throw new IllegalStateException();
        }
    }

    public Position left() {
        switch (direction) {
            case UP:
                return new Position(posX, posY, Direction.LEFT);
            case DOWN:
                return new Position(posX, posY, Direction.RIGHT);
            case LEFT:
                return new Position(posX, posY, Direction.DOWN);
            case RIGHT:
                return new Position(posX, posY, Direction.UP);
            default:
                throw new IllegalStateException();
        }
    }

    public Position right() {
        switch (direction) {
            case UP:
                return new Position(posX, posY, Direction.RIGHT);
            case DOWN:
                return new Position(posX, posY, Direction.LEFT);
            case LEFT:
                return new Position(posX, posY, Direction.UP);
            case RIGHT:
                return new Position(posX, posY, Direction.DOWN);
            default:
                throw new IllegalStateException();
        }
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