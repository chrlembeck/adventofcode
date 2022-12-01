package de.chrlembeck.aoc2015.day25;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.math.BigInteger;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2015Day25 extends AbstractAocBase {

    public static final Pattern REGEX = Pattern.compile(".+row (\\d+), column (\\d+).");

    public static void main(final String[] args) {
        new Aoc2015Day25().run();
    }

    @Override
    public BigInteger part1(final Scanner input) {
        Matcher matcher = matchRegex(REGEX, input.nextLine());
        return calc(Long.parseLong(matcher.group(2)), Long.parseLong(matcher.group(1)));
    }

    public BigInteger calc(long x, long y) {
        long v = (x+y-2)*(x+y-1)/2+x;
        BigInteger c = new BigInteger("20151125");
        BigInteger f = new BigInteger("252533");
        BigInteger m = new BigInteger("33554393");
        return c.multiply(f.modPow(BigInteger.valueOf(v-1), m)).mod(m);
    }

    @Override
    public Long part2(final Scanner input) {
        return 0l;
    }


    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2015/aoc2015day25.txt";
    }
}