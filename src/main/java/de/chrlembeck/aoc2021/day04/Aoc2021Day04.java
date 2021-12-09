package de.chrlembeck.aoc2021.day04;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.*;

public class Aoc2021Day04 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2021Day04().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        final int[] numbers = Arrays.stream(input.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        final List<BingoBoard> boards = readBingoBoards(input);
        final Set<Integer> drawnNumbers = new TreeSet<>();
        for (final int drawnNumber:numbers) {
            drawnNumbers.add(drawnNumber);
            final Optional<BingoBoard> winner = boards.stream().filter(board -> board.isComplete(drawnNumbers)).findAny();
            if (winner.isPresent()) {
                return winner.get().sumUnmarked(drawnNumbers) * drawnNumber;
            }
        }
        return -1;
    }

    private List<BingoBoard> readBingoBoards(final Scanner input) {
        final List<BingoBoard> boards = new ArrayList<>();
        while (input.hasNextLine()) {
            input.nextLine();
            boards.add(readBingoBoard(input));
        }
        return boards;
    }

    private BingoBoard readBingoBoard(final Scanner input) {
        final int[][] numbers = new int[5][];
        for (int i = 0; i < 5; i++) {
            final int[] row = Arrays.stream(input.nextLine().trim().split(" +")).mapToInt(Integer::parseInt).toArray();
            numbers[i] = row;
        }
        return new BingoBoard(numbers);
    }

    @Override
    public Object part2(final Scanner input) {
        final int[] numbers = Arrays.stream(input.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        final List<BingoBoard> boards = readBingoBoards(input);
        final Set<Integer> drawnNumbers = new TreeSet<>();
        for (final int drawnNumber:numbers) {
            drawnNumbers.add(drawnNumber);
            if (boards.size() > 1) {
                boards.removeIf(board -> board.isComplete(drawnNumbers));
            }
            if (boards.get(0).isComplete(drawnNumbers)) {
                return boards.get(0).sumUnmarked(drawnNumbers) * drawnNumber;
            }
        }
        return -1;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2021/aoc2021day04.txt";
    }
}