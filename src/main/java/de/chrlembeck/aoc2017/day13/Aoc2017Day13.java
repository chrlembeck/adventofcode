package de.chrlembeck.aoc2017.day13;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.chrlembeck.aoccommon.AbstractAocBase;

public class Aoc2017Day13 extends AbstractAocBase {

    Pattern regex = Pattern.compile("(\\d+):\\s*(\\d+)");

    public static void main(final String[] args) {
        new Aoc2017Day13().run();
    }

    @Override
    public String part1(final Scanner input) {
        final Map<Integer, Integer> map = createMap(input);
        int severity = 0;
        for (final Map.Entry<Integer, Integer> entry : map.entrySet()) {
            final int layer = entry.getKey();
            final int range = entry.getValue();
            if (caught(layer, range, layer)) {
                severity += layer * range;
            }
        }
        return Integer.toString(severity);
    }

    @Override
    public String part2(final Scanner input) {
        final Map<Integer, Integer> map = createMap(input);
        int delay = 0;
        boolean caught;
        do {
            caught = false;
            for (final Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (caught(entry.getKey(), entry.getValue(), delay + entry.getKey())) {
                    caught = true;
                    delay++;
                    break;
                }
            }
        } while (caught);
        return Integer.toString(delay);
    }

    private Map<Integer, Integer> createMap(final Scanner input) {
        final Map<Integer, Integer> map = new TreeMap<>();
        while (input.hasNextLine()) {
            final Matcher matcher = regex.matcher(input.nextLine());
            if (matcher.matches()) {
                map.put(Integer.valueOf(matcher.group(1)), Integer.valueOf(matcher.group(2)));
            }
        }
        return map;
    }

    public static final boolean caught(final int layer, final int range, final int round) {
        return round % ((range - 1) << 1) == 0;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2017/day13.txt";
    }
}