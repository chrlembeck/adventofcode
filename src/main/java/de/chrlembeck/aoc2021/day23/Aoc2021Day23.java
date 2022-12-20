package de.chrlembeck.aoc2021.day23;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static de.chrlembeck.aoc2021.day23.Position.*;

public class Aoc2021Day23 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2021Day23().run();
    }

    @Override
    public Object part1(final Scanner input) {
        Burrow burrow = readBurrow(input);
        PriorityQueue<Burrow> queue = new PriorityQueue<>();
        queue.add(burrow);
        AtomicInteger best = new AtomicInteger(Integer.MAX_VALUE);
        Burrow bestB;
        int counter = 0;
        int round = 0;
        while (!queue.isEmpty()) {
            PriorityQueue<Burrow> innerQueue = new PriorityQueue<>();
            while (!queue.isEmpty()) {
                counter++;
                Burrow current = queue.poll();
                if (current.getEnergy() >= best.get()) {
                    continue;
                }
                if (current.isReady()) {
                    best.set(current.getEnergy());
                    bestB = current;
                    System.out.println("solution: " + best.get());
                }
                if (counter % 10000 == 0) {
//                System.out.println(current.getEnergy());
                }
                innerQueue.addAll(possibleStates(current));
            }
            queue = innerQueue;
            round++;
            System.out.println("Runde " + round + ": " + queue.size());
        }

        return best.get();
    }

    public List<Burrow> possibleStates(Burrow burrow) {
        List<Burrow> newStates = new ArrayList<>();
        for (Position pos: Position.values()) {
            Collection<Move> moves = burrow.getMoves(pos);
            for (Move move : moves) {
                Burrow next = burrow.copy();
                next.move(move.from(), move.to());
//                next.removeAmphipod(move.from());
//                next.addAmphipod(move.to(), move.amphipodType());
                next.incEnergy(move.steps() * move.amphipodType().getEnergy());
                newStates.add(next);
            }
        }
        int ready = burrow.countReady();
        Optional<Burrow> better = newStates.stream().filter(b -> b.countReady() > ready).findAny();
        if (better.isPresent()) {
            return List.of(better.get());
        }

        return newStates;
    }

    private static Burrow readBurrow(Scanner input) {
        Burrow burrow = new Burrow();
        input.nextLine();
        input.nextLine();
        String line = input.nextLine();
        burrow.setAmphipod(A2, AmphipodType.of(line.charAt(3)));
        burrow.setAmphipod(B2, AmphipodType.of(line.charAt(5)));
        burrow.setAmphipod(C2, AmphipodType.of(line.charAt(7)));
        burrow.setAmphipod(D2, AmphipodType.of(line.charAt(9)));
        line = input.nextLine();
        burrow.setAmphipod(A1, AmphipodType.of(line.charAt(3)));
        burrow.setAmphipod(B1, AmphipodType.of(line.charAt(5)));
        burrow.setAmphipod(C1, AmphipodType.of(line.charAt(7)));
        burrow.setAmphipod(D1, AmphipodType.of(line.charAt(9)));
        return burrow;
    }

    @Override
    public Object part2(final Scanner input) {
        while (input.hasNext()) {
            final String line = input.nextLine();
        }
        return "";
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2021/aoc2021day23.txt";
    }
}