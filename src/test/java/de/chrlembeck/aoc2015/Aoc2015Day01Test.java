package de.chrlembeck.aoc2015;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import de.chrlembeck.aoc2015.day01.Aoc2015Day01;

@RunWith(JUnitPlatform.class)
public class Aoc2015Day01Test {

    @Test
    public void test1() {
        final Aoc2015Day01 day = new Aoc2015Day01();
        assertEquals("0", test("()()", day::part1));
        assertEquals("0", test("(())", day::part1));
        assertEquals("3", test("(((", day::part1));
        assertEquals("3", test("(()(()(", day::part1));
        assertEquals("-1", test("())", day::part1));
        assertEquals("280", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2015Day01 day = new Aoc2015Day01();
        assertEquals("1", test(")", day::part2));
        assertEquals("5", test("()())", day::part2));
        assertEquals("1797", test(day.getInput2(), day::part2));
    }
}