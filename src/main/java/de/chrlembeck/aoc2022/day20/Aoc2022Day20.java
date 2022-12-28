package de.chrlembeck.aoc2022.day20;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

public class Aoc2022Day20 extends AbstractAocBase {


    public static void main(final String[] args) {
        new Aoc2022Day20().run();
    }

    @Override
    public BigInteger part1(final Scanner input) {
        return calc(input, 1, ONE);
    }

    @Override
    public BigInteger part2(final Scanner input) {
        return calc(input, 10, new BigInteger("811589153"));
    }

    private BigInteger calc(final Scanner input, final int rounds, final BigInteger decryptionKey) {
        final List<BigInteger> items = new ArrayList<>();
        while (input.hasNextLine()) {
            items.add(input.nextBigInteger().multiply(decryptionKey));
        }
        final List<Integer> positions = new ArrayList<>(IntStream.range(0, items.size()).boxed().toList());
        for (int round = 0; round < rounds; round++) {
            for (int i = 0; i < items.size(); i++) {
                final int oldIndex = positions.indexOf(i);
                positions.remove(oldIndex);
                int newIndex = items.get(i).add(BigInteger.valueOf(oldIndex)).mod(BigInteger.valueOf(positions.size())).intValueExact();
                if (newIndex < 0) {
                    newIndex += positions.size();
                }
                positions.add(newIndex, i);
            }
        }
        final int z = positions.indexOf(items.indexOf(ZERO));
        final BigInteger a = items.get(positions.get((z + 1000) % positions.size()));
        final BigInteger b = items.get(positions.get((z + 2000) % positions.size()));
        final BigInteger c = items.get(positions.get((z + 3000) % positions.size()));
        return a.add(b).add(c);
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2022/aoc2022day20.txt";
    }
}