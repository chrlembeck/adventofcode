package de.chrlembeck.aoc2017.day08;

import static de.chrlembeck.aoccommon.Util.isNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.chrlembeck.aoccommon.AbstractAocBase;
import de.chrlembeck.aoccommon.Result;

public class Aoc2017Day08 extends AbstractAocBase {

    static Pattern regex = Pattern
            .compile(
                    "([a-z]+)\\s+([a-z]+)\\s+(\\-?[0-9]+)\\s+if\\s+([a-z]+)\\s+(<|>|!=|==|<=|>=)\\s+((\\-?[0-9]+))");

    public Result<Integer> calc(final Scanner input) {
        final Map<String, Integer> register = new HashMap<>();
        int maxTotal = Integer.MIN_VALUE;
        while (input.hasNextLine()) {
            final String line = input.nextLine();
            final Matcher matcher = regex.matcher(line);
            if (matcher.matches()) {
                final String varName = matcher.group(1);
                final String operation = matcher.group(2);
                final int amount = Integer.parseInt(matcher.group(3));
                final String conditionVariable = matcher.group(4);
                final String operator = matcher.group(5);
                final int threshold = Integer.parseInt(matcher.group(6));
                final Integer compareValue = isNull(register.get(conditionVariable), 0);
                Integer value = isNull(register.get(varName), 0);
                if (("==".equals(operator) && compareValue == threshold)
                        || ("!=".equals(operator) && compareValue != threshold)
                        || (">=".equals(operator) && compareValue >= threshold)
                        || ("<=".equals(operator) && compareValue <= threshold)
                        || ("<".equals(operator) && compareValue < threshold)
                        || (">".equals(operator) && compareValue > threshold)) {
                    value += "inc".equals(operation) ? amount : -amount;
                }
                register.put(varName, value);
                maxTotal = Math.max(maxTotal, value);
            } else {
                throw new IllegalArgumentException(line);
            }
        }
        final int max = register.values().stream().mapToInt(Integer::intValue).max().getAsInt();
        return new Result<>(max, maxTotal);
    }

    @Override
    public String part1(final Scanner input) {
        return calc(input).getPart1().toString();
    }

    @Override
    public String part2(final Scanner input) {
        return calc(input).getPart2().toString();
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2017/aoc2017day08.txt";
    }
}