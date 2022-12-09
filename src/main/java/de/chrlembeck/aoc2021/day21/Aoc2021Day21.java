package de.chrlembeck.aoc2021.day21;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class Aoc2021Day21 extends AbstractAocBase {

    private final Pattern regex = Pattern.compile("Player ([12]) starting position: (\\d+)");

    public static void main(final String[] args) {
        new Aoc2021Day21().run();
    }

    @Override
    public Object part1(final Scanner input) {
        Player[] players = parsePlayers(input);
        int dice = 1;
        int rounds = 0;
        while (players[0].score < 1000 && players[1].score < 1000) {
            int random = dice + (dice) % 100 + 1 + (dice + 1) % 100 + 1;
            players[rounds % 2] = players[rounds % 2].move(random);
            rounds++;
            dice = (dice + 2) % 100 + 1;
        }
        return (players[0].score >= 1000 ? players[1].score : players[0].score) * rounds * 3;
    }

    @Override
    public Object part2(final Scanner input) {
        Player[] players = parsePlayers(input);
        Stats stats = new Universe(1, 1, players[0], players[1]).calcStats();
        return stats.max();
    }

    private Player[] parsePlayers(Scanner input) {
        Player[] players = new Player[2];
        for (int i = 0; i < 2; i++) {
            Matcher matcher = matchRegex(regex, input.nextLine());
            players[parseInt(matcher.group(1)) - 1] = new Player(parseInt(matcher.group(2)), 0);
        }
        return players;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2021/aoc2021day21.txt";
    }

    record Player(int pos, int score) {

        public Player move(int steps) {
            int newPos = (pos + steps - 1) % 10 + 1;
            return new Player(newPos, score + newPos);
        }
    }

    record Universe(int round, long factor, Player player1, Player player2) {

        public Stats calcStats() {
            if (player1.score >= 21) {
                return new Stats(factor, 0);
            } else if (player2.score >= 21) {
                return new Stats(0, factor);
            } else if (round % 2 == 1) {
                // player 1 turn
                Stats sum = new Stats(0, 0);
                sum = sum.add(new Universe(round + 1, factor * 1, player1.move(3), player2).calcStats());
                sum = sum.add(new Universe(round + 1, factor * 3, player1.move(4), player2).calcStats());
                sum = sum.add(new Universe(round + 1, factor * 6, player1.move(5), player2).calcStats());
                sum = sum.add(new Universe(round + 1, factor * 7, player1.move(6), player2).calcStats());
                sum = sum.add(new Universe(round + 1, factor * 6, player1.move(7), player2).calcStats());
                sum = sum.add(new Universe(round + 1, factor * 3, player1.move(8), player2).calcStats());
                sum = sum.add(new Universe(round + 1, factor * 1, player1.move(9), player2).calcStats());
                return sum;
            } else {
                Stats sum = new Stats(0, 0);
                sum = sum.add(new Universe(round + 1, factor * 1, player1, player2.move(3)).calcStats());
                sum = sum.add(new Universe(round + 1, factor * 3, player1, player2.move(4)).calcStats());
                sum = sum.add(new Universe(round + 1, factor * 6, player1, player2.move(5)).calcStats());
                sum = sum.add(new Universe(round + 1, factor * 7, player1, player2.move(6)).calcStats());
                sum = sum.add(new Universe(round + 1, factor * 6, player1, player2.move(7)).calcStats());
                sum = sum.add(new Universe(round + 1, factor * 3, player1, player2.move(8)).calcStats());
                sum = sum.add(new Universe(round + 1, factor * 1, player1, player2.move(9)).calcStats());
                return sum;
            }
        }
    }

    record Stats(long p1Wins, long p2Wins) {

        public Stats add(Stats addend) {
            return new Stats(p1Wins + addend.p1Wins, p2Wins + addend.p2Wins);
        }

        public long max() {
            return Math.max(p1Wins, p2Wins);
        }
    }
}