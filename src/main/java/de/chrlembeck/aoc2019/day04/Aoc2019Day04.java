package de.chrlembeck.aoc2019.day04;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.util.Scanner;
import java.util.function.Predicate;

public class Aoc2019Day04 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2019Day04().run();
    }

    @Override
    public Object part1(final Scanner input) {
        return execute(input, this::isValid1);
    }

    @Override
    public Object part2(final Scanner input) {
        return execute(input, this::isValid2);
    }

    private int execute(final Scanner input, final Predicate<String> validCheck) {
        final String line = input.nextLine();
        final int start = Integer.valueOf(line.substring(0,6));
        final int end = Integer.valueOf(line.substring(7,13));
        int result = 0;
        for (int i = start; i <= end; i++) {
            if (validCheck.test(Integer.toString(i))) {
                result++;
            }
        }
        return result;
    }

    public boolean isValid1(final String password) {
        if (password.length() != 6) {
            return false;
        }
        char last = password.charAt(0);
        boolean pair = false;
        for (int i = 1; i < password.length(); i++) {
            final char next = password.charAt(i);
            if (next == last) {
                pair = true;
            }
            if (next < last) {
                return false;
            }
            last = next;
        }
        return pair;
    }

    public boolean isValid2(final String password) {
        if (password.length() != 6) {
            return false;
        }
        char last = password.charAt(0);
        boolean pair = false;
        int length  = 1;
        for (int i = 1; i < password.length(); i++) {
            final char next = password.charAt(i);
            if (next == last) {
                length++;
            } else {
                if (length == 2) {
                    pair = true;
                }
                length = 1;
            }
            if (next < last) {
                return false;
            }
            last = next;
        }
        return pair || length == 2;
    }
    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2019/aoc2019day04.txt";
    }
}