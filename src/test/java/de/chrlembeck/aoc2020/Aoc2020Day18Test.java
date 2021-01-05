package de.chrlembeck.aoc2020;

import de.chrlembeck.aoc2020.day18.Aoc2020Day18;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2020Day18Test {

    @Test
    public void test1() {
        final Aoc2020Day18 day = new Aoc2020Day18();
        assertEquals("3", test("1+2", day::part1));
        assertEquals("15", test("1+2*5", day::part1));
        assertEquals("11", test("1+(2*5)", day::part1));
        assertEquals("71", test("1 + 2 * 3 + 4 * 5 + 6", day::part1));
        assertEquals("26", test("2 * 3 + (4 * 5)", day::part1));
        assertEquals("437", test("5 + (8 * 3 + 9 + 3 * 4 * 3)", day::part1));
        assertEquals("12240", test("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))", day::part1));
        assertEquals("13632", test("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", day::part1));
        assertEquals("30753705453324", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2020Day18 day = new Aoc2020Day18();
        assertEquals("51", test("1 + (2 * 3) + (4 * (5 + 6))", day::part2));
        assertEquals("46", test("2 * 3 + (4 * 5)", day::part2));
        assertEquals("1445", test("5 + (8 * 3 + 9 + 3 * 4 * 3)", day::part2));
        assertEquals("669060", test("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))", day::part2));
        assertEquals("23340", test("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", day::part2));
        assertEquals("244817530095503", test(day.getInput1(), day::part2));
    }
}