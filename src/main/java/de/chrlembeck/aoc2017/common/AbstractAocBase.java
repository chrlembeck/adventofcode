package de.chrlembeck.aoc2017.common;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.function.Function;

public abstract class AbstractAocBase {

    protected static String getInput(final String location) {
        return FileUtil.readString(location);
    }

    public abstract String part1(Scanner input);

    public abstract String part2(Scanner input);

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

    private void printResult(final Scanner scanner, final String prefix, final Function<Scanner, String> function) {
        final LocalTime start = LocalTime.now();
        System.out.println(prefix + function.apply(scanner));
        final LocalTime end = LocalTime.now();
        final Duration duration = Duration.between(start, end);
        System.out.println(LocalTime.MIDNIGHT.plus(duration).format(
                DateTimeFormatter.ofPattern("HH 'hours', mm ' minutes', ss 'seconds', AAA 'milliseconds'")));
    }

    public String getInput1() {
        return getInput(getInputLocation(1));
    }

    public String getInput2() {
        return getInput(getInputLocation(2));
    }
}