package de.chrlembeck.aoc2017;

import de.chrlembeck.aoc2017.day08.Aoc2017Day08;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2017Day08Test {

    @Test
    public void test1() {
        final Aoc2017Day08 day8 = new Aoc2017Day08();
        assertEquals("1", test("b inc 5 if a > 1\n" +
                "a inc 1 if b < 5\n" +
                "c dec -10 if a >= 1\n" +
                "c inc -20 if c == 10", day8::part1));
        assertEquals("5752", test(day8.getInput1(), day8::part1));
    }

    @Test
    public void test2() {
        final Aoc2017Day08 day8 = new Aoc2017Day08();
        assertEquals("10", test("b inc 5 if a > 1\n" +
                "a inc 1 if b < 5\n" +
                "c dec -10 if a >= 1\n" +
                "c inc -20 if c == 10", day8::part2));
        assertEquals("6366", test(day8.getInput2(), day8::part2));
    }
}