package de.chrlembeck.aoc2017;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import de.chrlembeck.aoc2017.day22.Aoc2017Day22;

@RunWith(JUnitPlatform.class)
public class Aoc2017Day22Test {

    @Test
    public void test1() {
        final Aoc2017Day22 day22 = new Aoc2017Day22();
        assertEquals("5587", test("..#\n#..\n...", day22::part1));
        assertEquals("5259", test(day22.getInput1(), day22::part1));
    }

    @Test
    public void test2() {
        final Aoc2017Day22 day22 = new Aoc2017Day22();
        assertEquals("2511944", test("..#\n#..\n...", day22::part2));
        assertEquals("2511722", test(day22.getInput2(), day22::part2));
    }
}