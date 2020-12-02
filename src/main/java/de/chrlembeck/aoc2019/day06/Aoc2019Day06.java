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
        Map<String, String> map = generateOrbitMap(input);
        return map.keySet().stream().mapToInt(key -> length(key, map)).sum();
    }

    private Map<String, String> generateOrbitMap(Scanner input) {
        return input.findAll(REGEX).collect(Collectors.toMap(r -> r.group(2), r -> r.group(1)));
    }

    private int length(String key, Map<String, String> map) {
        String next = map.get(key);
        if (next == null) {
            return 0;
        } else {
            return 1 + length(next, map);
        }
    }

    @Override
    public Object part2(final Scanner input) {
        Map<String, String> map = generateOrbitMap(input);
        List<String> you = spur(map, "YOU");
        List<String> san = spur(map, "SAN");
        int i = 1;
        while (i <= you.size() && i <= san.size() && you.get(you.size() - i).equals(san.get(san.size() - i))) {
            i++;
        }
        return san.size() + you.size() - 2 * i;
    }

    private List<String> spur(Map<String, String> orbitMap, String searchValue) {
        List<String> spur = new ArrayList<>();
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