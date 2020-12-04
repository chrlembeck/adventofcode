package de.chrlembeck.aoc2019.day08;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Arrays;
import java.util.Scanner;

public class Aoc2019Day08 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2019Day08().run();
    }

    @Override
    public Object part1(final Scanner input) {
        final String line = input.nextLine();
        final int width = 25;
        final int height = 6;
        final int layerSize = width * height;

        int minZeros = layerSize;
        int result = -1;

        for (int layerIndex = 0; layerIndex < line.length() / layerSize; layerIndex++) {
            int zeros = 0;
            int ones = 0;
            int twos = 0;
            for (int i = 0; i < layerSize; i++) {
                final char pixel = line.charAt(layerIndex * layerSize + i);
                switch (pixel) {
                    case '0':
                        zeros++;
                        break;
                    case '1':
                        ones++;
                        break;
                    case '2':
                        twos++;
                        break;
                    default:
                }
            }
            if (zeros < minZeros) {
                minZeros = zeros;
                result = ones * twos;
            }
        }

        return result;
    }

    @Override
    public Object part2(final Scanner input) {
        final String line = input.nextLine();
        final int width = 25;
        final int height = 6;
        final int layersize = width * height;
        final char[] image = new char[layersize];
        Arrays.fill(image, ' ');
        for (int i = 0; i < line.length(); i++) {
            if (image[i % layersize] == ' ') {
                if (line.charAt(i) == '0') {
                    image[i % layersize] = '.';
                } else if (line.charAt(i) == '1') {
                    image[i % layersize] = 'X';
                }
            }
        }
        final StringBuilder result = new StringBuilder();
        for (int y = 0; y < height; y++) {
            result.append(image, y*width, width);
            result.append('\n');
        }
        return result.toString();
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2019/aoc2019day08.txt";
    }
}