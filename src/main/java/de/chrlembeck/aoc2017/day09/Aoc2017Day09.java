package de.chrlembeck.aoc2017.day09;

import java.util.Scanner;

import de.chrlembeck.aoccommon.AbstractAocBase;
import de.chrlembeck.aoccommon.Result;

public class Aoc2017Day09 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2017Day09().run();
    }

    @Override
    public String part1(final Scanner input) {
        return calc(input).getPart1().toString();
    }

    @Override
    public String part2(final Scanner input) {
        return calc(input).getPart2().toString();
    }

    public Result<Integer> calc(final Scanner input) {
        final String line = input.nextLine();
        int garbage = 0;
        boolean garbageMode = false;
        boolean ignoreNext = false;
        int depth = 0;
        int score = 0;
        for (int idx = 0; idx < line.length(); idx++) {
            final char next = line.charAt(idx);
            if (ignoreNext) {
                ignoreNext = false;
                continue;
            }
            if (garbageMode) {
                switch (next) {
                    case '!':
                        ignoreNext = true;
                        break;
                    case '>':
                        garbageMode = false;
                        break;
                    default:
                        garbage++;
                        break;
                }
                continue;
            }
            switch (next) {
                case '{':
                    depth++;
                    break;
                case '}':
                    score += depth;
                    depth--;
                    break;
                case ',':
                    break;
                case '<':
                    garbageMode = true;
                    break;
                case '>':
                    throw new IllegalStateException();
                case '!':
                    ignoreNext = true;
                    break;
                default:
                    if (!Character.isLetter(next)) {
                        throw new IllegalStateException(Character.toString(next));
                    }
            }
        }
        return new Result<>(score, garbage);
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2017/aoc2017day09.txt";
    }
}