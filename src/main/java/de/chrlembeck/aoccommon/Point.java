package de.chrlembeck.aoccommon;

import java.util.Comparator;
import java.util.Objects;

public class Point implements Comparable<Point> {

    private static final Comparator<Point> COMPARATOR = Comparator.comparingInt(Point::getY).thenComparingInt(Point::getX);

    private final int x;

    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

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