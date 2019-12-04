package de.chrlembeck.aoc2019.day03;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Aoc2019Day03 extends AbstractAocBase {

    @Override
    public Object part1(Scanner input) {
        String line1 = input.nextLine();
        String line2 = input.nextLine();
        Map<Point, Integer> points1 = calcPoints(line1);
        Map<Point, Integer> points2 = calcPoints(line2);
        int min = Integer.MAX_VALUE;
        for (Map.Entry<Point,Integer> e1: points1.entrySet()) {
            if (points2.containsKey(e1.getKey())) {
                int dist = Math.abs(e1.getKey().x)+Math.abs(e1.getKey().y);
                min = Math.min(min, dist);
            }
        }
        return min;
    }

    @Override
    public Object part2(Scanner input) {
        String line1 = input.nextLine();
        String line2 = input.nextLine();
        Map<Point, Integer> points1 = calcPoints(line1);
        Map<Point, Integer> points2 = calcPoints(line2);
        int min = Integer.MAX_VALUE;
        for (Map.Entry<Point,Integer> e1: points1.entrySet()) {
            Integer dist2 = points2.get(e1.getKey());
            if (dist2 != null) {
                min = Math.min(min, e1.getValue()+dist2);
            }
        }
        return min;
    }

    private Map<Point, Integer> calcPoints(String line) {
        Map<Point, Integer> points = new HashMap<>();
        String[] commands = line.split(",");
        int x = 0;
        int y = 0;
        int steps = 0;
        for (String command: commands) {
            char direction = command.charAt(0);
            int distance = Integer.valueOf(command.substring(1));
            switch (direction) {
                case 'U': for (int i = 0; i < distance; i++) {
                    y = y - 1;
                    steps++;
                    Point pos = new Point(x,y);
                    if (!points.containsKey(pos)) {
                        points.put(pos, steps);
                    }
                }
                break;
                case 'D': for (int i = 0; i < distance; i++) {
                    y = y + 1;
                    steps++;
                    Point pos = new Point(x,y);
                    if (!points.containsKey(pos)) {
                        points.put(pos, steps);
                    }
                }
                break;
                case 'L': for (int i = 0; i < distance; i++) {
                    x = x - 1;
                    steps++;
                    Point pos = new Point(x,y);
                    if (!points.containsKey(pos)) {
                        points.put(pos, steps);
                    }
                }
                break;
                case 'R': for (int i = 0; i < distance; i++) {
                    x = x + 1;
                    steps++;
                    Point pos = new Point(x,y);
                    if (!points.containsKey(pos)) {
                        points.put(pos, steps);
                    }
                }
                break;
            }
        }

        return points;
    }

    @Override
    public String getInputLocation(int part) {
        return "/input/aoc2019/day03.txt";
    }
}