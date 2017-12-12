package de.chrlembeck.aoc2017.day12;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class Day12Test {

    static String test(final String input, final Function<Scanner, String> function) {
        try (Scanner scanner = new Scanner(input)) {
            return function.apply(scanner);
        }
    }

    @Test
    public void test1() {
        final Day12 day12 = new Day12();
        assertEquals("6", test("0 <-> 2\n" +
                "1 <-> 1\n" +
                "2 <-> 0, 3, 4\n" +
                "3 <-> 2, 4\n" +
                "4 <-> 2, 3, 6\n" +
                "5 <-> 6\n" +
                "6 <-> 4, 5", day12::part1));
        assertEquals("175", test(day12.getInput1(), day12::part1));
    }

    @Test
    public void test2() {
        final Day12 day12 = new Day12();
        assertEquals("2", test("0 <-> 2\n" +
                "1 <-> 1\n" +
                "2 <-> 0, 3, 4\n" +
                "3 <-> 2, 4\n" +
                "4 <-> 2, 3, 6\n" +
                "5 <-> 6\n" +
                "6 <-> 4, 5", day12::part2));
        assertEquals("213", test(day12.getInput2(), day12::part2));
    }
}