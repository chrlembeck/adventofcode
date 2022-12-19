package de.chrlembeck.aoc2022.day15;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.awt.*;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;

public class Aoc2022Day15 extends AbstractAocBase {

    private final Pattern pattern = Pattern.compile("Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)");

    public static void main(final String[] args) {
        new Aoc2022Day15().run();
    }

    @Override
    public Long part1(final Scanner input) {
        return checkRow(input, 2000000);
    }

    @Override
    public BigInteger part2(final Scanner input) {
        return findBeacon(input, 0, 0, 4000001, 4000001);
    }

    public long checkRow(final Scanner input, int row) {
        List<Sensor> sensors = tokenStream(input, "\\n", pattern, this::sensor).toList();
        int minX = sensors.stream().mapToInt(s -> s.xPos - s.size).min().getAsInt();
        int maxX = sensors.stream().mapToInt(s -> s.xPos + s.size).max().getAsInt();
        return checkRow(sensors, minX, row, maxX - minX + 1) - countBeaconsInRow(sensors, minX, row, maxX - minX + 1);
    }

    public BigInteger findBeacon(final Scanner input, int x, int y, int width, int height) {
        List<Sensor> sensors = tokenStream(input, "\\n", pattern, this::sensor).toList();
        Point beacon = findBeacon(sensors, x, y, width, height).get();
        return BigInteger.valueOf(beacon.x).multiply(BigInteger.valueOf(4000000)).add(BigInteger.valueOf(beacon.y));
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2022/aoc2022day15.txt";
    }

    private Sensor sensor(final Matcher matcher) {
        final int xPos = parseInt(matcher.group(1));
        final int yPos = parseInt(matcher.group(2));
        final int beaconX = parseInt(matcher.group(3));
        final int beaconY = parseInt(matcher.group(4));
        return new Sensor(xPos, yPos, beaconX, beaconY, abs(beaconX - xPos) + abs(beaconY - yPos));
    }

    public long checkRow(List<Sensor> sensors, int x, int y, int width) {
        if (sensors.stream().anyMatch(s -> s.fullyContains(x, y, width, 1))) {
            return width;
        }
        if (sensors.stream().allMatch(s -> s.notWithin(x, y, width, 1))) {
            return 0;
        }
        return checkRow(sensors, x, y, width / 2) + checkRow(sensors, x + width / 2, y, width - (width / 2));
    }

    public Optional<Point> findBeacon(List<Sensor> sensors, int x, int y, int width, int height) {
        if (sensors.stream().anyMatch(s -> s.fullyContains(x, y, width, height))) {
            return Optional.empty();
        }
        if (sensors.stream().allMatch(s -> s.notWithin(x, y, width, height))) {
            return Optional.of(new Point(x, y));
        }
        if (width > height) {
            return findBeacon(sensors, x, y, width / 2, height)
                    .or(() -> findBeacon(sensors, x + width / 2, y, width - (width / 2), height));
        } else {
            return findBeacon(sensors, x, y, width, height / 2)
                    .or(() -> findBeacon(sensors, x, y + height / 2, width, height - (height / 2)));
        }
    }

    private long countBeaconsInRow(List<Sensor> sensors, int x, int y, int width) {
        return sensors.stream()
                .filter(s -> s.beaconY == y && s.beaconX >= x && s.beaconX < x + width)
                .map(s -> new Point(s.beaconX, s.beaconY))
                .distinct()
                .count();
    }

    record Sensor(int xPos, int yPos, int beaconX, int beaconY, int size) {

        public boolean contains(int x, int y) {
            return abs(xPos - x) + abs(yPos - y) <= size;
        }

        private boolean fullyContains(int x, int y, int width, int height) {
            return contains(x, y) && contains(x + width - 1, y) && contains(x, y + height - 1) && contains(x + width - 1, y + height - 1);
        }

        public boolean notWithin(int x, int y, int width, int height) {
            return x > xPos + size
                    || y > yPos + size
                    || x + width - 1 < xPos - size
                    || y + height - 1 < yPos - size
                    || (x > xPos && y > yPos && !contains(x, y))
                    || (x + width - 1 < xPos && y > yPos && !contains(x + width - 1, y))
                    || (x + width - 1 < xPos && y + height - 1 < yPos && !contains(x + width - 1, y + height - 1))
                    || (x > xPos && y + height - 1 < yPos && !contains(x, y + height - 1));
        }
    }
}