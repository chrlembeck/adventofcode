package de.chrlembeck.aoc2017.day22;

import de.chrlembeck.aoc2017.day19.Direction;
import de.chrlembeck.aoc2017.day19.Position;
import de.chrlembeck.aoccommon.AbstractAocBase;
import de.chrlembeck.util.collections.BidirectionalGrowingArray;
import java.util.Scanner;

public class Aoc2017Day22 extends AbstractAocBase {

    enum State {
        CLEAN, WEAKENED, INFECTED, FLAGGED;
    }

    public static void main(final String[] args) {
        new Aoc2017Day22().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        final VirusFunction virus = (pos, currentState, currentRow, counter) -> {
            if (currentState == State.INFECTED) {
                pos = pos.right();
                currentRow.put(pos.getPosX(), State.CLEAN);
            } else {
                pos = pos.left();
                currentRow.put(pos.getPosX(), State.INFECTED);
                counter.value++;
            }
            return pos.forward();
        };
        return calc(input, 10_000, virus);
    }

    @Override
    public Integer part2(final Scanner input) {
        final VirusFunction virus = (pos, currentState, currentRow, counter) -> {
            switch (currentState) {
                case CLEAN:
                    currentRow.put(pos.getPosX(), State.WEAKENED);
                    pos = pos.left();
                    break;
                case WEAKENED:
                    currentRow.put(pos.getPosX(), State.INFECTED);
                    counter.value++;
                    break;
                case INFECTED:
                    currentRow.put(pos.getPosX(), State.FLAGGED);
                    pos = pos.right();
                    break;
                case FLAGGED:
                    currentRow.put(pos.getPosX(), State.CLEAN);
                    pos = pos.left().left();
                    break;
                default:
                    throw new IllegalStateException();
            }
            return pos.forward();
        };
        return calc(input, 10_000_000, virus);
    }

    public Integer calc(final Scanner input, final int iterations, final VirusFunction virusFunction) {
        final BidirectionalGrowingArray<BidirectionalGrowingArray<State>> array = new BidirectionalGrowingArray<>(
                BidirectionalGrowingArray[]::new);
        while (input.hasNextLine()) {
            final String line = input.nextLine();
            final BidirectionalGrowingArray<State> row = new BidirectionalGrowingArray<>(State[]::new);
            array.put(array.size(), row);
            for (int i = 0; i < line.length(); i++) {
                row.put(i, line.charAt(i) == '#' ? State.INFECTED : State.CLEAN);
            }
        }
        Position pos = new Position(array.get(0).size() / 2, array.size() / 2, Direction.UP);
        final Counter infections = new Counter();
        for (int i = 0; i < iterations; i++) {
            BidirectionalGrowingArray<State> currentRow = array.get(pos.getPosY());
            if (currentRow == null) {
                array.put(pos.getPosY(), currentRow = new BidirectionalGrowingArray<>(State[]::new));
            }
            State currentState = currentRow.get(pos.getPosX());
            if (currentState == null) {
                currentRow.put(pos.getPosX(), currentState = State.CLEAN);
            }
            pos = virusFunction.apply(pos, currentState, currentRow, infections);
        }
        return infections.value;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2017/aoc2017day22.txt";
    }
}