package de.chrlembeck.aoc2017;

import de.chrlembeck.aoc2017.day13.Aoc2017Day13;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2017Day13Test {

    @Test
    public void test1() {
        final Aoc2017Day13 day13 = new Aoc2017Day13();
        assertEquals("24", test("0: 3\n" +
                "1: 2\n" +
                "4: 4\n" +
                "6: 4", day13::part1));
        assertEquals("788", test(day13.getInput1(), day13::part1));
    }

    @Test
    public void test2() {
        final Aoc2017Day13 day13 = new Aoc2017Day13();
        assertEquals("10", test("0: 3\n" +
                "1: 2\n" +
                "4: 4\n" +
                "6: 4", day13::part2));
        assertEquals("3905748", test(day13.getInput2(), day13::part2));
    }
}