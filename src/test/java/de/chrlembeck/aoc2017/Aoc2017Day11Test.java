package de.chrlembeck.aoc2017;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import de.chrlembeck.aoc2017.day11.Aoc2017Day11;

@RunWith(JUnitPlatform.class)
public class Aoc2017Day11Test {

    @Test
    public void test1() {
        final Aoc2017Day11 day11 = new Aoc2017Day11();
        assertEquals("3", test("ne,ne,ne", day11::part1));
        assertEquals("0", test("ne,ne,sw,sw", day11::part1));
        assertEquals("2", test("ne,ne,s,s", day11::part1));
        assertEquals("3", test("se,sw,se,sw,sw", day11::part1));
        assertEquals("698", test(day11.getInput1(), day11::part1));
    }

    @Test
    public void test2() {
        final Aoc2017Day11 day11 = new Aoc2017Day11();
        assertEquals("3", test("ne,ne,ne", day11::part2));
        assertEquals("2", test("ne,ne,sw,sw", day11::part2));
        assertEquals("2", test("ne,ne,s,s", day11::part2));
        assertEquals("3", test("se,sw,se,sw,sw", day11::part2));
        assertEquals("1435", test(day11.getInput2(), day11::part2));
    }
}