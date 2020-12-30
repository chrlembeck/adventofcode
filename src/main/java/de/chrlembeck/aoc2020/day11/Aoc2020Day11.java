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

    public int calc(final Scanner input, final BiFunction<SeatState[][], Position, Integer> neighborCounter, final int neighborThreshold) {
        final SeatState[][] seats = readSeats(input);
        boolean changed = true;
        while (changed) {
            changed = calculateRound(seats, neighborCounter, neighborThreshold);
        }

        int occupied = 0;
        for (final SeatState[] row : seats) {
            for (final SeatState seat : row) {
                if (seat == SeatState.OCCUPIED) {
                    occupied++;
                }
            }
        }
        return occupied;
    }

    private boolean calculateRound(final SeatState[][] seats, final BiFunction<SeatState[][], Position, Integer> neighborCounter,
            final int neighborThreshold) {
        final SeatState[][] oldSeats = new SeatState[seats.length][];
        final int width = seats[0].length;
        final int height = seats.length;
        for (int i = 0; i < height; i++) {
            oldSeats[i] = Arrays.copyOf(seats[i], width);
        }
        boolean modification = false;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int neighbors = neighborCounter.apply(oldSeats, new Position(x, y));
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

    private SeatState[] convertRow(final String row) {
        final SeatState[] seats = new SeatState[row.length()];
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

    public int directNeighborCounter(final SeatState[][] seats, final Position position) {
        final int posX = position.getPosX();
        final int posY = position.getPosY();
        final int width = seats[0].length;
        final int height = seats.length;
        int neighbors = 0;

        if (posX > 0 && posY > 0 && seats[posY - 1][posX - 1] == SeatState.OCCUPIED) {
            neighbors++;
        }
        if (posY > 0 && seats[posY - 1][posX] == SeatState.OCCUPIED) {
            neighbors++;
        }
        if (posX < width - 1 && posY > 0 && seats[posY - 1][posX + 1] == SeatState.OCCUPIED) {
            neighbors++;
        }
        if (posX < width - 1 && seats[posY][posX + 1] == SeatState.OCCUPIED) {
            neighbors++;
        }
        if (posX < width - 1 && posY < height - 1 && seats[posY + 1][posX + 1] == SeatState.OCCUPIED) {
            neighbors++;
        }
        if (posY < height - 1 && seats[posY + 1][posX] == SeatState.OCCUPIED) {
            neighbors++;
        }
        if (posX > 0 && posY < height - 1 && seats[posY + 1][posX - 1] == SeatState.OCCUPIED) {
            neighbors++;
        }
        if (posX > 0 && seats[posY][posX - 1] == SeatState.OCCUPIED) {
            neighbors++;
        }
        return neighbors;
    }

    public int visibleNeighborCounter(final SeatState[][] seats, final Position position) {
        final int posX = position.getPosX();
        final int posY = position.getPosY();
        return seesNeighbor(seats, posX, posY, -1, -1)
                + seesNeighbor(seats, posX, posY, 0, -1)
                + seesNeighbor(seats, posX, posY, 1, -1)
                + seesNeighbor(seats, posX, posY, 1, 0)
                + seesNeighbor(seats, posX, posY, 1, 1)
                + seesNeighbor(seats, posX, posY, 0, 1)
                + seesNeighbor(seats, posX, posY, -1, 1)
                + seesNeighbor(seats, posX, posY, -1, 0);
    }

    private int seesNeighbor(final SeatState[][] seats, int posX, int posY, final int deltaX, final int deltaY) {
        posX += deltaX;
        posY += deltaY;
        while (posX >= 0 && posX < seats[0].length && posY >= 0 && posY < seats.length) {
            if (seats[posY][posX] == SeatState.OCCUPIED) {
                return 1;
            }
            if (seats[posY][posX] == SeatState.EMPTY) {
                return 0;
            }
            posX += deltaX;
            posY += deltaY;
        }
        return 0;
    }
}