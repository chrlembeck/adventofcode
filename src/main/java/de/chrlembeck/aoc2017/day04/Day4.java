package de.chrlembeck.aoc2017.day04;

import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Predicate;

import de.chrlembeck.aoc2017.common.AbstractAocBase;

public class Day4 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Day4().run();
    }

    @Override
    public String part1(final Scanner input) {
        return countPasswords(input, this::passwordCheck1);
    }

    private boolean passwordCheck1(final String line) {
        try (Scanner scanner = new Scanner(line)) {
            scanner.useDelimiter(" ");
            final Set<String> known = new TreeSet<>();
            while (scanner.hasNext()) {
                final String password = scanner.next();
                if (!known.add(password)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean passwordCheck2(final String line) {
        try (Scanner scanner = new Scanner(line)) {
            scanner.useDelimiter(" ");
            final Set<Map<Character, Integer>> known = new HashSet<>();
            while (scanner.hasNext()) {
                final String password = scanner.next();
                final Map<Character, Integer> letters = new TreeMap<>();
                password.chars().forEach(charValue -> {
                    final Character character = Character.valueOf((char) charValue);
                    Integer count = letters.get(character);
                    if (count == null) {
                        count = Integer.valueOf(1);
                    } else {
                        count++;
                    }
                    letters.put(character, count);
                });

                if (!known.add(letters)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String part2(final Scanner input) {
        return countPasswords(input, this::passwordCheck2);
    }

    private String countPasswords(final Scanner input, final Predicate<String> passwordCheck) {
        int counter = 0;
        while (input.hasNextLine()) {
            final String line = input.nextLine();
            if (passwordCheck.test(line)) {
                counter++;
            }
        }
        return Integer.toString(counter);
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/day04.txt";
    }
}
