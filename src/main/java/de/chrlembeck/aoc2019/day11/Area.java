package de.chrlembeck.aoc2019.day11;

import de.chrlembeck.aoc2017.day19.Direction;
import java.util.Map;
import java.util.TreeMap;

public class Area {

    public static final int BLACK = 0;

    public static final int WHITE = 1;

    private Position position = new Position(0, 0);

    private Direction direction = Direction.UP;

    private final Map<Position, Integer> currentColors = new TreeMap();

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
                    case UP -> new Position(position.getPosX(), position.getPosY() - 1);
                    case LEFT -> new Position(position.getPosX() - 1, position.getPosY());
                    case DOWN -> new Position(position.getPosX(), position.getPosY() + 1);
                    case RIGHT -> new Position(position.getPosX() + 1, position.getPosY());
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
        final int minY = currentColors.keySet().stream().mapToInt(Position::getPosY).min().getAsInt();
        final int minX = currentColors.keySet().stream().mapToInt(Position::getPosX).min().getAsInt();
        final int maxY = currentColors.keySet().stream().mapToInt(Position::getPosY).max().getAsInt();
        final int maxX = currentColors.keySet().stream().mapToInt(Position::getPosX).max().getAsInt();
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