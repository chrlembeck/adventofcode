package de.chrlembeck.aoc2019.day16;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Scanner;

public class Aoc2019Day16 extends AbstractAocBase {

    private static final int[] MASK = { 0, 1, 0, -1 };

    public static void main(final String[] args) {
        new Aoc2019Day16().run();
    }

    @Override
    public Object part1(final Scanner input) {
        final String line = input.nextLine();
        int[] digits = toArray(line);
        for (int i = 0; i < 100; i++) {
            digits = fft(digits);
        }
        return first8ToString(digits);
    }

    @Override
    public Object part2(final Scanner input) {
        final String line = input.nextLine();
        final int offset = Integer.parseInt(line.substring(0, 7));
        final int[] digits = toArray(line);
        int[] tail = new int[digits.length * 10_000 - offset];
        System.arraycopy(digits, offset % digits.length, tail, 0, digits.length - offset % digits.length);
        for (int i = 1; i <= tail.length / digits.length; i++) {
            System.arraycopy(digits, 0, tail, tail.length - i * digits.length, digits.length);
        }
        for (int i = 0; i < 100; i++) {
            tail = fft2(tail);
        }
        return first8ToString(tail);
    }

    private int[] toArray(final String line) {
        final int[] array = new int[line.length()];
        for (int i = line.length() - 1; i >= 0; i--) {
            array[i] = line.charAt(i) - '0';
        }
        return array;
    }

    private String first8ToString(final int[] array) {
        final StringBuilder output = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            output.append(array[i]);
        }
        return output.toString();
    }

    private int[] fft(final int[] digits) {
        final int[] result = new int[digits.length];
        for (int round = 0; round < digits.length; round++) {
            int digit = 0;
            for (int i = 0; i < digits.length; i++) {
                digit = digit + digits[i] * MASK[(i + 1) / (round + 1) % 4];
            }
            result[round] = Math.abs(digit) % 10;
        }
        return result;
    }

    private int[] fft2(final int[] digits) {
        final int[] result = new int[digits.length];
        int sum = 0;
        for (int round = digits.length - 1; round >= 0; round--) {
            sum = (sum + digits[round]) % 10;
            result[round] = sum;
        }
        return result;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2019/aoc2019day16.txt";
    }
}