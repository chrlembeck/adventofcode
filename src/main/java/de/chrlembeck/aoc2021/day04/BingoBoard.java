package de.chrlembeck.aoc2021.day04;

import java.util.Collection;

public class BingoBoard {

    private final int[][] numbers;

    public BingoBoard(final int[]... numbers) {
        this.numbers = numbers.clone();
    }

    public boolean isComplete(final Collection<Integer> drawnNumbers) {
        for (final int[] row : numbers) {
            if (drawnNumbers.contains(row[0])
                    && drawnNumbers.contains(row[1])
                    && drawnNumbers.contains(row[2])
                    && drawnNumbers.contains(row[3])
                    && drawnNumbers.contains(row[4])) {
                return true;
            }
        }
        for (int colIdx = 0; colIdx < 5; colIdx++) {
            if (drawnNumbers.contains(numbers[0][colIdx])
                    && drawnNumbers.contains(numbers[1][colIdx])
                    && drawnNumbers.contains(numbers[2][colIdx])
                    && drawnNumbers.contains(numbers[3][colIdx])
                    && drawnNumbers.contains(numbers[4][colIdx])) {
                return true;
            }
        }
        return false;
    }

    public Integer sumUnmarked(final Collection<Integer> drawnNumbers) {
        int sum = 0;
        for (final int[] row : numbers) {
            for (final int number : row) {
                if (!drawnNumbers.contains(number)) {
                    sum += number;
                }
            }
        }
        return sum;
    }
}