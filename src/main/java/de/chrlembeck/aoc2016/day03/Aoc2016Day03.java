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
            Triple x = array[i];
            Triple y = array[i + 1];
            Triple z = array[i + 2];
            list.add(new Triple(x.a, y.a, z.a));
            list.add(new Triple(x.b, y.b, z.b));
            list.add(new Triple(x.c, y.c, z.c));
        }
        return list.stream().filter(Triple::isValid).count();
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2016/aoc2016day03.txt";
    }

    static class Triple {

        private int a;

        private int b;

        private int c;

        public Triple(String line) {
            StringTokenizer tokenizer = new StringTokenizer(line, " ", false);
            a = Integer.parseInt(tokenizer.nextToken());
            b = Integer.parseInt(tokenizer.nextToken());
            c = Integer.parseInt(tokenizer.nextToken());
        }

        public Triple(int x, int y, int z) {
            a = x;
            b = y;
            c = z;
        }

        public boolean isValid() {
            return (a >= b && a >= c && a < b + c) || (b >= a && b >= c && b < a + c) || (c >= a && c >= b && c < a + b);
        }
    }
}