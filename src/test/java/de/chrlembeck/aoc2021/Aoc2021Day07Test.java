package de.chrlembeck.aoc2021;

import de.chrlembeck.aoc2021.day07.Aoc2021Day07;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2021Day07Test {

    @Test
    public void test1() {
        final Aoc2021Day07 day = new Aoc2021Day07();
        assertEquals("37", test("16,1,2,0,4,2,7,1,2,14", day::part1));
        assertEquals("331067", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2021Day07 day = new Aoc2021Day07();
        assertEquals("168", test("16,1,2,0,4,2,7,1,2,14", day::part2));
        assertEquals("92881128", test(day.getInput2(), day::part2));
    }
}