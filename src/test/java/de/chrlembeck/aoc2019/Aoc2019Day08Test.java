package de.chrlembeck.aoc2019;

import de.chrlembeck.aoc2019.day08.Aoc2019Day08;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2019Day08Test {

    @Test
    public void test1() {
        final Aoc2019Day08 day = new Aoc2019Day08();
        assertEquals("1088", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2019Day08 day = new Aoc2019Day08();
        assertEquals("""
                     X.....XX..X...XX..X.XXX..
                     X....X..X.X...XX..X.X..X.
                     X....X.....X.X.XXXX.XXX..
                     X....X.XX...X..X..X.X..X.
                     X....X..X...X..X..X.X..X.
                     XXXX..XXX...X..X..X.XXX..
                     """, test(day.getInput2(), day::part2));
    }
}