package de.chrlembeck.aoc2015.day18;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Aoc2015Day18 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2015Day18().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        int[][] field = readField(input);

        for (int i = 0; i < 100; i++) {
            field = doStep(field, false);
        }

        return Arrays.stream(field).flatMapToInt(IntStream::of).sum();
    }

    @Override
    public Integer part2(final Scanner input) {
        int[][] field = readField(input);
        enlightCorners(field);
        for (int i = 0; i < 100; i++) {
            field = doStep(field, true);
        }
        return Arrays.stream(field).flatMapToInt(IntStream::of).sum();
    }

    private int[][] readField(Scanner input) {
        input.useDelimiter("\n");
        List<int[]> list = new ArrayList<>(input.tokens().map(this::toIntArray).collect(Collectors.toList()));
        list.add(0, new int[list.get(0).length]);
        list.add(new int[list.get(0).length]);
        int[][] field = list.toArray(int[][]::new);
        return field;
    }

    private int[][] doStep(int[][] field, boolean cornerLightsOn) {
        int[][] newField = new int[field.length][field[0].length];

        for (int y = 1; y < field.length - 1; y++) {
            for (int x = 1; x < field[0].length - 1; x++) {
                int neighbours =
                        field[y - 1][x - 1] + field[y - 1][x] + field[y - 1][x + 1] + field[y][x + 1] +
                                field[y + 1][x + 1] + field[y + 1][x] + field[y + 1][x - 1] + field[y][x - 1];
                if (field[y][x] == 1) {
                    newField[y][x] = neighbours == 2 || neighbours == 3 ? 1 : 0;
                } else {
                    newField[y][x] = neighbours == 3 ? 1 : 0;
                }
            }
        }
        if (cornerLightsOn) {
            enlightCorners(newField);
        }
        return newField;
    }

    private void enlightCorners(int[][] field) {
        field[1][1] = 1;
        field[1][field[0].length-2] = 1;
        field[field.length-2][1] = 1;
        field[field.length-2][field[0].length-2] = 1;
    }

    private int[] toIntArray(String line) {
        int[] array = new int[line.length() + 2];
        for (int i = 0; i < line.length(); i++) {
            array[i + 1] = line.charAt(i) == '#' ? 1 : 0;
        }
        return array;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2015/aoc2015day18.txt";
    }
}