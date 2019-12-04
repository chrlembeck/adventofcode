package de.chrlembeck.aoc2019.day03;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Aoc2019Day03 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2019Day03().run();
    }

    @Override
    public Object part1(final Scanner input) {
        final String line1 = input.nextLine();
        final String line2 = input.nextLine();
        final Map<Point, Integer> points1 = calcPoints(line1);
        final Map<Point, Integer> points2 = calcPoints(line2);
        int min = Integer.MAX_VALUE;
        for (final Map.Entry<Point,Integer> e1: points1.entrySet()) {
            if (points2.containsKey(e1.getKey())) {
                final int dist = Math.abs(e1.getKey().x)+Math.abs(e1.getKey().y);
                min = Math.min(min, dist);
            }
        }
        return min;
    }

    @Override
    public Object part2(final Scanner input) {
        final String line1 = input.nextLine();
        final String line2 = input.nextLine();
        final Map<Point, Integer> points1 = calcPoints(line1);
        final Map<Point, Integer> points2 = calcPoints(line2);
        int min = Integer.MAX_VALUE;
        for (final Map.Entry<Point,Integer> e1: points1.entrySet()) {
            final Integer dist2 = points2.get(e1.getKey());
            if (dist2 != null) {
                min = Math.min(min, e1.getValue()+dist2);
            }
        }
        return min;
    }

    private Map<Point, Integer> calcPoints(final String line) {
        final Map<Point, Integer> points = new HashMap<>();
        final String[] commands = line.split(",");
        int posX = 0;
        int posY = 0;
        int steps = 0;
        for (final String command: commands) {
            final char direction = command.charAt(0);
            final int distance = Integer.valueOf(command.substring(1));
            switch (direction) {
                case 'U': for (int i = 0; i < distance; i++) {
                    posY = posY - 1;
                    steps++;
                    final Point pos = new Point(posX,posY);
                    if (!points.containsKey(pos)) {
                        points.put(pos, steps);
                    }
                }
                break;
                case 'D': for (int i = 0; i < distance; i++) {
                    posY = posY + 1;
                    steps++;
                    final Point pos = new Point(posX,posY);
                    if (!points.containsKey(pos)) {
                        points.put(pos, steps);
                    }
                }
                break;
                case 'L': for (int i = 0; i < distance; i++) {
                    posX = posX - 1;
                    steps++;
                    final Point pos = new Point(posX,posY);
                    if (!points.containsKey(pos)) {
                        points.put(pos, steps);
                    }
                }
                break;
                case 'R': for (int i = 0; i < distance; i++) {
                    posX = posX + 1;
                    steps++;
                    final Point pos = new Point(posX,posY);
                    if (!points.containsKey(pos)) {
                        points.put(pos, steps);
                    }
                }
                break;
                default: throw new RuntimeException();
            }
        }

        return points;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2019/aoc2019day03.txt";
    }
}