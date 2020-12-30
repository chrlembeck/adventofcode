package de.chrlembeck.aoc2020.day06;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;

public class Aoc2020Day06 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2020Day06().run();
    }

    @Override
    public Object part1(final Scanner input) {
        int answerCounter = 0;
        while (input.hasNextLine()) {
            answerCounter += parseAllGroupAnswers(input).size();
        }
        return answerCounter;
    }

    private Set<Character> parseAllGroupAnswers(final Scanner input) {
        final Set<Character> answers = new TreeSet<>();
        String line;
        while (input.hasNextLine() && !"".equals(line = input.nextLine())) {
            for (int i = 0; i < line.length(); i++) {
                answers.add(line.charAt(i));
            }
        }
        return answers;
    }

    private Set<Character> parseCommonGroupAnswers(final Scanner input) {
        Set<Character> commonAnswers = null;
        String line;
        while (input.hasNextLine() && !"".equals(line = input.nextLine())) {
            final Set<Character> personAnswers = new TreeSet<>();
            for (int characterIdx = 0; characterIdx < line.length(); characterIdx++) {
                personAnswers.add(line.charAt(characterIdx));
            }
            if (commonAnswers == null) {
                commonAnswers = personAnswers;
            } else {
                commonAnswers.removeIf(Predicate.not(personAnswers::contains));
            }
        }
        return commonAnswers;
    }

    @Override
    public Object part2(final Scanner input) {
        int answerCounter = 0;
        while (input.hasNextLine()) {
            answerCounter += parseCommonGroupAnswers(input).size();
        }
        return answerCounter;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2020/aoc2020day06.txt";
    }
}