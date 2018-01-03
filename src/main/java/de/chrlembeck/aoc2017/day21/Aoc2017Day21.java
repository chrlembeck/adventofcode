package de.chrlembeck.aoc2017.day21;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import de.chrlembeck.aoccommon.AbstractAocBase;

public class Aoc2017Day21 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2017Day21().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        return calc(input, 5);
    }

    @Override
    public Integer part2(final Scanner input) {
        return calc(input, 18);
    }

    private int calc(final Scanner input, final int iterations) {
        // contains the transformations from a 2x2 matrix to a 3x3 matrix
        final int[] twoToThreeMap = new int[1 << 4];
        // contains the transformations from a 3x3 matrix to four 3x3 matrixes
        final int[][] threeToTwoMap = new int[1 << 9][];
        // contains the transformations from a 3x3 matrix to nine 2x2 matrixes (performs two steps at once)
        final int[][] threeToTwoMapStep2 = new int[1 << 9][];
        while (input.hasNextLine()) {
            final String line = input.nextLine();
            if (line.length() == 20) {
                readSmallToLarge(line, twoToThreeMap);
            } else {
                readLargeToSmall(line, threeToTwoMap);
            }
        }
        for (int i = 0; i < threeToTwoMap.length; i++) {
            final int[] step1 = new int[4];
            for (int j = 0; j < 4; j++) {
                step1[j] = twoToThreeMap[threeToTwoMap[i][j]];
            }
            threeToTwoMapStep2[i] = splitFour3x3ToNine2x2(step1);
        }
        final int start = 0b010_001_111;
        List<Integer> grid = Arrays.asList(start);
        int width = 3;
        List<Integer> gridCopy = null;
        for (int i = 0; i < iterations; i++) {
            final List<Integer> newGrid = new ArrayList<Integer>();
            if (width % 4 == 0) {
                width = width / 2 * 3;
                for (final Integer value : gridCopy) {

                    for (final int v : threeToTwoMapStep2[value]) {
                        newGrid.add(v);
                    }
                }
            } else if (width % 2 == 0) {
                width = width / 2 * 3;
                for (final Integer value : grid) {
                    newGrid.add(twoToThreeMap[value]);
                }
            } else {
                gridCopy = grid;
                width = width / 3 * 4;
                for (final Integer value : grid) {
                    for (final int v : threeToTwoMap[value]) {
                        newGrid.add(v);
                    }
                }
            }
            grid = newGrid;
        }
        int sum = 0;
        for (final Integer value : grid) {
            sum += Integer.bitCount(value);
        }
        return sum;
    }

    private int[] splitFour3x3ToNine2x2(final int[] field) {
        final int[] result = new int[9];
        result[0] = moveBit(field[0], 4, 0) | moveBit(field[0], 5, 1) | moveBit(field[0], 7, 2)
                | moveBit(field[0], 8, 3);
        result[1] = moveBit(field[1], 5, 0) | moveBit(field[0], 3, 1) | moveBit(field[1], 8, 2)
                | moveBit(field[0], 6, 3);
        result[2] = moveBit(field[1], 3, 0) | moveBit(field[1], 4, 1) | moveBit(field[1], 6, 2)
                | moveBit(field[1], 7, 3);
        result[3] = moveBit(field[2], 7, 0) | moveBit(field[2], 8, 1) | moveBit(field[0], 1, 2)
                | moveBit(field[0], 2, 3);
        result[4] = moveBit(field[3], 8, 0) | moveBit(field[2], 6, 1) | moveBit(field[1], 2, 2)
                | moveBit(field[0], 0, 3);
        result[5] = moveBit(field[3], 6, 0) | moveBit(field[3], 7, 1) | moveBit(field[1], 0, 2)
                | moveBit(field[1], 1, 3);
        result[6] = moveBit(field[2], 1, 0) | moveBit(field[2], 2, 1) | moveBit(field[2], 4, 2)
                | moveBit(field[2], 5, 3);
        result[7] = moveBit(field[3], 2, 0) | moveBit(field[2], 0, 1) | moveBit(field[3], 5, 2)
                | moveBit(field[2], 3, 3);
        result[8] = moveBit(field[3], 0, 0) | moveBit(field[3], 1, 1) | moveBit(field[3], 3, 2)
                | moveBit(field[3], 4, 3);
        return result;
    }

    private static int moveBit(final int input, final int source, final int dest) {
        return (source <= dest) ? (input & (1 << source)) << (dest - source)
                : (input & (1 << source)) >> (source - dest);
    }

    private static void readLargeToSmall(final String line, final int[][] threeToTwoMap) {
        final int input = (line.charAt(0) == '#' ? 0b100_000_000 : 0) | (line.charAt(1) == '#' ? 0b010_000_000 : 0)
                | (line.charAt(2) == '#' ? 0b001_000_000 : 0) | (line.charAt(4) == '#' ? 0b000_100_000 : 0)
                | (line.charAt(5) == '#' ? 0b000_010_000 : 0) | (line.charAt(6) == '#' ? 0b000_001_000 : 0)
                | (line.charAt(8) == '#' ? 0b000_000_100 : 0) | (line.charAt(9) == '#' ? 0b000_000_010 : 0)
                | (line.charAt(10) == '#' ? 0b000_000_001 : 0);
        final LargeField inputField = new LargeField(input);
        final int[] output = new int[4];
        output[0] = (line.charAt(15) == '#' ? 0b1000 : 0) | (line.charAt(16) == '#' ? 0b0100 : 0)
                | (line.charAt(20) == '#' ? 0b0010 : 0) | (line.charAt(21) == '#' ? 0b0001 : 0);
        output[1] = (line.charAt(17) == '#' ? 0b1000 : 0) | (line.charAt(18) == '#' ? 0b0100 : 0)
                | (line.charAt(22) == '#' ? 0b0010 : 0) | (line.charAt(23) == '#' ? 0b0001 : 0);
        output[2] = (line.charAt(25) == '#' ? 0b1000 : 0) | (line.charAt(26) == '#' ? 0b0100 : 0)
                | (line.charAt(30) == '#' ? 0b0010 : 0) | (line.charAt(31) == '#' ? 0b0001 : 0);
        output[3] = (line.charAt(27) == '#' ? 0b1000 : 0) | (line.charAt(28) == '#' ? 0b0100 : 0)
                | (line.charAt(32) == '#' ? 0b0010 : 0) | (line.charAt(33) == '#' ? 0b0001 : 0);
        for (final AbstractField variant : inputField.variants()) {
            threeToTwoMap[variant.getState()] = output;
        }
    }

    private void readSmallToLarge(final String line, final int[] smallToLargeMap) {
        final int input = (line.charAt(0) == '#' ? 0b1000 : 0) | (line.charAt(1) == '#' ? 0b0100 : 0)
                | (line.charAt(3) == '#' ? 0b0010 : 0) | (line.charAt(4) == '#' ? 0b0001 : 0);
        final int output = (line.charAt(9) == '#' ? 0b100_000_000 : 0) | (line.charAt(10) == '#' ? 0b010_000_000 : 0)
                | (line.charAt(11) == '#' ? 0b001_000_000 : 0) | (line.charAt(13) == '#' ? 0b000_100_000 : 0)
                | (line.charAt(14) == '#' ? 0b000_010_000 : 0) | (line.charAt(15) == '#' ? 0b000_001_000 : 0)
                | (line.charAt(17) == '#' ? 0b000_000_100 : 0) | (line.charAt(18) == '#' ? 0b000_000_010 : 0)
                | (line.charAt(19) == '#' ? 0b000_000_001 : 0);
        final SmallField inputState = new SmallField(input);
        for (final AbstractField variant : inputState.variants()) {
            smallToLargeMap[variant.getState()] = output;
        }
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2017/aoc2017day21.txt";
    }
}