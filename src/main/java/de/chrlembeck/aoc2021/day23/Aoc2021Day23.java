package de.chrlembeck.aoc2021.day23;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static de.chrlembeck.aoc2021.day23.AmphipodType.*;

public class Aoc2021Day23 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2021Day23().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        return calc(input, false);
    }

    @Override
    public Integer part2(final Scanner input) {
        return calc(input, true);
    }

    private Integer calc(final Scanner input, boolean step2) {
        Burrow burrow = readBurrow(input, step2);
        PriorityQueue<Burrow> queue = new PriorityQueue<>();
        queue.add(burrow);
        AtomicInteger best = new AtomicInteger(Integer.MAX_VALUE);
        int round = 0;
        while (!queue.isEmpty()) {
            Set<Burrow> innerQueue = new HashSet<>();
            while (!queue.isEmpty()) {
                Burrow current = queue.poll();
                if (current.getEnergy() >= best.get()) {
                    continue;
                }
                if (current.isReady(step2)) {
                    best.set(current.getEnergy());
                    System.out.println("solution: " + best.get());
                    return best.get();
                }
                innerQueue.addAll(possibleStates(current, step2));
            }
            queue = new PriorityQueue<>(innerQueue);
            round++;
            System.out.println("Runde " + round + ": " + queue.size());
        }
        return best.get();
    }

    public List<Burrow> possibleStates(Burrow burrow, boolean step2) {
        List<Burrow> newStates = new ArrayList<>();
        for (Position pos : Position.values()) {
            Collection<Move> moves = burrow.getMoves(pos, step2);
            for (Move move : moves) {
                Burrow next = burrow.move(move.from(), move.to(), move.steps() * move.amphipodType().getEnergy());
                if (move.to().isRoom()) {
                    return List.of(next);
                }
                newStates.add(next);
            }
        }
        return newStates;
    }

    private static Burrow readBurrow(Scanner input, boolean step2) {
        input.nextLine();
        input.nextLine();
        String line1 = input.nextLine();
        String line2 = input.nextLine();
        if (step2) {
            return new Burrow(null, null, null, null, null, null, null,
                    AmphipodType.of(line1.charAt(3)), DESERT, DESERT, AmphipodType.of(line2.charAt(3)),
                    AmphipodType.of(line1.charAt(5)), COPPER, BRONZE, AmphipodType.of(line2.charAt(5)),
                    AmphipodType.of(line1.charAt(7)), BRONZE, AMBER, AmphipodType.of(line2.charAt(7)),
                    AmphipodType.of(line1.charAt(9)), AMBER, COPPER, AmphipodType.of(line2.charAt(9)), 0);
        } else {
            return new Burrow(null, null, null, null, null, null, null,
                    AmphipodType.of(line1.charAt(3)), AmphipodType.of(line2.charAt(3)), null, null,
                    AmphipodType.of(line1.charAt(5)), AmphipodType.of(line2.charAt(5)), null, null,
                    AmphipodType.of(line1.charAt(7)), AmphipodType.of(line2.charAt(7)), null, null,
                    AmphipodType.of(line1.charAt(9)), AmphipodType.of(line2.charAt(9)), null, null, 0);
        }
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2021/aoc2021day23.txt";
    }
}