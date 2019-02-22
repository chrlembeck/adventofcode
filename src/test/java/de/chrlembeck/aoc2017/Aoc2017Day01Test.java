package de.chrlembeck.aoc2017;

import de.chrlembeck.aoc2017.day01.Aoc2017Day01;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2017Day01Test {

    @Test
    public void test1() {
        final Aoc2017Day01 day1 = new Aoc2017Day01();
        assertEquals("3", test("1122", day1::part1));
        assertEquals("4", test("1111", day1::part1));
        assertEquals("0", test("1234", day1::part1));
        assertEquals("9", test("91212129", day1::part1));
        assertEquals("1175", test(day1.getInput1(), day1::part1));
    }

    @Test
    public void test2() {
        final Aoc2017Day01 day1 = new Aoc2017Day01();
        assertEquals("6", test("1212", day1::part2));
        assertEquals("0", test("1221", day1::part2));
        assertEquals("4", test("123425", day1::part2));
        assertEquals("12", test("123123", day1::part2));
        assertEquals("4", test("12131415", day1::part2));
        assertEquals("1166", test(day1.getInput2(), day1::part2));
    }
}