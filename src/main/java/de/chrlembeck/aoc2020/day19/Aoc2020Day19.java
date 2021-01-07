package de.chrlembeck.aoc2020.day19;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Function;

public class Aoc2020Day19 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2020Day19().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        return countMatches(input, Function.identity()::apply);
    }

    @Override
    public Integer part2(final Scanner input) {
        return countMatches(input, t -> {
            t.put(8, parseRule("42 | 42 8"));
            t.put(11, parseRule("42 31 | 42 11 31"));
        });
    }

    public int countMatches(final Scanner input, final Consumer<Map<Integer, int[][]>> ruleChanger) {
        String[] ruleParts;
        int nonTerminalA = -1;
        int nonTerminalB = -1;
        final Map<Integer, int[][]> rules = new TreeMap<>();
        while ((ruleParts = input.nextLine().split(": ")).length > 1) {
            final int index = Integer.parseInt(ruleParts[0]);
            final String rhs = ruleParts[1];
            if (rhs.charAt(0) == '\"') {
                if (rhs.charAt(1) == 'a') {
                    nonTerminalA = index;
                } else {
                    nonTerminalB = index;
                }
            } else {
                rules.put(index, parseRule(rhs));
            }
        }
        ruleChanger.accept(rules);
        int counter = 0;
        while (input.hasNextLine()) {
            if (tryToBuild(toNonTerminals(input.nextLine(), nonTerminalA, nonTerminalB), rules.get(0)[0], rules, nonTerminalA, nonTerminalB)) {
                counter++;
            }
        }
        return counter;
    }

    private int[][] parseRule(final String rhs) {
        return Arrays.stream(rhs.split(" \\| "))
                .map(a -> Arrays.stream(a.split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .toArray(int[][]::new);
    }

    private boolean tryToBuild(final int[] nonTerminals, final int[] currentRule, final Map<Integer, int[][]> rules, final int nonterminalA,
            final int nonterminalB) {
        if (nonTerminals.length == 0 || currentRule.length == 0) {
            return currentRule.length == nonTerminals.length;
        }
        final int firstRuleSymbol = currentRule[0];
        if (firstRuleSymbol == nonTerminals[0]) {
            return tryToBuild(removeFirst(nonTerminals), removeFirst(currentRule), rules, nonterminalA, nonterminalB);
        } else {
            if (firstRuleSymbol == nonterminalA || firstRuleSymbol == nonterminalB) {
                return false;
            }
        }
        final int lastRuleSymbol = currentRule[currentRule.length - 1];
        if (lastRuleSymbol == nonTerminals[nonTerminals.length - 1]) {
            return tryToBuild(removeLast(nonTerminals), removeLast(currentRule), rules, nonterminalA, nonterminalB);
        } else {
            if (lastRuleSymbol == nonterminalA || lastRuleSymbol == nonterminalB) {
                return false;
            }
        }
        return Arrays.stream(rules.get(firstRuleSymbol)).anyMatch(alt -> tryToBuild(nonTerminals, replaceFirst(currentRule, alt), rules, nonterminalA, nonterminalB));
    }

    private int[] replaceFirst(final int[] array, final int... replacement) {
        final int[] newArray = new int[array.length - 1 + replacement.length];
        System.arraycopy(replacement, 0, newArray, 0, replacement.length);
        System.arraycopy(array, 1, newArray, replacement.length, array.length - 1);
        return newArray;
    }

    private int[] removeFirst(final int... original) {
        return Arrays.copyOfRange(original, 1, original.length);
    }

    private int[] removeLast(final int... original) {
        return Arrays.copyOfRange(original, 0, original.length - 1);
    }

    private int[] toNonTerminals(final String line, final int nonTerminalA, final int nonTerminalB) {
        return line.chars().sequential().map(ch -> ch == 'a' ? nonTerminalA : nonTerminalB).toArray();
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2020/aoc2020day19.txt";
    }
}