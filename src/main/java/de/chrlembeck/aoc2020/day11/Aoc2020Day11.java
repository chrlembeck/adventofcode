package de.chrlembeck.aoc2020.day11;

import de.chrlembeck.aoc2019.day11.Position;
import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.BiFunction;

public class Aoc2020Day11 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2020Day11().run();
    }

    @Override
    public Object part1(final Scanner input) {
        return calc(input, this::directNeighborCounter, 4);
    }

    @Override
    public Object part2(final Scanner input) {
        return calc(input, this::visibleNeighborCounter, 5);
    }

    public int calc(final Scanner input, BiFunction<SeatState[][], Position, Integer> neighborCounter, int neighborThreshold) {
        SeatState[][] seats = readSeats(input);
        while (calculateRound(seats, neighborCounter, neighborThreshold)) {
        }
        int occupied = 0;
        for (int y = 0; y < seats.length; y++) {
            for (int x = 0; x < seats[y].length; x++) {
                if (seats[y][x] == SeatState.OCCUPIED) {
                    occupied++;
                }
            }
        }
        return occupied;
    }

    private boolean calculateRound(final SeatState[][] seats, final BiFunction<SeatState[][], Position, Integer> neighborCounter,
            int neighborThreshold) {
        final SeatState[][] oldSeats = new SeatState[seats.length][];
        final int width = seats[0].length;
        final int height = seats.length;
        for (int i = 0; i < height; i++) {
            oldSeats[i] = Arrays.copyOf(seats[i], width);
        }
        boolean modification = false;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int neighbors = neighborCounter.apply(oldSeats, new Position(x, y));
                if (oldSeats[y][x] == SeatState.EMPTY && neighbors == 0) {
                    seats[y][x] = SeatState.OCCUPIED;
                    modification = true;
                }
                if (oldSeats[y][x] == SeatState.OCCUPIED && neighbors >= neighborThreshold) {
                    seats[y][x] = SeatState.EMPTY;
                    modification = true;
                }
            }
        }
        return modification;
    }

    private SeatState[][] readSeats(final Scanner input) {
        return input.tokens().map(this::convertRow).toArray(SeatState[][]::new);
    }

    private SeatState[] convertRow(String row) {
        SeatState[] seats = new SeatState[row.length()];
        for (int i = 0; i < row.length(); i++) {
            seats[i] = switch (row.charAt(i)) {
                case '#' -> SeatState.OCCUPIED;
                case '.' -> SeatState.FLOOR;
                case 'L' -> SeatState.EMPTY;
                default -> throw new IllegalArgumentException();
            };
        }
        return seats;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2020/aoc2020day11.txt";
    }

    public int directNeighborCounter(SeatState[][] seats, Position position) {
        final int x = position.getxPos();
        final int y = position.getyPos();
        final int width = seats[0].length;
        final int height = seats.length;
        int neighbors = 0;

        if (x > 0 && y > 0 && seats[y - 1][x - 1] == SeatState.OCCUPIED) {
            neighbors++;
        }
        if (y > 0 && seats[y - 1][x] == SeatState.OCCUPIED) {
            neighbors++;
        }
        if (x < width - 1 && y > 0 && seats[y - 1][x + 1] == SeatState.OCCUPIED) {
            neighbors++;
        }
        if (x < width - 1 && seats[y][x + 1] == SeatState.OCCUPIED) {
            neighbors++;
        }
        if (x < width - 1 && y < height - 1 && seats[y + 1][x + 1] == SeatState.OCCUPIED) {
            neighbors++;
        }
        if (y < height - 1 && seats[y + 1][x] == SeatState.OCCUPIED) {
            neighbors++;
        }
        if (x > 0 && y < height - 1 && seats[y + 1][x - 1] == SeatState.OCCUPIED) {
            neighbors++;
        }
        if (x > 0 && seats[y][x - 1] == SeatState.OCCUPIED) {
            neighbors++;
        }
        return neighbors;
    }

    public int visibleNeighborCounter(SeatState[][] seats, Position position) {
        final int x = position.getxPos();
        final int y = position.getyPos();
        return seesNeighbor(seats, x, y, -1, -1)
                + seesNeighbor(seats, x, y, 0,-1)
                + seesNeighbor(seats, x, y, 1,-1)
                + seesNeighbor(seats, x, y, 1,0)
                + seesNeighbor(seats, x, y, 1,1)
                + seesNeighbor(seats, x, y, 0,1)
                + seesNeighbor(seats, x, y, -1,1)
                + seesNeighbor(seats, x, y, -1,0);
    }

    private int seesNeighbor(final SeatState[][] seats, int x, int y, final int dx, final int dy) {
        x+=dx;
        y+=dy;
        while (x >= 0 && x < seats[0].length && y >= 0 && y < seats.length) {
            if (seats[y][x] == SeatState.OCCUPIED) {
                return 1;
            }
            if (seats[y][x] == SeatState.EMPTY) {
                return 0;
            }
            x+=dx;
            y+=dy;
        }
        return 0;
    }
}