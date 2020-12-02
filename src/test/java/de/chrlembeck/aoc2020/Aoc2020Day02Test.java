package de.chrlembeck.aoc2020;

import de.chrlembeck.aoc2020.day02.Aoc2020Day02;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2020Day02Test {

    @Test
    public void test1() {
        final Aoc2020Day02 day = new Aoc2020Day02();
        assertEquals("2", test("1-3 a: abcde\n1-3 b: cdefg\n2-9 c: ccccccccc", day::part1));
        assertEquals("378", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2020Day02 day = new Aoc2020Day02();
        assertEquals("1", test("1-3 a: abcde\n1-3 b: cdefg\n2-9 c: ccccccccc", day::part2));
        assertEquals("280", test(day.getInput1(), day::part2));
    }
}