package de.chrlembeck.aoc2020.day12;

public class Robot {

    private final Direction direction;

    private final Position position;

    public Robot(final Position position, final Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    public Robot forward(final long steps) {
        return switch (direction) {
            case NORTH -> new Robot(new Position(position.getX(), position.getY() - steps), direction);
            case EAST -> new Robot(new Position(position.getX() + steps, position.getY()), direction);
            case SOUTH -> new Robot(new Position(position.getX(), position.getY() + steps), direction);
            case WEST -> new Robot(new Position(position.getX() - steps, position.getY()), direction);
        };
    }

    public long getManhattan() {
        return position.getManhattan();
    }

    public Robot north(final long steps) {
        return new Robot(position.north(steps), direction);
    }

    public Robot east(final long steps) {
        return new Robot(position.east(steps), direction);
    }

    public Robot south(final long steps) {
        return new Robot(position.south(steps), direction);
    }

    public Robot west(final long steps) {
        return new Robot(position.west(steps), direction);
    }

    public Robot left(final long value) {
        return new Robot(position, direction.left(value));
    }

    public Robot right(final long value) {
        return new Robot(position, direction.right(value));
    }

    public long getX() {
        return position.getX();
    }

    public long getY() {
        return position.getY();
    }

    public Robot translate(final long dx, final long dy) {
        return new Robot(position.translate(dx, dy), direction);
    }
}