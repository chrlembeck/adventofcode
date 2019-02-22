package de.chrlembeck.aoc2017;

import de.chrlembeck.aoc2017.day18.Aoc2017Day18;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2017Day18Test {

    @Test
    public void test1() {
        final Aoc2017Day18 day18 = new Aoc2017Day18();
        assertEquals("4", test("set a 1\n" +
                "add a 2\n" +
                "mul a a\n" +
                "mod a 5\n" +
                "snd a\n" +
                "set a 0\n" +
                "rcv a\n" +
                "jgz a -1\n" +
                "set a 1\n" +
                "jgz a -2", day18::part1));
        assertEquals("2951", test(day18.getInput1(), day18::part1));
    }

    @Test
    public void test2() {
        final Aoc2017Day18 day13 = new Aoc2017Day18();
        assertEquals("7366", test(day13.getInput2(), day13::part2));
    }
}