package de.chrlembeck.aoc2021.day13;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2021Day13 extends AbstractAocBase {

    public static final Pattern DOT_REGEX = Pattern.compile("(\\d+),(\\d+)");

    public static final Pattern COMMAND_REGEX = Pattern.compile("fold along ([xy])=(\\d+)");

    public static void main(final String[] args) {
        new Aoc2021Day13().run();
    }

    @Override
    public Object part1(final Scanner input) {
        Set<Point> dots = readDots(input);
        dots = fold(dots, input.nextLine());
        return dots.size();
    }

    private Set<Point> readDots(Scanner input) {
        Set<Point> dots = new HashSet<>();
        while (input.hasNext()) {
            Matcher matcher = DOT_REGEX.matcher(input.nextLine());
            if (matcher.matches()) {
                dots.add(new Point(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))));
            } else {
                break;
            }
        }
        return dots;
    }

    private Set<Point> fold(Set<Point> dots, String command) {
        Matcher matcher = matchRegex(COMMAND_REGEX, command);
        int value = Integer.parseInt(matcher.group(2));
        Set<Point> newSet = new HashSet<>();
        switch (matcher.group(1)) {
            case "x":
                for (Point dot : dots) {
                    if (dot.x > value) {
                        newSet.add(new Point(2 * value - dot.x, dot.y));
                    } else {
                        newSet.add(dot);
                    }
                }
                break;
            case "y":
                for (Point dot : dots) {
                    if (dot.y > value) {
                        newSet.add(new Point(dot.x, 2 * value - dot.y));
                    } else {
                        newSet.add(dot);
                    }
                }
                break;
            default:
                throw new IllegalArgumentException();
        }
        return newSet;
    }

    @Override
    public Object part2(final Scanner input) {
        Set<Point> dots = readDots(input);
        while (input.hasNext()) {
            dots = fold(dots, input.nextLine());
        }
        String output = createOutput(dots);
        return output;
    }

    private String createOutput(Set<Point> dots) {
        int maxX = dots.stream().mapToInt(p -> p.x).max().getAsInt();
        int maxY = dots.stream().mapToInt(p -> p.y).max().getAsInt();
        char[][] chars = new char[maxY+1][maxX+1];
        for (char[] line: chars) {
            Arrays.fill(line, '.');
        }
        dots.stream().forEach(p -> chars[p.y][p.x] = '#');
        StringBuilder sb = new StringBuilder();

        for (char[] line: chars) {
            sb.append(line);
            sb.append('\n');
        }
        return sb.toString();
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2021/aoc2021day13.txt";
    }
}