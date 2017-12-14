package de.chrlembeck.aoc2017.day14;

import java.util.Scanner;

import de.chrlembeck.aoc2017.common.AbstractAocBase;
import de.chrlembeck.aoc2017.day10.Day10;

public class Day14 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Day14().run();
    }

    @Override
    public String part1(final Scanner input) {
        final String key = input.nextLine();
        final Day10 day10 = new Day10();
        int result = 0;
        for (int rowIndex = 0; rowIndex < 128; rowIndex++) {
            final String line = key + "-" + rowIndex;
            final int[] ascii = new int[line.length()];
            for (int j = 0; j < line.length(); j++) {
                ascii[j] = line.charAt(j);
            }
            result += countBits(day10.hash(ascii));
        }
        return Integer.toString(result);
    }

    private int countBits(final int... input) {
        int sum = 0;
        for (int value : input) {
            for (; value != 0; value >>>= 1) {
                if ((value & 1) != 0) {
                    sum++;
                }
            }
        }
        return sum;
    }

    @Override
    public String part2(final Scanner input) {
        final String key = input.nextLine();
        final Day10 day10 = new Day10();
        final int[][] field = new int[128][128];
        for (int rowIndex = 0; rowIndex < 128; rowIndex++) {
            final String line = key + "-" + rowIndex;
            final int[] ascii = new int[line.length()];
            for (int j = 0; j < line.length(); j++) {
                ascii[j] = line.charAt(j);
            }
            final int[] hash = day10.hash(ascii);
            final int[] row = field[rowIndex];
            for (int i = 0; i < 128; i++) {
                row[i] = -((hash[i / 8] >> (7 - (i % 8))) & 1); // set elements to 0 or -1
            }
        }
        int result = 0;
        for (int y = 0; y < 128; y++) {
            for (int x = 0; x < 128; x++) {
                if (field[y][x] == -1) {
                    floodFill(field, x, y, ++result);
                }
            }
        }
        return Integer.toString(result);
    }

    public void floodFill(final int[][] field, final int x, final int y, final int value) {
        final int current = field[y][x];
        field[y][x] = value;
        if (y > 0 && field[y - 1][x] == current) {
            floodFill(field, x, y - 1, value);
        }
        if (x > 0 && field[y][x - 1] == current) {
            floodFill(field, x - 1, y, value);
        }
        if (y < 127 && field[y + 1][x] == current) {
            floodFill(field, x, y + 1, value);
        }
        if (x < 127 && field[y][x + 1] == current) {
            floodFill(field, x + 1, y, value);
        }
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/day14.txt";
    }
}