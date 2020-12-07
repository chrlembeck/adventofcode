package de.chrlembeck.aoc2019.day11;

import de.chrlembeck.aoc2017.day19.Direction;
import java.util.Map;
import java.util.TreeMap;

public class Area {

    public static final int BLACK = 0;

    public static final int WHITE = 1;

    private Position position = new Position(0, 0);

    private Direction direction = Direction.UP;

    private Map<Position, Integer> currentColors = new TreeMap();

    public void left() {
        direction = direction.left();
        move();
    }

    public void right() {
        direction = direction.right();
        move();
    }

    private void move() {
        position =
                switch (direction) {
                    case UP -> new Position(position.getxPos(), position.getyPos() - 1);
                    case LEFT -> new Position(position.getxPos() - 1, position.getyPos());
                    case DOWN -> new Position(position.getxPos(), position.getyPos() + 1);
                    case RIGHT -> new Position(position.getxPos() + 1, position.getyPos());
                };
    }

    public int getCurrentColor() {
        return getCurrentColor(position);
    }

    public int getCurrentColor(final Position position) {
        final Integer color = currentColors.get(position);
        return color == null ? BLACK : color;
    }

    public void paint(final int color) {
        paint(position, color);
    }

    public void paint(final Position position, final int color) {
        currentColors.put(position, color);
    }

    public int getPaintedFieldCount() {
        return currentColors.size();
    }

    public String getImage() {
        final int minY = currentColors.keySet().stream().mapToInt(Position::getyPos).min().getAsInt();
        final int minX = currentColors.keySet().stream().mapToInt(Position::getxPos).min().getAsInt();
        final int maxY = currentColors.keySet().stream().mapToInt(Position::getyPos).max().getAsInt();
        final int maxX = currentColors.keySet().stream().mapToInt(Position::getxPos).max().getAsInt();
        final StringBuilder image = new StringBuilder();
        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                image.append(getCurrentColor(new Position(x, y)) == BLACK ? '.' : "#");
            }
            image.append('\n');
        }

        return image.toString();
    }
}