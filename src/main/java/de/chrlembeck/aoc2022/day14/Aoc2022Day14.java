package de.chrlembeck.aoc2022.day14;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.awt.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.function.BiPredicate;

public class Aoc2022Day14 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2022Day14().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        return calc(input, (p, yMax) -> p.y <= yMax) - 1;
    }

    @Override
    public Integer part2(final Scanner input) {
        return calc(input, (p, y) -> p.x != 500 || p.y != 0);
    }

    private int calc(final Scanner input, final BiPredicate<Point, Integer> predicate) {
        final Set<Point> area = readArea(input);
        final int yMax = area.stream().mapToInt(p -> p.y).max().orElseThrow();
        int count = 0;
        Point p;
        do {
            p = addPoint(area, yMax + 2);
            count++;
        } while (predicate.test(p, yMax));
        return count;
    }

    private Set<Point> readArea(Scanner input) {
        Set<Point> area = new HashSet<>();
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] points = line.split(" -> ");
            for (int i = 1; i < points.length; i++) {
                register(area, toPoint(points[i - 1]), toPoint(points[i]));
            }
        }
        return area;
    }

    private Point addPoint(Set<Point> area, int floor) {
        int x = 500;
        int y = 0;
        boolean moved = false;
        do {
            moved = false;
            if (!area.contains(new Point(x, y + 1))) {
                y++;
                moved = true;
            } else if (!area.contains(new Point(x - 1, y + 1))) {
                y++;
                x--;
                moved = true;
            } else if (!area.contains(new Point(x + 1, y + 1))) {
                x++;
                y++;
                moved = true;
            }
        } while (moved && y < floor - 1);
        Point p = new Point(x, y);
        area.add(p);
        return p;
    }

    private void register(Set<Point> area, Point pointA, Point pointB) {
        Point t = pointA;
        area.add(t);
        while (!t.equals(pointB)) {
            t = new Point(t.x + Integer.signum(pointB.x - t.x), t.y + Integer.signum(pointB.y - t.y));
            area.add(t);
        }
    }

    private Point toPoint(String data) {
        String[] coordinates = data.split(",");
        return new Point(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2022/aoc2022day14.txt";
    }
}