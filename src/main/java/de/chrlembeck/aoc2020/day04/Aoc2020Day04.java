package de.chrlembeck.aoc2020.day04;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2020Day04 extends AbstractAocBase {

    private static final Pattern ENTRY_PATTERN = Pattern.compile("(\\w+):([#\\w]+)\\s*");

    private static final Pattern HCL_PATTERN = Pattern.compile("#[\\da-f]{6}");

    private static final Pattern PID_PATTERN = Pattern.compile("\\d{9}");

    private static final Pattern ECL_PATTERN = Pattern.compile("(amb|blu|brn|gry|grn|hzl|oth)");

    private static final Pattern HGT_PATTERN = Pattern.compile("(\\d+)(in|cm)");

    public static void main(final String[] args) {
        new Aoc2020Day04().run();
    }

    public static final String[] REQUIRED_FIELDS = new String[] { "byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid" };

    private Predicate<Map<String, String>> hasRequiredFields = passport -> Arrays.stream(REQUIRED_FIELDS).allMatch(passport::containsKey);

    private Predicate<Map<String, String>> fieldsValid =
            passport -> between(passport.get("byr"), 1920, 2002)
                    && between(passport.get("iyr"), 2010, 2020)
                    && between(passport.get("eyr"), 2020, 2030)
                    && isHeightValid(passport.get("hgt"))
                    && HCL_PATTERN.matcher(passport.get("hcl")).matches()
                    && PID_PATTERN.matcher(passport.get("pid")).matches()
                    && ECL_PATTERN.matcher(passport.get("ecl")).matches();

    @Override
    public Object part1(final Scanner input) {
        return count(input, hasRequiredFields);
    }

    @Override
    public Object part2(final Scanner input) {
        return count(input, hasRequiredFields.and(fieldsValid));
    }

    private int count(Scanner input, Predicate<Map<String, String>> validator) {
        int validCounter = 0;
        while (input.hasNextLine()) {
            if (validator.test(parsePassport(input))) {
                validCounter++;
            }
        }
        return validCounter;
    }

    private boolean isHeightValid(String hgt) {
        Matcher hgtMatcher = HGT_PATTERN.matcher(hgt);
        if (hgtMatcher.matches()) {
            if (hgtMatcher.group(2).equals("cm")) {
                return between(hgtMatcher.group(1), 150, 193);
            } else {
                return between(hgtMatcher.group(1), 59, 76);
            }
        } else {
            return false;
        }
    }

    private boolean between(String value, int min, int max) {
        try {
            Integer number = Integer.parseInt(value);
            return number >= min && number <= max;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private Map<String, String> parsePassport(Scanner input) {
        Map<String, String> passport = new TreeMap<>();
        String line;
        while (input.hasNextLine() && !(line = input.nextLine()).equals("")) {
            Matcher matcher = ENTRY_PATTERN.matcher(line);
            while (matcher.find()) {
                passport.put(matcher.group(1), matcher.group(2));
            }
        }
        return passport;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2020/aoc2020day04.txt";
    }
}