package de.chrlembeck.aoc2021.day08;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2021Day08 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2021Day08().run();
    }

    @Override
    public Object part1(final Scanner input) {
        final Pattern regex = Pattern.compile("([\\w ]+)\\| (\\w+) (\\w+) (\\w+) (\\w+)");
        int counter = 0;
        while (input.hasNext()) {
            final Matcher matcher = matchRegex(regex, input.nextLine());
            for (int i = 2; i <= 5; i++) {
                final String digit = matcher.group(i);
                if (digit.length() <= 4 || digit.length() == 7) {
                    counter++;
                }
            }
        }
        return counter;
    }

    @Override
    public Object part2(final Scanner input) {
        final Pattern regex = Pattern.compile("(\\w+) (\\w+) (\\w+) (\\w+) (\\w+) (\\w+) (\\w+) (\\w+) (\\w+) (\\w+) \\| (\\w+) (\\w+) (\\w+) (\\w+)");
        long sum = 0;
        while (input.hasNext()) {
            final Matcher matcher = matchRegex(regex, input.nextLine());
            sum = sum + new DisplayCode(matcher).getOutputAsInt();
        }
        return sum;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2021/aoc2021day08.txt";
    }
}