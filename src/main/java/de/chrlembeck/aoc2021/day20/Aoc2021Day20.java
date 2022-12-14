package de.chrlembeck.aoc2021.day20;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.util.Scanner;

public class Aoc2021Day20 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2021Day20().run();
    }

    @Override
    public Object part1(final Scanner input) {
        return calc(input, 2);
    }

    @Override
    public Integer part2(final Scanner input) {
        return calc(input, 50);
    }

    private int calc(final Scanner input, int rounds) {
        final boolean[] iea = toImageRow(input.nextLine());
        input.nextLine();
        boolean[][] image = input.useDelimiter("\\n").tokens().map(this::toImageRow).toArray(boolean[][]::new);

        for (int i = 0; i < rounds; i++) {
            image = enhance(image, iea, iea[0] && (i % 2 == 1));
        }

        int counter = 0;
        for (boolean[] row : image) {
            for (int i = 0; i < row.length; i++) {
                if (row[i]) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private boolean[] toImageRow(String s) {
        boolean[] row = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            row[i] = s.charAt(i) == '#';
        }
        return row;
    }

    private boolean[][] enhance(final boolean[][] image, final boolean[] iea, final boolean background) {
        final boolean[][] result = new boolean[image.length + 4][image[0].length + 4];
        for (int ny = 0; ny < result.length; ny++) {
            for (int nx = 0; nx < result[0].length; nx++) {
                final int x = nx - 2;
                final int y = ny - 2;
                int index = 0;

                if ((x - 1 < 0 || y - 1 < 0 || x - 1 >= image[0].length || y - 1 >= image.length) ? background : image[y - 1][x - 1]) {
                    index = index | 0b100000000;
                }
                if ((x < 0 || y - 1 < 0 || x >= image[0].length || y - 1 >= image.length) ? background : image[y - 1][x]) {
                    index = index | 0b010000000;
                }
                if ((x + 1 < 0 || y - 1 < 0 || x + 1 >= image[0].length || y - 1 >= image.length) ? background : image[y - 1][x + 1]) {
                    index = index | 0b001000000;
                }
                if ((x - 1 < 0 || y < 0 || x - 1 >= image[0].length || y >= image.length) ? background : image[y][x - 1]) {
                    index = index | 0b000100000;
                }
                if ((x < 0 || y < 0 || x >= image[0].length || y >= image.length) ? background : image[y][x]) {
                    index = index | 0b000010000;
                }
                if ((x + 1 < 0 || y < 0 || x + 1 >= image[0].length || y >= image.length) ? background : image[y][x + 1]) {
                    index = index | 0b000001000;
                }
                if ((x - 1 < 0 || y + 1 < 0 || x - 1 >= image[0].length || y + 1 >= image.length) ? background : image[y + 1][x - 1]) {
                    index = index | 0b000000100;
                }
                if ((x < 0 || y + 1 < 0 || x >= image[0].length || y + 1 >= image.length) ? background : image[y + 1][x]) {
                    index = index | 0b000000010;
                }
                if ((x + 1 < 0 || y + 1 < 0 || x + 1 >= image[0].length || y + 1 >= image.length) ? background : image[y + 1][x + 1]) {
                    index = index | 0b000000001;
                }
                result[ny][nx] = iea[index];
            }
        }
        return result;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2021/aoc2021day20.txt";
    }
}