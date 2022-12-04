package de.chrlembeck.aoc2022.day04;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2022Day04 extends AbstractAocBase {

    private final static Pattern pattern = Pattern.compile("(\\d+)-(\\d+),(\\d+)-(\\d+)");

    public static void main(final String[] args) {
        new Aoc2022Day04().run();
    }

    @Override
    public Long part1(final Scanner input) {
        return tokenStream(input, "\n", pattern, this::matcherToGroup)
                .filter(Group::contains)
                .count();
    }

    @Override
    public Long part2(final Scanner input) {
        return tokenStream(input, "\n", pattern, this::matcherToGroup)
                .filter(Group::overlaps)
                .count();
    }

    private Group matcherToGroup(Matcher matcher) {
        return new Group(
                new Intervall(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))),
                new Intervall(Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4))));
    }

    private record Group(Intervall first, Intervall second) {
        boolean contains() {
            return first.contains(second) || second().contains(first);
        }

        boolean overlaps() {
            return first.overlaps(second);
        }
    }

    private record Intervall(int left, int right) {

        public boolean contains(Intervall other) {
            return left <= other.left && right >= other.right;
        }

        public boolean overlaps(Intervall other) {
            return left <= other.right && right >= other.left;
        }
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2022/aoc2022day04.txt";
    }
}