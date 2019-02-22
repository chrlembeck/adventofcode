package de.chrlembeck.aoc2015;

import de.chrlembeck.aoc2015.day07.Aoc2015Day07;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2015Day07Test {

    @Test
    public void test1() {
        final Aoc2015Day07 day = new Aoc2015Day07();
        assertEquals("65079", test("123 -> x\n" +
                "456 -> y\n" +
                "x AND y -> d\n" +
                "x OR y -> e\n" +
                "x LSHIFT 2 -> f\n" +
                "y RSHIFT 2 -> g\n" +
                "NOT x -> h\n" +
                "NOT y -> a", day::part1));
        assertEquals("16076", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2015Day07 day = new Aoc2015Day07();
        assertEquals("2797", test(day.getInput2(), day::part2));
    }
}