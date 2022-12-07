package de.chrlembeck.aoc2022.day06;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.util.Scanner;

public class Aoc2022Day06 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2022Day06().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        String signal = input.nextLine();
        for (int i = 3; i < signal.length(); i++) {
            char a = signal.charAt(i - 3);
            char b = signal.charAt(i - 2);
            char c = signal.charAt(i - 1);
            char d = signal.charAt(i);
            if (a != b && a != c && a != d && b != c && b != d && c != d) {
                return i + 1;
            }
        }
        return null;
    }

    @Override
    public Integer part2(final Scanner input) {
        final String signal = input.nextLine();
        final int[] hist = new int['z' - 'a' + 1];
        final int length = 14;
        loop:
        for (int i = 0; i < signal.length(); i++) {
            hist[signal.charAt(i) - 'a']++;
            if (i >= length) {
                hist[signal.charAt(i - length) - 'a']--;
            }
            if (i >= length - 1) {
                for (int counter : hist) {
                    if (counter > 1) {
                        continue loop;
                    }
                }
                return i + 1;
            }
        }
        return null;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2022/aoc2022day06.txt";
    }
}