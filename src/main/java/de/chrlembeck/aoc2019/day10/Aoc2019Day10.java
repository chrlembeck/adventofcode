package de.chrlembeck.aoc2019.day10;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.awt.*;
import java.util.*;

public class Aoc2019Day10 extends AbstractAocBase {

    public static Comparator<Point> angleComparator = Comparator.comparingDouble((Point point) -> {
        if (point.x == 0) {
            return angle(0, point.y > 0 ? 1 : -1);
        } else if (point.y == 0) {
            return angle(point.x > 0 ? 1 : -1, 0);
        } else {
            final int gcd = gcd(point.x, point.y);
            return (angle(point.x / gcd, point.y / gcd));
        }
    });

    public static Comparator<Point> distanceComparator = Comparator.comparingInt((Point point) -> point.x * point.x + point.y * point.y);

    public static void main(final String[] args) {
        new Aoc2019Day10().run();
    }

    @Override
    public Object part1(final Scanner input) {
        final boolean[][] asteroids = parseAstroids(input);
        final int[] best = findBest(asteroids);
        return best[2];
    }

    @Override
    public Object part2(final Scanner input) {
        final boolean[][] asteroids = parseAstroids(input);
        final int height = asteroids.length;
        final int width = asteroids[0].length;
        final int[] best = findBest(asteroids);
        final int stationX = best[0];
        final int stationY = best[1];
        final SortedMap<Double, SortedSet<Point>> map = new TreeMap<>();
        for (int yPos = 0; yPos < height; yPos++) {
            for (int xPos = 0; xPos < width; xPos++) {
                if ((xPos != stationX || yPos != stationY) && asteroids[yPos][xPos]) {
                    final Point point = new Point(xPos - stationX, yPos - stationY);
                    final double angle = angle(point.x, point.y);
                    SortedSet<Point> set = map.get(angle);
                    if (set == null) {
                        set = new TreeSet<>(distanceComparator);
                        map.put(angle, set);
                    }
                    set.add(point);
                }
            }
        }
        int counter = 0;
        final Collection<SortedSet<Point>> keys = map.values();

        while (counter < 200) {
            for (final SortedSet<Point> set : keys) {
                if (!set.isEmpty()) {
                    final Point point = set.first();
                    counter++;
                    set.remove(point);
                    if (counter == 200) {
                        return (stationX + point.x) * 100 + (stationY + point.y);
                    }
                }
            }
        }
        return null;
    }

    public int[] findBest(final boolean[][] asteroids) {
        final int height = asteroids.length;
        final int width = asteroids[0].length;
        int maxVisible = 0, maxX = -1, maxY = -1;
        for (int yPos = 0; yPos < height; yPos++) {
            for (int xPos = 0; xPos < width; xPos++) {
                if (asteroids[yPos][xPos]) {
                    final boolean[][] visibleAsteroids = copy(asteroids);
                    final int visible = countVisible(visibleAsteroids, xPos, yPos);
                    if (visible > maxVisible) {
                        maxVisible = visible;
                        maxX = xPos;
                        maxY = yPos;
                    }
                }
            }
        }
        return new int[] { maxX, maxY, maxVisible };
    }

    public boolean[][] parseAstroids(final Scanner input) {
        return input.tokens().map(this::scanLine).toArray(boolean[][]::new);
    }

    private int countVisible(final boolean[][] asteroids, final int stationX, final int stationY) {
        final int height = asteroids.length;
        final int width = asteroids[0].length;
        int visibleCount = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if ((x != stationX || y != stationY) && asteroids[y][x] && isVisible(asteroids, stationX, stationY, x, y)) {
                    visibleCount++;
                }
            }
        }
        return visibleCount;
    }

    public boolean isVisible(final boolean[][] asteroids, final int stationX, final int stationY, int asteroidX, int asteroidY) {
        int deltaX = stationX - asteroidX;
        int deltaY = stationY - asteroidY;
        if (deltaX == 0) {
            deltaY = deltaY / Math.abs(deltaY);
        } else if (deltaY == 0) {
            deltaX = deltaX / Math.abs(deltaX);
        } else {
            final int gcd = gcd(Math.abs(deltaX), Math.abs(deltaY));
            if (gcd == 1) {
                return true;
            }
            deltaX /= gcd;
            deltaY /= gcd;
        }
        asteroidX += deltaX;
        asteroidY += deltaY;
        while (asteroidX != stationX || asteroidY != stationY) {
            if (asteroids[asteroidY][asteroidX]) {
                return false;
            }
            asteroidX += deltaX;
            asteroidY += deltaY;
        }
        return true;
    }

    private boolean[][] copy(final boolean[][] asteroids) {
        final boolean[][] copy = new boolean[asteroids.length][];
        for (int y = 0; y < asteroids.length; y++) {
            copy[y] = Arrays.copyOf(asteroids[y], asteroids[y].length);
        }
        return copy;
    }

    private boolean[] scanLine(final String line) {
        final boolean[] asteroids = new boolean[line.length()];
        for (int characterIdx = 0; characterIdx < line.length(); characterIdx++) {
            asteroids[characterIdx] = line.charAt(characterIdx) == '#';
        }
        return asteroids;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2019/aoc2019day10.txt";
    }

    public static int gcd(final int operand1, final int operand2) {
        if (operand2 == 0) {
            return operand1;
        }
        return gcd(operand2, operand1 % operand2);
    }

    public static double angle(final int deltaX, final int deltaY) {
        return -Math.atan2(deltaX, deltaY) * 180 / Math.PI + 180;
    }
}