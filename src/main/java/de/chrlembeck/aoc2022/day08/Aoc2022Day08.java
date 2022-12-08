package de.chrlembeck.aoc2022.day08;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.util.Scanner;

import static java.lang.Math.max;

public class Aoc2022Day08 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2022Day08().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        final int[][] trees = input.useDelimiter("\\n").tokens().map(this::readLine).toArray(int[][]::new);
        final boolean[][] visible = new boolean[trees[0].length][trees.length];
        int counter = 0;
        for (int y = 0; y < trees.length; y++) {
            final int[] row = trees[y];
            int maxL = -1;
            int maxR = -1;
            for (int x = 0; x < row.length; x++) {
                if (row[x] > maxL) {
                    maxL = row[x];
                    if (!visible[y][x]) {
                        visible[y][x] = true;
                        counter++;
                    }
                }
                if (row[row.length - x - 1] > maxR) {
                    maxR = row[row.length - x - 1];
                    if (!visible[y][row.length - x - 1]) {
                        visible[y][row.length - x - 1] = true;
                        counter++;
                    }
                }
            }
        }
        for (int x = 0; x < trees[0].length; x++) {
            int maxL = -1;
            int maxR = -1;
            for (int y = 0; y < trees.length; y++) {
                if (trees[y][x] > maxL) {
                    maxL = trees[y][x];
                    if (!visible[y][x]) {
                        visible[y][x] = true;
                        counter++;
                    }
                }
                if (trees[trees[y].length - y - 1][x] > maxR) {
                    maxR = trees[trees[y].length - y - 1][x];
                    if (!visible[trees[y].length - y - 1][x]) {
                        visible[trees[y].length - y - 1][x] = true;
                        counter++;
                    }
                }
            }
        }
        return counter;
    }

    @Override
    public Integer part2(final Scanner input) {
        final int[][] trees = input.useDelimiter("\\n").tokens().map(this::readLine).toArray(int[][]::new);
        int best = 0;
        for (int y = 1; y < trees.length - 1; y++) {
            for (int x = 1; x < trees[y].length - 1; x++) {
                int max = 0;
                int counter = 0;
                final int height = trees[y][x];
                for (int i = x - 1; i >= 0 && max < height; i--) {
                    counter++;
                    max = trees[y][i];
                }
                int visible = counter;
                max = 0;
                counter = 0;
                for (int i = x + 1; i < trees[y].length && max < height; i++) {
                    counter++;
                    max = trees[y][i];
                }
                visible *= counter;
                max = 0;
                counter = 0;
                for (int i = y - 1; i >= 0 && max < height; i--) {
                    counter++;
                    max = trees[i][x];
                }
                visible *= counter;
                max = 0;
                counter = 0;
                for (int i = y + 1; i < trees.length && max < height; i++) {
                    counter++;
                    max = trees[i][x];
                }
                best = max(best, visible*counter);
            }
        }
        return best;
    }

    public int[] readLine(String line) {
        int[] result = new int[line.length()];
        for (int i = 0; i < line.length(); i++) {
            result[i] = line.charAt(i) - '0';
        }
        return result;
    }


    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2022/aoc2022day08.txt";
    }
}