package de.chrlembeck.aoc2017;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import de.chrlembeck.aoc2017.day04.Aoc2017Day04;

@RunWith(JUnitPlatform.class)
public class Aoc2017Day04Test {

    @Test
    public void test1() {
        final Aoc2017Day04 day4 = new Aoc2017Day04();
        assertEquals("1", test("aa bb cc dd ee", day4::part1));
        assertEquals("0", test("aa bb cc dd aa", day4::part1));
        assertEquals("1", test("aa bb cc dd aaa", day4::part1));
        assertEquals("2", test("aa bb cc dd ee\naa bb cc dd aa\naa bb cc dd aaa", day4::part1));
        assertEquals("451", test(day4.getInput1(), day4::part1));
    }

    @Test
    public void test2() {
        final Aoc2017Day04 day4 = new Aoc2017Day04();
        assertEquals("1", test("abcde fghij", day4::part2));
        assertEquals("0", test("abcde xyz ecdab", day4::part2));
        assertEquals("1", test("a ab abc abd abf abj", day4::part2));
        assertEquals("1", test("iiii oiii ooii oooi oooo", day4::part2));
        assertEquals("0", test("oiii ioii iioi iiio", day4::part2));
        assertEquals("223", test(day4.getInput2(), day4::part2));
    }
}