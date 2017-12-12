package de.chrlembeck.aoc2017.day10;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import de.chrlembeck.aoc2017.common.AbstractAocBase;

public class Day10 extends AbstractAocBase {

    private int length = 256;

    public static void main(final String[] args) {
        new Day10().run();
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
        final int[] lengths = new int[line.length() + 5];
        for (int i = 0; i < line.length(); i++) {
            lengths[i] = line.charAt(i);
        }
        lengths[line.length()] = 17;
        lengths[line.length() + 1] = 31;
        lengths[line.length() + 2] = 73;
        lengths[line.length() + 3] = 47;
        lengths[line.length() + 4] = 23;

        int[] array = new int[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        int pos = 0;
        int skip = 0;
        for (int i = 0; i < 64; i++) {
            for (final int length : lengths) {
                array = rotate(array, pos, length);
                pos = (pos + skip + length) % array.length;
                skip++;
            }
        }

        final int[] dense = new int[16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                dense[i] = dense[i] ^ array[16 * i + j];
            }
        }

        final StringBuilder output = new StringBuilder();
        for (final int value : dense) {
            output.append(Integer.toHexString((value & 0xf0) >> 4));
            output.append(Integer.toHexString(value & 0xf));
        }

        return output.toString();
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/day10.txt";
    }
}