package de.chrlembeck.aoc2021.day11;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.awt.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Aoc2021Day11 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2021Day11().run();
    }

    @Override
    public Object part1(final Scanner input) {
        int[][] octopuses = readOctopuses(input);
        int counter = 0;
        Set<Point> flashes = new HashSet<>();
        for (int round = 0; round < 100; round++) {
            performStep(octopuses, flashes);
            counter += flashes.size();
            flashes.clear();
        }

        return counter;
    }

    @Override
    public Object part2(final Scanner input) {
        int[][] octopuses = readOctopuses(input);
        int stepCount = 0;
        Set<Point> flashes = new HashSet<>();
        do {
            flashes.clear();
            stepCount++;
            performStep(octopuses, flashes);
        } while (flashes.size() != 100);

        return stepCount;
    }


    private void performStep(int[][] octopuses, Set<Point> flashes) {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                inc(octopuses, x, y, flashes);
            }
        }
        for (Point p : flashes) {
            octopuses[p.y][p.x] = 0;
        }
    }

    private int[][] readOctopuses(Scanner input) {
        int[][] octopuses = new int[10][10];
        for (int i = 0; i < 10; i++) {
            final String line = input.nextLine();
            for (int x = 0; x < 10; x++) {
                octopuses[i][x] = line.charAt(x) - '0';
            }
        }
        return octopuses;
    }

    private void inc(int[][] octopuses, int x, int y, Set<Point> flashes) {
        octopuses[y][x]++;
        if (octopuses[y][x] > 9) {
            octopuses[y][x] = 0;
            flashes.add(new Point(x, y));
            if (x > 0) {
                inc(octopuses, x - 1, y, flashes);
                if (y > 0) {
                    inc(octopuses, x - 1, y - 1, flashes);
                }
                if (y < 9) {
                    inc(octopuses, x - 1, y + 1, flashes);
                }
            }
            if (y > 0) {
                inc(octopuses, x, y - 1, flashes);
            }
            if (x < 9) {
                inc(octopuses, x + 1, y, flashes);
                if (y > 0) {
                    inc(octopuses, x + 1, y - 1, flashes);
                }
                if (y < 9) {
                    inc(octopuses, x + 1, y + 1, flashes);
                }
            }
            if (y < 9) {
                inc(octopuses, x, y + 1, flashes);
            }
        }
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2021/aoc2021day11.txt";
    }
}