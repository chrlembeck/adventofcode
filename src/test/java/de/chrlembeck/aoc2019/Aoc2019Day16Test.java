package de.chrlembeck.aoc2019;

import de.chrlembeck.aoc2019.day16.Aoc2019Day16;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2019Day16Test {

    @Test
    public void test1() {
        final Aoc2019Day16 day = new Aoc2019Day16();
        assertEquals("24176176", test("80871224585914546619083218645595", day::part1));
        assertEquals("73745418", test("19617804207202209144916044189917", day::part1));
        assertEquals("52432133", test("69317163492948606335995924319873", day::part1));
        assertEquals("52611030", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2019Day16 day = new Aoc2019Day16();
        assertEquals("84462026", test("03036732577212944063491565474664", day::part2));
        assertEquals("78725270", test("02935109699940807407585447034323", day::part2));
        assertEquals("53553731", test("03081770884921959731165446850517", day::part2));
        assertEquals("52541026", test(day.getInput2(), day::part2));
    }
}