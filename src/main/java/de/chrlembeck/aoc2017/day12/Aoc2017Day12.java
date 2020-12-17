package de.chrlembeck.aoc2017.day12;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Aoc2017Day12 extends AbstractAocBase {

    private static final Pattern REGEX = Pattern.compile("(\\d+)\\s*<->\\s*([\\s,\\d]+)");

    public static void main(final String[] args) {
        new Aoc2017Day12().run();
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
        final Set<Integer> result = new TreeSet<>();
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
            final Matcher matcher = REGEX.matcher(line);
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
        return "/input/aoc2017/aoc2017day12.txt";
    }
}