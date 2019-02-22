package de.chrlembeck.aoc2017;

import de.chrlembeck.aoc2017.day09.Aoc2017Day09;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2017Day09Test {

    @Test
    public void test1() {
        final Aoc2017Day09 day9 = new Aoc2017Day09();
        assertEquals("1", test("{}", day9::part1));
        assertEquals("6", test("{{{}}}", day9::part1));
        assertEquals("5", test("{{},{}}", day9::part1));
        assertEquals("16", test("{{{},{},{{}}}}", day9::part1));
        assertEquals("1", test("{<a>,<a>,<a>,<a>}", day9::part1));
        assertEquals("9", test("{{<ab>},{<ab>},{<ab>},{<ab>}}", day9::part1));
        assertEquals("9", test("{{<!!>},{<!!>},{<!!>},{<!!>}}", day9::part1));
        assertEquals("3", test("{{<a!>},{<a!>},{<a!>},{<ab>}}", day9::part1));
        assertEquals("16689", test(day9.getInput1(), day9::part1));
    }

    @Test
    public void test2() {
        final Aoc2017Day09 day9 = new Aoc2017Day09();
        assertEquals("0", test("<>", day9::part2));
        assertEquals("17", test("<random characters", day9::part2));
        assertEquals("3", test("<<<<>", day9::part2));
        assertEquals("2", test("<{!>}>", day9::part2));
        assertEquals("0", test("<!!>", day9::part2));
        assertEquals("0", test("<!!!>>", day9::part2));
        assertEquals("10", test("<{o\"i!a,<{i<a>", day9::part2));
        assertEquals("7982", test(day9.getInput2(), day9::part2));
    }
}