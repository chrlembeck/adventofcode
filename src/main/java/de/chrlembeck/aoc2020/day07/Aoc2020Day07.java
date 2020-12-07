package de.chrlembeck.aoc2020.day07;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Aoc2020Day07 extends AbstractAocBase {

    private static final Pattern RULE_PATTERN = Pattern.compile("(\\w+ \\w+) bags contain ([\\w\\d, ]+)\\.");

    private static final Pattern CONTENT_PATTERN = Pattern.compile("(\\d+) (\\w+ \\w+) (bag|bags)\\z");

    public static void main(final String[] args) {
        new Aoc2020Day07().run();
    }

    @Override
    public Object part1(final Scanner input) {
        final Map<String, List<Content>> rules = readRules(input);
        return rules.values().stream()
                .filter(c -> contains(c, "shiny gold", rules))
                .count();
    }

    @Override
    public Object part2(final Scanner input) {
        final Map<String, List<Content>> rules = readRules(input);
        return countMembers(rules.get("shiny gold"), rules);
    }

    private Map<String, List<Content>> readRules(final Scanner input) {
        return input.findAll(RULE_PATTERN)
                .collect(Collectors.toMap(mr -> mr.group(1), this::createRules));
    }

    private boolean contains(final List<Content> contents, final String color, final Map<String, List<Content>> rules) {
        return contents.stream().anyMatch(c -> c.getColor().equals(color)) || contents.stream().anyMatch(c -> contains(rules.get(c.getColor()), color, rules));
    }

    private List<Content> createRules(final MatchResult rulesResult) {
        return Arrays.stream(rulesResult.group(2).split(", "))
                .filter(Predicate.not("no other bags"::equals))
                .map(this::createRule)
                .collect(Collectors.toList());
    }

    private Content createRule(final String content) {
        final Matcher matcher = CONTENT_PATTERN.matcher(content);
        matcher.matches();
        return new Content(Integer.parseInt(matcher.group(1)), matcher.group(2));
    }

    private long countMembers(final List<Content> contents, final Map<String, List<Content>> rules) {
        return contents.stream()
                .mapToLong(con -> con.getQuantity() * (1 + countMembers(rules.get(con.getColor()), rules)))
                .sum();
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2020/aoc2020day07.txt";
    }

    static class Content {

        private final int quantity;

        private final String color;

        public Content(final int quantity, final String color) {
            this.quantity = quantity;
            this.color = color;
        }

        public int getQuantity() {
            return quantity;
        }

        public String getColor() {
            return color;
        }
    }
}