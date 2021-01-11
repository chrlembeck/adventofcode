package de.chrlembeck.aoc2020.day22;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.*;
import java.util.stream.IntStream;

public class Aoc2020Day22 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2020Day22().run();
    }

    @Override
    public Object part1(final Scanner input) {
        return calc(input, false);
    }

    @Override
    public Object part2(final Scanner input) {
        return calc(input, true);
    }

    private long calcResult(final Queue<Integer> winner) {
        int factor = winner.size();
        long result = 0;
        while (!winner.isEmpty()) {
            result += factor-- * winner.remove();
        }
        return result;
    }

    public boolean whoWins(final Queue<Integer> queue1, final Queue<Integer> queue2, final boolean recursive) {
        final Set<String> hist1 = new TreeSet<>();
        final Set<String> hist2 = new TreeSet<>();
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            if (!hist1.add(queue1.toString()) || !hist2.add(queue2.toString())) {
                return true;
            }
            final Integer cardPlayer1 = queue1.remove();
            final Integer cardPlayer2 = queue2.remove();
            final boolean p1wins;
            if (recursive && cardPlayer1 <= queue1.size() && cardPlayer2 <= queue2.size()) {
                final LinkedList<Integer> queue1copy = new LinkedList(queue1);
                final LinkedList<Integer> queue2copy = new LinkedList(queue2);
                while (queue1copy.size() > cardPlayer1) {
                    queue1copy.removeLast();
                }
                while (queue2copy.size() > cardPlayer2) {
                    queue2copy.removeLast();
                }
                p1wins = whoWins(queue1copy, queue2copy, true);
            } else {
                p1wins = cardPlayer1 > cardPlayer2;
            }
            if (p1wins) {
                queue1.add(cardPlayer1);
                queue1.add(cardPlayer2);
            } else {
                queue2.add(cardPlayer2);
                queue2.add(cardPlayer1);
            }
        }
        return queue2.isEmpty();
    }

    public long calc(final Scanner input, final boolean recursive) {
        input.nextLine();
        final Queue<Integer> queue1 = new LinkedList<>();
        while (input.hasNextInt()) {
            queue1.add(input.nextInt());
        }
        IntStream.rangeClosed(0, 2).forEach(i -> input.nextLine());
        final Queue<Integer> queue2 = new LinkedList<>();
        while (input.hasNextInt()) {
            queue2.add(input.nextInt());
        }
        return calcResult(whoWins(queue1, queue2, recursive) ? queue1 : queue2);
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2020/aoc2020day22.txt";
    }
}