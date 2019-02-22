package de.chrlembeck.aoc2017.day25;

import java.util.Scanner;

import de.chrlembeck.aoccommon.AbstractAocBase;
import de.chrlembeck.util.collections.BidirectionalGrowingArray;

public class Aoc2017Day25 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2017Day25().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        final char startState = matchRegex("Begin in state ([A-Z]).", input.nextLine()).group(1).charAt(0);
        final long stepCount = Long.parseLong(matchRegex("Perform a diagnostic checksum after (\\d*) steps.", input.nextLine()).group(1));
        State[] states = new State[26];
        while (input.hasNextLine()) {
            input.nextLine();
            final State state = parseState(input);
            states[state.stateIdentifier - 'A'] = state;
        }

        final BidirectionalGrowingArray<Integer> array = new BidirectionalGrowingArray<>(Integer[]::new);
        char currentState = startState;
        int pos = 0;
        array.put(0, 0);
        for (long i = 0; i < stepCount; i++) {
            final State state = states[currentState - 'A'];
            final Rule rule = array.get(pos).intValue() == 0 ? state.zeroRule : state.oneRule;
            array.put(pos, rule.valueToWrite);
            pos += rule.offset;
            if (array.get(pos) == null) {
                array.put(pos, 0);
            }
            currentState = rule.nextState;
        }
        int checksum = 0;
        for (final Integer element: array) {
            if (element.intValue() == 1) {
                checksum++;
            }
        }
        return checksum;
    }

    private State parseState(final Scanner input) {
        final State state = new State();
        state.stateIdentifier = matchRegex("In state ([A-Z]):", input.nextLine()).group(1).charAt(0);
        for (int i = 0; i <= 1; i++) {
            final Rule rule = parseRule(input);
            if (rule.condition == 0) {
                state.zeroRule = rule;
            } else {
                state.oneRule = rule;
            }
        }
        return state;
    }

    private Rule parseRule(final Scanner input) {
        final Rule rule = new Rule();
        rule.condition = Integer.parseInt(matchRegex("  If the current value is (\\d+):", input.nextLine()).group(1));
        rule.valueToWrite = Integer.parseInt(matchRegex("    - Write the value (\\d+).", input.nextLine()).group(1));
        rule.offset = matchRegex("    - Move one slot to the (right|left).", input.nextLine()).group(1).equalsIgnoreCase("left")?-1:1;
        rule.nextState = matchRegex("    - Continue with state ([A-Z]).", input.nextLine()).group(1).charAt(0);
        return rule;
    }

    @Override
    public String part2(final Scanner input) {
        return null;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2017/aoc2017day25.txt";
    }

    static class State {
        char stateIdentifier;
        Rule zeroRule;
        Rule oneRule;
    }

    static class Rule {
        int condition;
        int valueToWrite;
        int offset;
        char nextState;
    }
}