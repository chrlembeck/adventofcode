package de.chrlembeck.aoc2017.day12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import de.chrlembeck.aoc2017.common.AbstractAocBase;

public class Day12 extends AbstractAocBase {

    Pattern regex = Pattern.compile("(\\d+)\\s*<->\\s*([\\s,\\d]+)");

    public static void main(final String[] args) {
        new Day12().run();
    }

    @Override
    public String part1(final Scanner input) {
        return Integer.toString(collectGroup(createMap(input), 0).size());
    }

    @Override
    public String part2(final Scanner input) {
        final Map<Integer, Set<Integer>> map = createMap(input);
        final List<Set<Integer>> groups = new ArrayList<>();
        while (!map.isEmpty()) {
            groups.add(collectGroup(map, map.keySet().iterator().next()));
        }
        return Integer.toString(groups.size());
    }

    private Set<Integer> collectGroup(final Map<Integer, Set<Integer>> map, final Integer start) {
        final Set<Integer> result = new TreeSet<Integer>();
        final Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            final Integer current = queue.poll();
            if (result.add(current)) {
                queue.addAll(map.remove(current));
            }
        }
        return result;
    }

    private Map<Integer, Set<Integer>> createMap(final Scanner input) {
        final Map<Integer, Set<Integer>> map = new TreeMap<>();
        while (input.hasNextLine()) {
            final String line = input.nextLine();
            final Matcher matcher = regex.matcher(line);
            if (matcher.matches()) {
                final Integer processId = Integer.valueOf(matcher.group(1));
                final String rhs = matcher.group(2).trim();
                map.put(processId, Arrays.stream(rhs.split(", ")).map(Integer::valueOf).collect(Collectors.toSet()));
            }
        }
        return map;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/day12.txt";
    }
}