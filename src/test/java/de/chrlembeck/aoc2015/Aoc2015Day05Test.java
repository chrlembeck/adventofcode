package de.chrlembeck.aoc2015;

import de.chrlembeck.aoc2015.day05.Aoc2015Day05;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2015Day05Test {

    @Test
    public void test1() {
        final Aoc2015Day05 day = new Aoc2015Day05();
        assertEquals("1", test("ugknbfddgicrmopn", day::part1));
        assertEquals("1", test("aaa", day::part1));
        assertEquals("0", test("jchzalrnumimnmhp", day::part1));
        assertEquals("0", test("haegwjzuvuyypxyu", day::part1));
        assertEquals("0", test("dvszwmarrgswjxmb", day::part1));
        assertEquals("2", test("ugknbfddgicrmopn\naaa\njchzalrnumimnmhp\nhaegwjzuvuyypxyu", day::part1));
        assertEquals("258", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2015Day05 day = new Aoc2015Day05();
        assertEquals("1", test("qjhvhtzxzqqjkmpb", day::part2));
        assertEquals("1", test("xxyxx", day::part2));
        assertEquals("0", test("uurcxstgmygtbstg", day::part2));
        assertEquals("0", test("ieodomkazucvgmuy", day::part2));
        assertEquals("0", test("fxxxyx", day::part2));
        assertEquals("1", test("yxxxyx", day::part2));
        assertEquals("1", test("yxyx", day::part2));
        assertEquals("53", test(day.getInput2(), day::part2));
    }
}