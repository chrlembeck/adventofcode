package de.chrlembeck.aoc2021.day03;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;

public class Aoc2021Day03 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2021Day03().run();
    }

    @Override
    public Object part1(final Scanner input) {
        int [] bitCounter = null;
        int totalCount = 0;
        while (input.hasNext()) {
            final String line = input.nextLine();
            if (bitCounter == null) {
                bitCounter = new int[line.length()];
            }
            for (int idx = 0; idx < line.length(); idx++) {
                if (line.charAt(idx) == '1') {
                    bitCounter[idx]++;
                }
            }
            totalCount++;
        }
        int gamma = 0;
        int epsilon = 0;
        for (int idx = 0; idx < bitCounter.length; idx++) {
            if (bitCounter[idx] > totalCount / 2) {
                gamma |= 1 << (bitCounter.length - idx - 1);
            } else {
                epsilon |= 1 << (bitCounter.length - idx - 1);
            }
        }
        return gamma * epsilon;
    }

    @Override
    public Object part2(final Scanner input) {
        final List<String> lines = new ArrayList<>();
        while (input.hasNext()) {
            lines.add(input.nextLine());
        }
        final int oxygenGeneratorRating = compute(new ArrayList<>(lines), (count, total) -> count * 2 >= total ? '1': '0');
        final int co2ScrubberRating = compute(new ArrayList<>(lines), (count, total) -> count * 2 >= total ? '0': '1');
        return oxygenGeneratorRating * co2ScrubberRating;
    }

    private int compute(final List<String> lines, final BiFunction<Integer, Integer, Character> wantedBitCriteria) {
        for (int charIdx = 0; charIdx < lines.get(0).length() && lines.size() > 1; charIdx++) {
            int counter = 0;
            for (final String line: lines) {
                if (line.charAt(charIdx) == '1') {
                    counter++;
                }
            }
            final char wantedBit = wantedBitCriteria.apply(counter, lines.size());
            for (int lineIdx = lines.size() - 1; lineIdx >= 0; lineIdx--) {
                if (lines.get(lineIdx).charAt(charIdx) != wantedBit) {
                    lines.remove(lineIdx);
                }
            }
        }
        return Integer.parseInt(lines.get(0), 2);
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2021/aoc2021day03.txt";
    }
}