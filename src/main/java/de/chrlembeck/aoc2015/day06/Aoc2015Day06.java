package de.chrlembeck.aoc2015.day06;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.chrlembeck.aoccommon.AbstractAocBase;

public class Aoc2015Day06 extends AbstractAocBase {
    // toggle 772,846 through 994,888

    Pattern regex = Pattern.compile("(toggle|turn\\ on|turn\\ off)\\ (\\d+),(\\d+)\\ through\\ (\\d+),(\\d+)");

    public static void main(final String[] args) {
        new Aoc2015Day06().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        final boolean[][] lights = new boolean[1000][1000];
        while (input.hasNextLine()) {
            final Matcher matcher = matchRegex(regex, input.nextLine());
            final String command = matcher.group(1);
            final int startX = Integer.parseInt(matcher.group(2));
            final int startY = Integer.parseInt(matcher.group(3));
            final int endX = Integer.parseInt(matcher.group(4));
            final int endY = Integer.parseInt(matcher.group(5));
            for (int y = startY; y <= endY; y++) {
                for (int x = startX; x <= endX; x++) {
                    switch (command) {
                        case "toggle":
                            lights[y][x] ^= true;
                            break;
                        case "turn on":
                            lights[y][x] = true;
                            break;
                        case "turn off":
                            lights[y][x] = false;
                            break;
                        default:
                            throw new IllegalStateException();
                    }
                }
            }
        }
        int bright = 0;
        for (final boolean[] row : lights) {
            for (final boolean light : row) {
                if (light) {
                    bright++;
                }
            }
        }
        return bright;
    }

    @Override
    public Long part2(final Scanner input) {
        final int[][] lights = new int[1000][1000];
        while (input.hasNextLine()) {
            final Matcher matcher = matchRegex(regex, input.nextLine());
            final String command = matcher.group(1);
            final int startX = Integer.parseInt(matcher.group(2));
            final int startY = Integer.parseInt(matcher.group(3));
            final int endX = Integer.parseInt(matcher.group(4));
            final int endY = Integer.parseInt(matcher.group(5));
            for (int y = startY; y <= endY; y++) {
                for (int x = startX; x <= endX; x++) {
                    switch (command) {
                        case "toggle":
                            lights[y][x] += 2;
                            break;
                        case "turn on":
                            lights[y][x]++;
                            break;
                        case "turn off":
                            lights[y][x] = Math.max(0, lights[y][x] - 1);
                            break;
                        default:
                            throw new IllegalStateException();
                    }
                }
            }
        }
        long bright = 0;
        for (final int[] row : lights) {
            for (final int light : row) {
                bright += light;
            }
        }
        return bright;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2015/aoc2015day06.txt";
    }
}