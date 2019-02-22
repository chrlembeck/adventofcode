package de.chrlembeck.aoc2015.day09;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.IntBinaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.chrlembeck.aoccommon.AbstractAocBase;
import de.chrlembeck.aoccommon.Permutation;

public class Aoc2015Day09 extends AbstractAocBase {

    Pattern regex = Pattern.compile("(\\w+) to (\\w+) = (\\d+)");

    public static void main(final String[] args) {
        new Aoc2015Day09().run();
    }

    @Override
    public Object part1(final Scanner input) {
        return calc(input, Integer.MAX_VALUE, Math::min);
    }

    @Override
    public Integer part2(final Scanner input) {
        return calc(input, 0, Math::max);
    }

    public Integer calc(final Scanner input, final int startValue, final IntBinaryOperator comparator) {
        final Map<String, Map<String, Integer>> map = new HashMap<>();
        final Set<String> names = new TreeSet<>();
        while (input.hasNextLine()) {
            final Matcher matcher = matchRegex(regex, input.nextLine());
            final String src = matcher.group(1);
            final String dest = matcher.group(2);
            final Integer dist = Integer.parseInt(matcher.group(3));
            putMapValue(map, src, dest, dist);
            putMapValue(map, dest, src, dist);
            names.add(src);
            names.add(dest);
        }
        final Permutation<String> permGen = new Permutation<>(names, String[]::new);
        int result = startValue;
        while (permGen.hasNext()) {
            final String[] perm = permGen.next();
            int length = 0;
            String pos = perm[0];
            for (int i = 1; i < perm.length; i++) {
                length += map.get(pos).get(pos = perm[i]);
            }
            result = comparator.applyAsInt(result, length);
        }
        return result;
    }

    public static <K1, K2, V> void putMapValue(final Map<K1, Map<K2, V>> map, final K1 key1, final K2 key2,
            final V value) {
        Map<K2, V> tmp = map.get(key1);
        if (tmp == null) {
            map.put(key1, tmp = new HashMap<>());
        }
        tmp.put(key2, value);
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2015/aoc2015day09.txt";
    }
}