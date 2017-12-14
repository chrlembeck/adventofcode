package de.chrlembeck.aoc2017.common;

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
        final long start = System.nanoTime();
        final String result = function.apply(scanner);
        final long end = System.nanoTime();
        System.out.println(prefix + result);
        long duration = end - start;
        final long nanos = (duration) % 1000000;
        duration /= 1000000;
        final long millis = (duration) % 1000;
        duration /= 1000;
        final long seconds = (duration) % 60;
        duration /= 60;
        final long minutes = (duration) % 60;
        duration /= 60;
        final long hours = duration;
        System.out.printf("%02d hours, %02d minuts, %02d seconds, %03d millis, %06d nanos\n", hours, minutes, seconds,
                millis, nanos);
    }

    public String getInput1() {
        return getInput(getInputLocation(1));
    }

    public String getInput2() {
        return getInput(getInputLocation(2));
    }
}