package de.chrlembeck.aoc2017.day10;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import de.chrlembeck.aoccommon.AbstractAocBase;

public class Aoc2017Day10 extends AbstractAocBase {

    private int length = 256;

    public static void main(final String[] args) {
        new Aoc2017Day10().run();
    }

    public void setLength(final int length) {
        this.length = length;
    }

    @Override
    public String part1(final Scanner input) {
        input.useDelimiter(",");
        final List<Integer> lengths = new ArrayList<>();
        while (input.hasNextInt()) {
            lengths.add(Integer.valueOf(input.nextInt()));
        }
        int[] array = new int[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        int pos = 0;
        for (int skip = 0; skip < lengths.size(); skip++) {
            final int length = lengths.get(skip);
            array = rotate(array, pos, length);
            pos = (pos + skip + length) % array.length;
        }

        return Integer.toString(array[0] * array[1]);
    }

    private int[] rotate(final int[] array, final int pos, final int length) {
        final int[] newArray = array.clone();
        for (int i = 0; i < length; i++) {
            newArray[(i + pos) % array.length] = array[(pos + length - 1 - i) % array.length];
        }
        return newArray;
    }

    @Override
    public String part2(final Scanner input) {
        final String line = input.hasNextLine() ? input.nextLine() : "";
        final int[] lengths = new int[line.length()];
        for (int i = 0; i < line.length(); i++) {
            lengths[i] = line.charAt(i);
        }

        final int[] knotHash = hash(lengths);

        final StringBuilder output = new StringBuilder();
        for (final int value : knotHash) {
            output.append(Integer.toHexString((value & 0xf0) >> 4));
            output.append(Integer.toHexString(value & 0xf));
        }

        return output.toString();
    }

    public int[] hash(final int... input) {
        int[] array = new int[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        final int[] base = new int[input.length + 5];
        System.arraycopy(input, 0, base, 0, input.length);
        System.arraycopy(new int[] { 17, 31, 73, 47, 23 }, 0, base, input.length, 5);

        int pos = 0;
        int skip = 0;
        for (int i = 0; i < 64; i++) {
            for (final int value : base) {
                array = rotate(array, pos, value);
                pos = (pos + skip + value) % array.length;
                skip++;
            }
        }

        final int[] dense = new int[16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                dense[i] = dense[i] ^ array[16 * i + j];
            }
        }
        return dense;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2017/day10.txt";
    }
}