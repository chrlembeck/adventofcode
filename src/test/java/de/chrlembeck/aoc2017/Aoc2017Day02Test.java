package de.chrlembeck.aoc2017;

import de.chrlembeck.aoc2017.day02.Aoc2017Day02;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2017Day02Test {

    @Test
    public void test1() {
        final Aoc2017Day02 day2 = new Aoc2017Day02();
        assertEquals("18", test("5 1 9 5\n7 5 3\n2 4 6 8", day2::part1));
        assertEquals("44670", test(day2.getInput1(), day2::part1));
    }

    @Test
    public void test2() {
        final Aoc2017Day02 day2 = new Aoc2017Day02();
        assertEquals("9", test("5 9 2 8\n9 4 7 3\n3 8 6 5", day2::part2));
        assertEquals("285", test(day2.getInput2(), day2::part2));
    }
}