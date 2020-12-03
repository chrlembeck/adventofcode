package de.chrlembeck.aoc2020.day03;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Scanner;

public class Aoc2020Day03 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2020Day03().run();
    }

    @Override
    public Object part1(final Scanner input) {
        String[] area = input.tokens().toArray(String[]::new);
        return countTrees(area, 3,1);
    }

    @Override
    public Object part2(final Scanner input) {
        String[] area = input.tokens().toArray(String[]::new);
        return countTrees(area, 1, 1)
                * countTrees(area, 3, 1)
                * countTrees(area, 5, 1)
                * countTrees(area, 7, 1)
                * countTrees(area, 1, 2);
    }

    private long countTrees(String[] area, int right, int down) {
        int trees = 0;
        int pos = 0;
        for (int lineIdx = 0; lineIdx < area.length; lineIdx += down) {
            String line = area[lineIdx];
            if (line.charAt(pos) == '#') {
                trees++;
            }
            pos = (pos + right) % line.length();
        }
        return trees;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2020/aoc2020day03.txt";
    }
}