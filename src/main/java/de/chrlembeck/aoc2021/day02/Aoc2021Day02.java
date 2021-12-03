package de.chrlembeck.aoc2021.day02;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2021Day02 extends AbstractAocBase {

    public static final Pattern REGEX = Pattern.compile("(up|down|forward) (\\d+)");

    public static void main(final String[] args) {
        new Aoc2021Day02().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        int depth = 0;
        int pos = 0;
        while (input.hasNext()) {
            Matcher matcher = matchRegex(REGEX, input.nextLine());
            final String command = matcher.group(1);
            final int value = Integer.parseInt(matcher.group(2));
            switch (command) {
                case "up": depth -= value; break;
                case "down": depth += value; break;
                case "forward": pos += value; break;
            }
        }
        return pos * depth;
    }

    @Override
    public Integer part2(final Scanner input) {
        int depth = 0;
        int pos = 0;
        int aim = 0;
        while (input.hasNext()) {
            Matcher matcher = matchRegex(REGEX, input.nextLine());
            final String command = matcher.group(1);
            final int value = Integer.parseInt(matcher.group(2));
            switch (command) {
                case "up": aim -= value; break;
                case "down": aim += value; break;
                case "forward": pos += value; depth += aim * value; break;
            }
        }
        return pos * depth;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2021/aoc2021day02.txt";
    }
}