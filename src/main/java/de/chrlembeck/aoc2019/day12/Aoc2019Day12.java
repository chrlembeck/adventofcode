package de.chrlembeck.aoc2019.day12;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static de.chrlembeck.aoccommon.MathUtil.lcm;

public class Aoc2019Day12 extends AbstractAocBase {

    private static final Pattern REGEX = Pattern.compile("<x=(-?\\d+), y=(-?\\d+), z=(-?\\d+)>");

    public static void main(final String[] args) {
        new Aoc2019Day12().run();
    }

    @Override
    public Object part1(final Scanner input) {
        final List<Moon> moons = readMoons(input);
        for (int i = 0; i < 1000; i++) {
            move(moons);
        }
        return moons.stream().mapToLong(Moon::getEnergy).sum();
    }

    @Override
    public Object part2(final Scanner input) {
        final List<Moon> moons = readMoons(input);
        return lcm(BigInteger.valueOf(getLoopLength(extractDimension(moons, 0))),
                BigInteger.valueOf(getLoopLength(extractDimension(moons, 1))),
                BigInteger.valueOf(getLoopLength(extractDimension(moons, 2))));
    }

    private List<Moon> readMoons(final Scanner input) {
        return input.findAll(REGEX)
                .map(mr -> new Moon(Integer.parseInt(mr.group(1)), Integer.parseInt(mr.group(2)), Integer.parseInt(mr.group(3))))
                .collect(Collectors.toList());
    }

    private Dimension[] extractDimension(final List<Moon> moons, final int dimensionIdx) {
        return moons.stream().map(m -> m.getDimension(dimensionIdx)).toArray(Dimension[]::new);
    }

    private long getLoopLength(final Dimension... moonDimensions) {
        final Dimension[] originalDimensions = Arrays.stream(moonDimensions).map(Dimension::clone).toArray(Dimension[]::new);
        long idx = 0;
        boolean same;
        do {
            idx++;
            move(moonDimensions);
            same = true;
            for (int moonIdx = 0; moonIdx < moonDimensions.length; moonIdx++) {
                same &= moonDimensions[moonIdx].getPosition() == originalDimensions[moonIdx].getPosition() &&
                        moonDimensions[moonIdx].getVelocity() == originalDimensions[moonIdx].getVelocity();
            }
        } while (!same);
        return idx;
    }

    private void move(final List<Moon> moons) {
        for (int leftIndex = 0; leftIndex < moons.size() - 1; leftIndex++) {
            for (int rightIndex = leftIndex + 1; rightIndex < moons.size(); rightIndex++) {
                final Moon oneMoon = moons.get(leftIndex);
                final Moon otherMoon = moons.get(rightIndex);
                oneMoon.applyGravityMutually(otherMoon);
            }
        }
        moons.forEach(Moon::applyVelocity);
    }

    private void move(final Dimension... dimensions) {
        for (int leftIndex = 0; leftIndex < dimensions.length - 1; leftIndex++) {
            for (int rightIndex = leftIndex + 1; rightIndex < dimensions.length; rightIndex++) {
                final Dimension oneDimension = dimensions[leftIndex];
                final Dimension otherDimension = dimensions[rightIndex];
                oneDimension.applyGravityMutually(otherDimension);
            }
        }
        Arrays.stream(dimensions).forEach(Dimension::applyVelocity);
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2019/aoc2019day12.txt";
    }
}