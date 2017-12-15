package de.chrlembeck.aoc2017;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import de.chrlembeck.aoc2017.day14.Aoc2017Day14;

@RunWith(JUnitPlatform.class)
public class Aoc2017Day14Test {

    @Test
    public void test1() {
        final Aoc2017Day14 day14 = new Aoc2017Day14();
        assertEquals("8108", test("flqrgnkx", day14::part1));
        assertEquals("8222", test("amgozmfv", day14::part1));
        assertEquals("8214", test(day14.getInput1(), day14::part1));
    }

    @Test
    public void test2() {
        final Aoc2017Day14 day14 = new Aoc2017Day14();
        assertEquals("1242", test("flqrgnkx", day14::part2));
        assertEquals("1086", test("amgozmfv", day14::part2));
        assertEquals("1093", test(day14.getInput2(), day14::part2));
    }
}