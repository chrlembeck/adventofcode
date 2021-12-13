package de.chrlembeck.aoc2016;

import de.chrlembeck.aoc2016.day17.Aoc2016Day17;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2016Day17Test {

    @Test
    public void test1() {
        final Aoc2016Day17 day = new Aoc2016Day17();
        assertEquals("DDRRRD", test("ihgpwlah", day::part1));
        assertEquals("DDUDRLRRUDRD", test("kglvqrro", day::part1));
        assertEquals("DRURDRUDDLLDLUURRDULRLDUUDDDRR", test("ulqzkmiv", day::part1));
        assertEquals("DRLRDDURDR", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2016Day17 day = new Aoc2016Day17();
        assertEquals("370", test("ihgpwlah", day::part2));
        assertEquals("492", test("kglvqrro", day::part2));
        assertEquals("830", test("ulqzkmiv", day::part2));
        assertEquals("500", test(day.getInput2(), day::part2));
    }
}