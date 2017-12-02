package de.chrlembeck.aoc2017.common;

import java.util.Scanner;

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
            System.out.println("Part 1: " + part1(scanner));
        }
        try (Scanner scanner = new Scanner(input2)) {
            System.out.println("Part 2: " + part2(scanner));
        }
    }

    public String getInput1() {
        return getInput(getInputLocation(1));
    }

    public String getInput2() {
        return getInput(getInputLocation(2));
    }
}