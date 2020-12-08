package de.chrlembeck.aoc2019.day14;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formula {

    private static final Pattern REGEX = Pattern.compile("(\\d+) (\\w+)");

    private final FormulaUnit result;

    private final List<FormulaUnit> input = new ArrayList<>();

    public Formula(final BigInteger resultQuantity, final String result, final String input) {
        this.result = new FormulaUnit(resultQuantity, result);
        Arrays.stream(input.split(", "))
                .map(REGEX::matcher)
                .filter(Matcher::matches)
                .map(mr -> new FormulaUnit(new BigInteger(mr.group(1)), mr.group(2)))
                .forEach(this.input::add);
    }

    public FormulaUnit getResult() {
        return result;
    }

    public List<FormulaUnit> getInput() {
        return input;
    }
}