package de.chrlembeck.aoc2020.day13;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.function.Predicate;

public class Aoc2020Day13 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2020Day13().run();
    }

    @Override
    public Object part1(final Scanner input) {
        long earliestDepartureTime = Long.parseLong(input.nextLine());
        long[] busses = Arrays.stream(input.nextLine().split(",")).filter(Predicate.not("x"::equals)).mapToLong(Long::parseLong).toArray();
        long min = Long.MAX_VALUE;
        long minLine = -1;
        for (long line: busses) {
            long waitTime = (line - (earliestDepartureTime % line))%line;
            if (waitTime < min) {
                min = waitTime;
                minLine = line;
            }
        }


        return minLine * min;
    }

    // http://www.arndt-bruenner.de/mathe/scripts/diophant.htm#script
    @Override
    public Object part2(final Scanner input) {
        long earliestDepartureTime = Long.parseLong(input.nextLine());
        String[] busses = input.nextLine().split(",");
        Map<Integer, Integer> lines = new TreeMap<>();
        for (int i = 0; i < busses.length; i++) {
            if (!"x".equals(busses[i])) {
                lines.put(Integer.parseInt(busses[i]), i);
            }
        }
        System.out.println(lines);
        return null;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2020/aoc2020day13.txt";
    }
}