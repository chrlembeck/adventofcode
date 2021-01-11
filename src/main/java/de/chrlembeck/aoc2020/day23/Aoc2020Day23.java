package de.chrlembeck.aoc2020.day23;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.math.BigInteger;
import java.util.Scanner;

public class Aoc2020Day23 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2020Day23().run();
    }

    @Override
    public Object part1(final Scanner input) {
        final String line = input.nextLine();
        final int[] nextArray = new int[line.length() + 1];
        int start = line.charAt(0) - '0';
        for (int i = 0; i < line.length(); i++) {
            nextArray[line.charAt(i) - '0'] = line.charAt((i + 1) % line.length()) - '0';
        }
        for (int i = 0; i < 100; i++) {
            move(nextArray, start);
            start = nextArray[start];
        }
        final StringBuilder result = new StringBuilder();
        start = nextArray[1];
        while (start != 1) {
            result.append(start);
            start = nextArray[start];
        }
        return result.toString();
    }

    @Override
    public Object part2(final Scanner input) {
        final String line = input.nextLine();
        final int[] nextArray = new int[1_000_000 + 1];
        int start = line.charAt(0) - '0';
        for (int i = 0; i < line.length(); i++) {
            nextArray[line.charAt(i) - '0'] = line.charAt((i + 1) % line.length()) - '0';
        }
        nextArray[nextArray.length - 1] = nextArray[line.charAt(line.length() - 1) - '0'];
        nextArray[line.charAt(line.length() - 1) - '0'] = line.length() + 1;
        for (int i = line.length() + 1; i < nextArray.length - 1; i++) {
            nextArray[i] = i + 1;
        }
        for (int i = 0; i < 10_000_000; i++) {
            move(nextArray, start);
            start = nextArray[start];
        }
        return BigInteger.valueOf(nextArray[1]).multiply(BigInteger.valueOf(nextArray[nextArray[1]]));
    }

    private void move(final int[] nextArray, final int current) {
        final int length = nextArray.length - 1;
        final int delta = length - 2;
        final int three = nextArray[current];
        nextArray[current] = nextArray[nextArray[nextArray[nextArray[current]]]];
        int destination = (current + delta) % (length) + 1;
        while (destination == three || destination == nextArray[three] || destination == nextArray[nextArray[three]]) {
            destination = (destination + delta) % (length) + 1;
        }
        nextArray[nextArray[nextArray[three]]] = nextArray[destination];
        nextArray[destination] = three;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2020/aoc2020day23.txt";
    }
}