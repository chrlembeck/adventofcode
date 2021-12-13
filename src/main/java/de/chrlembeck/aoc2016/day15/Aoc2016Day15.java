package de.chrlembeck.aoc2016.day15;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2016Day15 extends AbstractAocBase {

    private static final Pattern REGEX = Pattern.compile ("Disc #(\\d+) has (\\d+) positions; at time=0, it is at position (\\d+).");

    public static void main(final String[] args) {
        new Aoc2016Day15().run();
    }

    @Override
    public Long part1(final Scanner input) {
        int[] discSizes = new int[6];
        int[] discPositions = new int[6];
        readInput(input, discSizes, discPositions);
        return count(discSizes, discPositions);
    }

    @Override
    public Long part2(final Scanner input) {
        int[] discSizes = new int[7];
        int[] discPositions = new int[7];
        readInput(input, discSizes, discPositions);
        discSizes[6] = 11;
        discPositions[6] = (discPositions[5] + discSizes[6] - 1) % discSizes[6];
        return count(discSizes, discPositions);
    }

    private long count(int[] discSizes, int[] discPositions) {
        int[] readyPositions = new int[discSizes.length];
        calcReadyPositions(discSizes, readyPositions);

        long time = 0;
        while (!Arrays.equals(discPositions, readyPositions)) {
            time++;
            for (int i = 0; i < discSizes.length; i++) {
                discPositions[i] = (discPositions[i]+1) % discSizes[i];
            }
        }
        return time;
    }

    private void calcReadyPositions(int[] discSizes, int[] readyPositions) {
        for (int i = 0; i < readyPositions.length; i++) {
            readyPositions[i] = (discSizes[i]-i-1)% discSizes[i];
            while (readyPositions[i] < 0) {
                readyPositions[i]+= discSizes[i];
            }
        }
    }

    private void readInput(Scanner input, int[] discSizes, int[] discPositions) {
        while (input.hasNextLine()) {
            Matcher matcher = matchRegex(REGEX, input.nextLine());
            int index = Integer.parseInt(matcher.group(1))-1;
            discSizes[index] = Integer.parseInt(matcher.group(2));
            discPositions[index] = Integer.parseInt(matcher.group(3));
        }
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2016/aoc2016day15.txt";
    }
}