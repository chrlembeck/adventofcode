package de.chrlembeck.aoc2016.day02;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.awt.*;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class Aoc2016Day02 extends AbstractAocBase {

    public final static char[][] PAD = {
            { ' ', ' ', '1', ' ', ' ' },
            { ' ', '2', '3', '4', ' ' },
            { '5', '6', '7', '8', '9' },
            { ' ', 'A', 'B', 'C', ' ' },
            { ' ', ' ', 'D', ' ', ' ' }, };

    public static void main(final String[] args) {
        new Aoc2016Day02().run();
    }

    @Override
    public Object part1(final Scanner input) {
        return decode(input, new Point(1, 1), this::step1decoder, pos -> 1 + pos.y * 3 + pos.x);
    }

    @Override
    public Object part2(final Scanner input) {
        return decode(input, new Point(2,2), this::step2decoder, pos -> PAD[pos.y][pos.x]);
    }

    public Object decode(final Scanner input, final Point pos, final BiConsumer<Point, Character> decoder, final Function<Point, Object> patternDecoder){
        final StringBuilder result = new StringBuilder();
        while (input.hasNextLine()) {
            final String pattern = input.nextLine();
            for (final char token: pattern.toCharArray()) {
                decoder.accept(pos, token);
            }
            result.append(patternDecoder.apply(pos));
        }
        return result.toString();
    }

    public void step1decoder(final Point pos, final char token) {
        switch (token) {
            case 'U':
                pos.y = Math.max(pos.y - 1, 0);
                break;
            case 'D':
                pos.y = Math.min(pos.y + 1, 2);
                break;
            case 'L':
                pos.x = Math.max(pos.x - 1, 0);
                break;
            case 'R':
                pos.x = Math.min(pos.x + 1, 2);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public void step2decoder(final Point pos, final char token) {
        switch (token) {
            case 'U':
                pos.y = Math.max(pos.y - 1, Math.abs(2 - pos.x));
                break;
            case 'D':
                pos.y = Math.min(pos.y + 1, 4 - Math.abs(2 - pos.x));
                break;
            case 'L':
                pos.x = Math.max(pos.x - 1, Math.abs(2 - pos.y));
                break;
            case 'R':
                pos.x = Math.min(pos.x + 1, 4 - Math.abs(2 - pos.y));
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2016/aoc2016day02.txt";
    }
}