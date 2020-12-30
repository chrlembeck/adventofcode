package de.chrlembeck.aoc2020.day13;

import de.chrlembeck.aoccommon.AbstractAocBase;
import de.chrlembeck.aoccommon.MathUtil;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class Aoc2020Day13 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2020Day13().run();
    }

    @Override
    public Object part1(final Scanner input) {
        final long earliestDepartureTime = Long.parseLong(input.nextLine());
        final long[] busses = Arrays.stream(input.nextLine().split(",")).filter(Predicate.not("x"::equals)).mapToLong(Long::parseLong).toArray();
        long min = Long.MAX_VALUE;
        long minLine = -1;
        for (final long line : busses) {
            final long waitTime = (line - (earliestDepartureTime % line)) % line;
            if (waitTime < min) {
                min = waitTime;
                minLine = line;
            }
        }

        return minLine * min;
    }

    @Override
    public Object part2(final Scanner input) {
        final BigInteger earliestDepartureTime = new BigInteger(input.nextLine());
        final String[] busses = input.nextLine().split(",");
        final List<BigInteger> factors = new ArrayList<>();
        final List<BigInteger> modulos = new ArrayList<>();
        for (int i = 0; i < busses.length; i++) {
            if (!"x".equals(busses[i])) {
                final BigInteger lineId = new BigInteger(busses[i]);
                modulos.add(lineId);
                factors.add(lineId.subtract(BigInteger.valueOf(i)).mod(lineId));
            }
        }
        final BigInteger[] chineeseRemainder = MathUtil.chineeseRemainder(factors.toArray(BigInteger[]::new), modulos.toArray(BigInteger[]::new));
        BigInteger result = chineeseRemainder[0];
        while (result.compareTo(earliestDepartureTime) < 0) {
            result = result.add(chineeseRemainder[1]);
        }
        return chineeseRemainder[0];
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2020/aoc2020day13.txt";
    }
}