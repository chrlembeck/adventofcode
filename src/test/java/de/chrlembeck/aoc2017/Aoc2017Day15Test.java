package de.chrlembeck.aoc2017;

import de.chrlembeck.aoc2017.day15.Aoc2017Day15;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2017Day15Test {

    @Test
    public void test1() {
        final Aoc2017Day15 day = new Aoc2017Day15();
        assertEquals("588", test("Generator A starts with 65\r\n" +
                "Generator B starts with 8921", day::part1));
        assertEquals("631", test("Generator A starts with 873\n" +
                "Generator B starts with 583", day::part1));

        assertEquals("573", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2017Day15 day = new Aoc2017Day15();
        assertEquals("309", test("Generator A starts with 65\r\n" +
                "Generator B starts with 8921", day::part2));
        assertEquals("279", test("Generator A starts with 873\n" +
                "Generator B starts with 583", day::part2));
        assertEquals("294", test(day.getInput2(), day::part2));
    }
}