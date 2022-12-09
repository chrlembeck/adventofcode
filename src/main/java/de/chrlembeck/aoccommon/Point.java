package de.chrlembeck.aoccommon;

import java.util.Comparator;

public record Point(int x, int y) implements Comparable<Point> {

    private static final Comparator<Point> COMPARATOR = Comparator.comparingInt(Point::getY).thenComparingInt(Point::getX);

    @Override
    public String toString() {
        return "(" + x + ';' + y + ')';
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point left() {
        return new Point(x - 1, y);
    }

    public Point right() {
        return new Point(x + 1, y);
    }

    public Point top() {
        return new Point(x, y - 1);
    }

    public Point bottom() {
        return new Point(x, y + 1);
    }

    @Override
    public int compareTo(Point other) {
        return COMPARATOR.compare(this, other);
    }
}