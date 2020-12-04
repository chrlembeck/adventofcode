package de.chrlembeck.aoc2019.day06;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Aoc2019Day06 extends AbstractAocBase {

    private final static Pattern REGEX = Pattern.compile("(\\w+)\\)(\\w+)");

    public static void main(final String[] args) {
        new Aoc2019Day06().run();
    }

    @Override
    public Object part1(final Scanner input) {
        final Map<String, String> map = generateOrbitMap(input);
        return map.keySet().stream().mapToInt(key -> length(key, map)).sum();
    }

    private Map<String, String> generateOrbitMap(final Scanner input) {
        return input.findAll(REGEX).collect(Collectors.toMap(r -> r.group(2), r -> r.group(1)));
    }

    private int length(final String key, final Map<String, String> map) {
        final String next = map.get(key);
        if (next == null) {
            return 0;
        } else {
            return 1 + length(next, map);
        }
    }

    @Override
    public Object part2(final Scanner input) {
        final Map<String, String> map = generateOrbitMap(input);
        final List<String> you = spur(map, "YOU");
        final List<String> san = spur(map, "SAN");
        int orbitIndex = 1;
        while (orbitIndex <= you.size() && orbitIndex <= san.size() && you.get(you.size() - orbitIndex).equals(san.get(san.size() - orbitIndex))) {
            orbitIndex++;
        }
        return san.size() + you.size() - 2 * orbitIndex;
    }

    private List<String> spur(final Map<String, String> orbitMap, String searchValue) {
        final List<String> spur = new ArrayList<>();
        while (searchValue != null) {
            spur.add(searchValue);
            searchValue = orbitMap.get(searchValue);
        }
        return spur;
    }//338 to low

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2019/aoc2019day06.txt";
    }
}