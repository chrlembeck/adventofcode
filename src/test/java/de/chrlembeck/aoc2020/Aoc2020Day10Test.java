package de.chrlembeck.aoc2020;

import de.chrlembeck.aoc2020.day10.Aoc2020Day10;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2020Day10Test {

    @Test
    public void test1() {
        final Aoc2020Day10 day = new Aoc2020Day10();
        assertEquals("35", test("""
                                16
                                10
                                15
                                5
                                1
                                11
                                7
                                19
                                6
                                12
                                4""", day::part1));
        assertEquals("220", test("""
                                 28
                                 33
                                 18
                                 42
                                 31
                                 14
                                 46
                                 20
                                 48
                                 47
                                 24
                                 23
                                 49
                                 45
                                 19
                                 38
                                 39
                                 11
                                 1
                                 32
                                 25
                                 35
                                 8
                                 17
                                 7
                                 9
                                 4
                                 2
                                 34
                                 10
                                 3""", day::part1));
        assertEquals("2176", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2020Day10 day = new Aoc2020Day10();
        assertEquals("8", test("""
                                16
                                10
                                15
                                5
                                1
                                11
                                7
                                19
                                6
                                12
                                4""", day::part2));
        assertEquals("19208", test("""
                                28
                                33
                                18
                                42
                                31
                                14
                                46
                                20
                                48
                                47
                                24
                                23
                                49
                                45
                                19
                                38
                                39
                                11
                                1
                                32
                                25
                                35
                                8
                                17
                                7
                                9
                                4
                                2
                                34
                                10
                                3""", day::part2));
        assertEquals("18512297918464", test(day.getInput1(), day::part2));
    }
}