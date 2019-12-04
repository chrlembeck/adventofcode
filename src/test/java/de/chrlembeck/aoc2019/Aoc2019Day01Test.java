package de.chrlembeck.aoc2019;

import de.chrlembeck.aoc2019.day01.Aoc2019Day01;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2019Day01Test {

    @Test
    public void test1() {
        final Aoc2019Day01 day1 = new Aoc2019Day01();
        assertEquals("2", test("12", day1::part1));
        assertEquals("2", test("14", day1::part1));
        assertEquals("654", test("1969", day1::part1));
        assertEquals("33583", test("100756", day1::part1));
        assertEquals("3167282", test(day1.getInput1(), day1::part1));
    }

    @Test
    public void test2() {
        final Aoc2019Day01 day1 = new Aoc2019Day01();
        assertEquals("2", test("14", day1::part2));
        assertEquals("966", test("1969", day1::part2));
        assertEquals("50346", test("100756", day1::part2));
        assertEquals("4748063", test(day1.getInput2(), day1::part2));
    }
}