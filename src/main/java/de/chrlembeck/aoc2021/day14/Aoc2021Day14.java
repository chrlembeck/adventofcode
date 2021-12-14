package de.chrlembeck.aoc2021.day14;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2021Day14 extends AbstractAocBase {

    public static final Pattern REGEX = Pattern.compile("(\\w\\w) -> (\\w)");

    public static void main(final String[] args) {
        new Aoc2021Day14().run();
    }

    @Override
    public Object part1(final Scanner input) {
        return calc(input, 10);
    }

    @Override
    public Object part2(final Scanner input) {
        return calc(input, 40);
    }

    public long calc(final Scanner input, final int rounds) {
        final String polymer = input.nextLine();
        input.nextLine();
        Map<String, Rule> rules = new TreeMap<>();

        while (input.hasNext()) {
            Matcher matcher = matchRegex(REGEX, input.nextLine());
            Rule rule = new Rule(matcher.group(1), matcher.group(2));
            rules.put(rule.getLhs(), rule);
        }
        Counter counter = new Counter();
        for (int i = 0; i < polymer.length() - 1; i++) {
            counter.add(polymer.substring(i, i + 2), 1);
        }

        for (int level = 0; level < rounds; level++) {
            Counter newCounter = new Counter();
            for (Map.Entry<String, Long> entry : counter.entrySet()) {
                Rule rule = rules.get(entry.getKey());
                newCounter.add(entry.getKey().substring(0, 1) + rule.getInsertion(), entry.getValue());
                newCounter.add(rule.getInsertion() + entry.getKey().substring(1, 2), entry.getValue());
            }
            counter = newCounter;
        }
        Map<Character, Long> characters = counter.countCharacters(polymer.charAt(0));

        LongSummaryStatistics stats = characters.values().stream().mapToLong(Long::longValue).summaryStatistics();
        return stats.getMax() - stats.getMin();
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2021/aoc2021day14.txt";
    }
}