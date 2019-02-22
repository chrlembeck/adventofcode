package de.chrlembeck.aoc2017.day03;

import java.util.Scanner;

import de.chrlembeck.aoccommon.AbstractAocBase;
import de.chrlembeck.util.collections.BidirectionalGrowingArray;

public class Aoc2017Day03 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2017Day03().run();
    }

    @Override
    public String part1(final Scanner input) {
        final int testValue = input.nextInt();
        return Integer.toString(manhattanDist(testValue));
    }

    @Override
    public String part2(final Scanner input) {
        final int testValue = input.nextInt();
        return Integer.toString(nextBiggerValue(testValue));
    }

    public int squareSize(final int value) {
        int squareSize = 1;
        while ((2 * squareSize - 1) * (2 * squareSize - 1) < value) {
            squareSize++;
        }
        return squareSize;
    }

    public int bottomRight(final int size) {
        return (2 * size - 1) * (2 * size - 1);
    }

    public int bottomLeft(final int size) {
        return bottomRight(size) - 2 * (size - 1);
    }

    public int topLeft(final int size) {
        return bottomLeft(size) - 2 * (size - 1);
    }

    public int topRight(final int size) {
        return topLeft(size) - 2 * (size - 1);
    }

    public int manhattanDist(final int value) {
        final int size = squareSize(value);
        final int bottomLeft = bottomLeft(size);
        final int bottomRight = bottomRight(size);
        if (bottomLeft <= value && value <= bottomRight) {
            return size - 1 + Math.abs(((bottomRight + bottomLeft) / 2 - value));
        }
        final int topLeft = topLeft(size);
        if (topLeft <= value && value <= bottomLeft) {
            return size - 1 + Math.abs(((bottomLeft + topLeft) / 2 - value));
        }
        final int topRight = topRight(size);
        if (topRight <= value && value <= topLeft) {
            return size - 1 + Math.abs(((topLeft + topRight) / 2 - value));
        }
        if (bottomRight(size - 1) <= value && value <= topRight) {
            return size - 1 + Math.abs(((topRight + bottomRight(size - 1)) / 2 - value));
        }
        throw new IllegalStateException();
    }

    private int nextBiggerValue(final int value) {
        final BidirectionalGrowingArray<BidirectionalGrowingArray<Integer>> array = new BidirectionalGrowingArray<BidirectionalGrowingArray<Integer>>(
                BidirectionalGrowingArray[]::new);
        int xPos = 0;
        int yPos = 0;
        setValue(array, xPos, yPos, 1);
        while (getValue(array, xPos, yPos) <= value) {
            if (getValue(array, xPos, yPos - 1) == 0 && getValue(array, xPos, yPos + 1) == 0 &&
                    getValue(array, xPos - 1, yPos) == 0 && getValue(array, xPos + 1, yPos) == 0) {
                xPos++;
            } else if (getValue(array, xPos - 1, yPos) > 0 && getValue(array, xPos, yPos - 1) == 0) {
                yPos--;
            } else if (getValue(array, xPos, yPos + 1) > 0 && getValue(array, xPos - 1, yPos) == 0) {
                xPos--;
            } else if (getValue(array, xPos + 1, yPos) > 0 && getValue(array, xPos, yPos + 1) == 0) {
                yPos++;
            } else if (getValue(array, xPos, yPos - 1) > 0 && getValue(array, xPos + 1, yPos) == 0) {
                xPos++;
            }
            final int sum = getValue(array, xPos - 1, yPos - 1)
                    + getValue(array, xPos, yPos - 1)
                    + getValue(array, xPos + 1, yPos - 1)
                    + getValue(array, xPos - 1, yPos)
                    + getValue(array, xPos + 1, yPos)
                    + getValue(array, xPos - 1, yPos + 1)
                    + getValue(array, xPos, yPos + 1)
                    + getValue(array, xPos + 1, yPos + 1);
            setValue(array, xPos, yPos, sum);
        }
        return getValue(array, xPos, yPos);
    }

    public void setValue(final BidirectionalGrowingArray<BidirectionalGrowingArray<Integer>> array, final int xPos,
            final int yPos,
            final int value) {
        BidirectionalGrowingArray<Integer> row = array.get(yPos);
        if (row == null) {
            row = new BidirectionalGrowingArray<>(Integer[]::new);
            array.put(yPos, row);
        }
        row.put(xPos, value);
    }

    public int getValue(final BidirectionalGrowingArray<BidirectionalGrowingArray<Integer>> array, final int xPos,
            final int yPos) {
        final BidirectionalGrowingArray<Integer> row = array.get(yPos);
        if (row == null) {
            return 0;
        } else {
            final Integer value = row.get(xPos);
            return value == null ? 0 : value.intValue();
        }
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2017/aoc2017day03.txt";
    }
}