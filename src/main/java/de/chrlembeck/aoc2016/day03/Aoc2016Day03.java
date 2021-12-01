package de.chrlembeck.aoc2016.day03;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Aoc2016Day03 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2016Day03().run();
    }

    @Override
    public Long part1(final Scanner input) {
        return input.useDelimiter("\n").tokens().map(Triple::new).filter(Triple::isValid).count();
    }

    @Override
    public Long part2(final Scanner input) {
        final List<Triple> list = new ArrayList<>();
        final Triple[] array = input.useDelimiter("\n").tokens().map(Triple::new).toArray(Triple[]::new);
        for (int i = 0; i < array.length; i += 3) {
            final Triple tripleA = array[i];
            final Triple tripleB = array[i + 1];
            final Triple tripleC = array[i + 2];
            list.add(new Triple(tripleA.valueA, tripleB.valueA, tripleC.valueA));
            list.add(new Triple(tripleA.valueB, tripleB.valueB, tripleC.valueB));
            list.add(new Triple(tripleA.valueC, tripleB.valueC, tripleC.valueC));
        }
        return list.stream().filter(Triple::isValid).count();
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2016/aoc2016day03.txt";
    }

    static class Triple {

        private final int valueA;

        private final int valueB;

        private final int valueC;

        public Triple(final String line) {
            final StringTokenizer tokenizer = new StringTokenizer(line, " ", false);
            valueA = Integer.parseInt(tokenizer.nextToken());
            valueB = Integer.parseInt(tokenizer.nextToken());
            valueC = Integer.parseInt(tokenizer.nextToken());
        }

        public Triple(final int valueA, final int valueB, final int valueC) {
            this.valueA = valueA;
            this.valueB = valueB;
            this.valueC = valueC;
        }

        public boolean isValid() {
            return (valueA >= valueB && valueA >= valueC && valueA < valueB + valueC)
                    || (valueB >= valueA && valueB >= valueC && valueB < valueA + valueC)
                    || (valueC >= valueA && valueC >= valueB && valueC < valueA + valueB);
        }
    }
}