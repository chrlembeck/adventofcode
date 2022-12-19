package de.chrlembeck.aoc2021;

import de.chrlembeck.aoc2021.day25.Aoc2021Day25;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2021Day25Test {

    @Test
    public void test1() {
        final Aoc2021Day25 day = new Aoc2021Day25();
        assertEquals("58", test("""
                v...>>.vv>
                .vv>>.vv..
                >>.>v>...v
                >>v>>.>.v.
                v>v.vv.v..
                >.>>..v...
                .vv..>.>v.
                v.v..>>v.v
                ....v..v.>""", day::part1));
        assertEquals("360", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2021Day25 day = new Aoc2021Day25();
        assertEquals("", test("", day::part2));
        assertEquals("", test(day.getInput2(), day::part2));
    }
}