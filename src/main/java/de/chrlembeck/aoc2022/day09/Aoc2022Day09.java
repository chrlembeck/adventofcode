package de.chrlembeck.aoc2022.day09;

import de.chrlembeck.aoccommon.AbstractAocBase;
import de.chrlembeck.aoccommon.Point;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.signum;
import static java.lang.Math.abs;

public class Aoc2022Day09 extends AbstractAocBase {

    private final Pattern commandPattern = Pattern.compile("(\\w) (\\d+)");

    public static void main(final String[] args) {
        new Aoc2022Day09().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        return calc(input, 2);
    }

    @Override
    public Integer part2(final Scanner input) {
        return calc(input, 10);
    }

    private int calc(final Scanner input, int length) {
        Point[] rope = new Point[length];
        Arrays.fill(rope, new Point(0, 0));
        Set<Point> history = new HashSet<>();
        history.add(rope[length - 1]);
        while (input.hasNextLine()) {
            final Matcher matcher = matchRegex(commandPattern, input.nextLine());
            final String direction = matcher.group(1);
            final int steps = Integer.parseInt(matcher.group(2));
            for (int i = 0; i < steps; i++) {
                rope[0] = move(rope[0], direction);
                for (int m = 1; m < rope.length; m++) {
                    rope[m] = follow(rope[m - 1], rope[m]);
                }
                history.add(rope[length - 1]);
            }
        }
        return history.size();
    }

    private Point follow(Point head, Point tail) {
        if (abs(tail.x() - head.x()) > 1 || abs(tail.y() - head.y()) > 1) {
            return new Point(tail.x() + signum(head.x() - tail.x()), tail.y() + signum(head.y() - tail.y()));
        }
        return tail;
    }

    private Point move(Point point, String direction) {
        return switch (direction) {
            case "U" -> point.top();
            case "D" -> point.bottom();
            case "L" -> point.left();
            case "R" -> point.right();
            default -> throw new IllegalArgumentException();
        };
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2022/aoc2022day09.txt";
    }
}