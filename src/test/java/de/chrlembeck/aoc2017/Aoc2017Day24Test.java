package de.chrlembeck.aoc2017;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import de.chrlembeck.aoc2017.day24.Aoc2017Day24;

@RunWith(JUnitPlatform.class)
public class Aoc2017Day24Test {

    @Test
    public void test1() {
        final Aoc2017Day24 day24 = new Aoc2017Day24();
        assertEquals("31", test("0/2\n" +
                "2/2\n" +
                "2/3\n" +
                "3/4\n" +
                "3/5\n" +
                "0/1\n" +
                "10/1\n" +
                "9/10", day24::part1));
        assertEquals("1859", test(day24.getInput1(), day24::part1));
    }

    @Test
    public void test2() {
        final Aoc2017Day24 day24 = new Aoc2017Day24();
        assertEquals("19", test("0/2\n" +
                "2/2\n" +
                "2/3\n" +
                "3/4\n" +
                "3/5\n" +
                "0/1\n" +
                "10/1\n" +
                "9/10", day24::part2));
        assertEquals("1799", test(day24.getInput2(), day24::part2));
    }
}