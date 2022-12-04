package de.chrlembeck.aoccommon;

import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public abstract class AbstractAocBase {

    protected static String getInput(final String location) {
        return FileUtil.readString(location);
    }

    public abstract Object part1(Scanner input);

    public abstract Object part2(Scanner input);

    public abstract String getInputLocation(int part);

    public void run() {
        final String input1 = getInput1();
        System.out.println("Input 1: \n'" + input1 + "'\n");
        final String input2 = getInput2();
        System.out.println("Input 2: \n'" + input2 + "'\n");
        try (Scanner scanner = new Scanner(input1)) {
            printResult(scanner, "Part 1: ", this::part1);
        }
        try (Scanner scanner = new Scanner(input2)) {
            printResult(scanner, "Part 2: ", this::part2);
        }
    }

    private void printResult(final Scanner scanner, final String prefix, final Function<Scanner, Object> function) {
        final long start = System.nanoTime();
        final String result = String.valueOf(function.apply(scanner));
        final long end = System.nanoTime();
        System.out.println(prefix + result);
        long duration = end - start;
        final long nanos = (duration) % 1_000_000;
        duration /= 1_000_000;
        final long millis = (duration) % 1_000;
        duration /= 1_000;
        final long seconds = (duration) % 60;
        duration /= 60;
        final long minutes = (duration) % 60;
        duration /= 60;
        final long hours = duration;
        System.out.printf("%02d hours, %02d minutes, %02d seconds, %03d millis, %06d nanos\n", hours, minutes, seconds,
                millis, nanos);
    }

    public String getInput1() {
        return getInput(getInputLocation(1));
    }

    public String getInput2() {
        return getInput(getInputLocation(2));
    }

    public static Matcher matchRegex(final Pattern pattern, final CharSequence input) {
        final Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            return matcher;
        } else {
            throw new IllegalArgumentException("Input '" + input + "' does not match pattern " + pattern.pattern());
        }
    }

    public Matcher matchRegex(final String regex, final CharSequence input) {
        return matchRegex(Pattern.compile(regex), input);
    }

    public static Stream<Matcher> matcherStream(Scanner input, String delimiterPattern, Pattern pattern) {
        return tokenStream(input, delimiterPattern, pattern, Function.identity());
    }

    public static <T> Stream<T> tokenStream(Scanner input, String delimiterPattern, Pattern pattern, Function <Matcher, T> tokenGenerator) {
        return input.useDelimiter(delimiterPattern).tokens().map(token -> matchRegex(pattern, token)).map(tokenGenerator);
    }
}