package de.chrlembeck.aoc2015.day14;

import de.chrlembeck.aoccommon.AbstractAocBase;
import de.chrlembeck.aoccommon.MathUtil;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Aoc2015Day14 extends AbstractAocBase {

    public static final Pattern REGEX = Pattern.compile("(\\w+) can fly (\\d+) km/s for (\\d+) seconds, but then must rest for (\\d+) seconds.");

    public static void main(final String[] args) {
        new Aoc2015Day14().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        List<Reindeer> reindeers = input.findAll(REGEX).map(Reindeer::new).collect(Collectors.toList());
        int max = 0;
        for (Reindeer reindeer : reindeers) {
            int distance = reindeer.getDistance(2503);
            max = Math.max(max, distance);
        }

        return max;
    }

    @Override
    public Integer part2(final Scanner input) {
        List<Reindeer> reindeers = input.findAll(REGEX).map(Reindeer::new).collect(Collectors.toList());
        int[] points = new int[reindeers.size()];
        for (int time = 1; time <= 2503; time++) {
            int[] distances = new int[reindeers.size()];
            int max = 0;
            for (int idx = 0; idx < reindeers.size(); idx++) {
                distances[idx] = reindeers.get(idx).getDistance(time);
                max = Math.max(max, distances[idx]);
            }
            for (int idx = 0; idx < reindeers.size(); idx++) {
                if (distances[idx] == max) {
                    points[idx]++;
                }
            }
        }
        return MathUtil.max(points);
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2015/aoc2015day14.txt";
    }
}