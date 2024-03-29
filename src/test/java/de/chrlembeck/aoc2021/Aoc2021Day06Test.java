package de.chrlembeck.aoc2021;

import de.chrlembeck.aoc2021.day06.Aoc2021Day06;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2021Day06Test {

    @Test
    public void test1() {
        final Aoc2021Day06 day = new Aoc2021Day06();
        assertEquals("5934", test("3,4,3,1,2", day::part1));
        assertEquals("361169", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2021Day06 day = new Aoc2021Day06();
        assertEquals("26984457539", test("3,4,3,1,2", day::part2));
        assertEquals("1634946868992", test(day.getInput2(), day::part2));
    }
}