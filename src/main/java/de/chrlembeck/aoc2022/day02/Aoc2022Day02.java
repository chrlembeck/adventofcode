package de.chrlembeck.aoc2022.day02;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.util.Scanner;

public class Aoc2022Day02 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2022Day02().run();
    }

    @Override
    public Long part1(final Scanner input) {
        long sum = 0;
        while (input.hasNextLine()) {
            String line = input.nextLine();
            Shape opponent = Shape.byValue(line.charAt(0));
            Shape me = Shape.byValue(line.charAt(2));
            sum += score1(opponent, me);
        }
        return sum;
    }

    @Override
    public Long part2(final Scanner input) {
        long sum = 0;
        while (input.hasNextLine()) {
            String line = input.nextLine();
            Shape opponent = Shape.byValue(line.charAt(0));
            Strategy me = Strategy.byValue(line.charAt(2));
            sum += score2(opponent, me);
        }
        return sum;
    }


    private long score1(Shape opponent, Shape me) {
        if (me.beats() == opponent) {
            return me.baseScore() + 6;
        } else if (me.beatenBy() == opponent) {
            return me.baseScore();
        } else {
            return me.baseScore() + 3;
        }
    }

    private long score2(Shape opponent, Strategy me) {
        return switch (me) {
            case LOSE -> opponent.beats().baseScore();
            case DRAW -> opponent.baseScore() + 3;
            case WIN -> opponent.beatenBy().baseScore() + 6;
        };
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2022/aoc2022day02.txt";
    }

    private enum Strategy {
        LOSE, DRAW, WIN;

        public static Strategy byValue(char value) {
            return switch (value) {
                case 'X' -> LOSE;
                case 'Y' -> DRAW;
                case 'Z' -> WIN;
                default -> throw new IllegalArgumentException();
            };
        }

    }

    private enum Shape {
        ROCK, PAPER, SCISSOR;

        public Shape beats() {
            return switch (this) {
                case ROCK -> SCISSOR;
                case PAPER -> ROCK;
                case SCISSOR -> PAPER;
            };
        }

        public Shape beatenBy() {
            return switch (this) {
                case ROCK -> PAPER;
                case PAPER -> SCISSOR;
                case SCISSOR -> ROCK;
            };
        }

        public static Shape byValue(char value) {
            return switch (value) {
                case 'A', 'X' -> ROCK;
                case 'B', 'Y' -> PAPER;
                case 'C', 'Z' -> SCISSOR;
                default -> throw new IllegalArgumentException();
            };
        }

        public int baseScore() {
            return switch (this) {
                case ROCK -> 1;
                case PAPER -> 2;
                case SCISSOR -> 3;
            };
        }
    }
}