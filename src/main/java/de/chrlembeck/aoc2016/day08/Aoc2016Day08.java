package de.chrlembeck.aoc2016.day08;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2016Day08 extends AbstractAocBase {

    public static final Pattern RECT = Pattern.compile("rect (\\d+)x(\\d+)");

    public static final Pattern ROTATE_ROW = Pattern.compile("rotate row y=(\\d+) by (\\d+)");

    public static final Pattern ROTATE_COL = Pattern.compile("rotate column x=(\\d+) by (\\d+)");

    public static void main(final String[] args) {
        new Aoc2016Day08().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        final boolean[][] screen = new boolean[6][50];
        input.useDelimiter("\n").tokens().forEach(command -> applyCommand(screen, command));
        return Arrays.stream(screen).mapToInt(this::countTrue).sum();
    }

    @Override
    public String part2(final Scanner input) {
        final boolean[][] screen = new boolean[6][50];
        input.useDelimiter("\n").tokens().forEach(command -> applyCommand(screen, command));
        return print(screen);
    }

    private void applyCommand(final boolean[][] screen, final String command) {
        final Matcher rectMatcher = RECT.matcher(command);
        final Matcher rotColMatcher = ROTATE_COL.matcher(command);
        final Matcher rotRowMatcher = ROTATE_ROW.matcher(command);
        if (rectMatcher.matches()) {
            final int width = Integer.parseInt(rectMatcher.group(1));
            final int height = Integer.parseInt(rectMatcher.group(2));
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    screen[y][x] = true;
                }
            }
        } else if (rotColMatcher.matches()) {
            final int columnIndex = Integer.parseInt(rotColMatcher.group(1));
            final int offset = Integer.parseInt(rotColMatcher.group(2));
            final boolean[] tmp = new boolean[screen.length];
            for (int i = 0; i < screen.length; i++) {
                tmp[i] = screen[i][columnIndex];
            }
            for (int i = 0; i < screen.length; i++) {
                screen[i][columnIndex] = tmp[(i - offset + screen.length) % screen.length];
            }
        } else if (rotRowMatcher.matches()) {
            final int rowIndex = Integer.parseInt(rotRowMatcher.group(1));
            final int offset = Integer.parseInt(rotRowMatcher.group(2));
            final boolean[] tmp = Arrays.copyOf(screen[rowIndex], screen[rowIndex].length);
            for (int i = 0; i < screen[rowIndex].length; i++) {
                screen[rowIndex][i] = tmp[(i - offset + screen[rowIndex].length) % screen[rowIndex].length];
            }
        } else {
            throw new IllegalArgumentException(command);
        }
        print(screen);
    }

    private int countTrue(final boolean... booleans) {
        int result = 0;
        for (final boolean value : booleans) {
            if (value) {
                result++;
            }
        }
        return result;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2016/aoc2016day08.txt";
    }

    @SuppressWarnings("PMD.UseVarargs")
    public String print(final boolean[][] screen) {
        final StringBuilder result = new StringBuilder();
        for (final boolean[] row : screen) {
            for (final boolean v : row) {
                result.append(v ? '#' : '.');
            }
            result.append('\n');
        }
        return result.toString().trim();
    }
}