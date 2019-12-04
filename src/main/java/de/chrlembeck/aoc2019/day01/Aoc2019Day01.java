package de.chrlembeck.aoc2019.day01;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.math.BigInteger;
import java.util.Scanner;

public class Aoc2019Day01 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2019Day01().run();
    }

    @Override
    public Object part1(final Scanner input) {
        BigInteger sum = BigInteger.ZERO;
        while (input.hasNextBigInteger()) {
            final BigInteger mass = input.nextBigInteger();
            sum = sum.add(calcFuelForMass(mass));
        }
        return sum;
    }

    private BigInteger calcFuelForMass(final BigInteger mass) {
        return mass.divide(BigInteger.valueOf(3)).subtract(BigInteger.TWO);
    }

    @Override
    public Object part2(final Scanner input) {
        BigInteger sum = BigInteger.ZERO;
        while (input.hasNextBigInteger()) {
            BigInteger fuel = calcFuelForMass(input.nextBigInteger());
            while (fuel.compareTo(BigInteger.ZERO) > 0) {
                sum = sum.add(fuel);
                fuel = calcFuelForMass(fuel);
            }
        }
        return sum;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2019/aoc2019day01.txt";
    }
}
