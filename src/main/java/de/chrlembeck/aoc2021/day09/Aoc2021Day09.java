package de.chrlembeck.aoc2021.day09;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Aoc2021Day09 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2021Day09().run();
    }

    @Override
    public Object part1(final Scanner input) {
        final char[][] field = input.tokens().map(String::toCharArray).toArray(char[][]::new);
        int risk = 0;
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[0].length; x++) {
                final char current = field[y][x];
                if ((y == 0 || field[y - 1][x] > current) && (x == 0 || field[y][x - 1] > current) &&
                        (y == field.length - 1 || field[y + 1][x] > current) && (x == field[0].length - 1 || field[y][x + 1] > current)) {
                    risk += current - '0' + 1;
                }
            }
        }
        return risk;
    }

    @Override
    public Object part2(final Scanner input) {
        final char[][] field = input.tokens().map(String::toCharArray).toArray(char[][]::new);
        final List<Integer> basinSizes = new ArrayList<>();
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[0].length; x++) {
                final char current = field[y][x];
                if ((y == 0 || field[y - 1][x] > current) && (x == 0 || field[y][x - 1] > current) &&
                        (y == field.length - 1 || field[y + 1][x] > current) && (x == field[0].length - 1 || field[y][x + 1] > current)) {
                    basinSizes.add(calcBasinSize(field, x, y));
                }
            }
        }
        Collections.sort(basinSizes);
        return basinSizes.get(basinSizes.size() - 1) * basinSizes.get(basinSizes.size() - 2) * basinSizes.get(basinSizes.size() - 3);
    }

    private int calcBasinSize(final char[][] field, final int xPos, final int yPos) {
        if (xPos < 0 || yPos < 0 || xPos >= field[0].length || yPos >= field.length || field[yPos][xPos] == '9') {
            return 0;
        }
        field[yPos][xPos] = '9';
        return 1 + calcBasinSize(field, xPos + 1, yPos) + calcBasinSize(field, xPos - 1, yPos) + calcBasinSize(field, xPos, yPos + 1) + calcBasinSize(field, xPos, yPos - 1);
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2021/aoc2021day09.txt";
    }
}