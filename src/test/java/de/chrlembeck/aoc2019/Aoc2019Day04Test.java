package de.chrlembeck.aoc2019;

import de.chrlembeck.aoc2019.day04.Aoc2019Day04;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2019Day04Test {

    @Test
    public void test1() {
        final Aoc2019Day04 day4 = new Aoc2019Day04();
        assertEquals(true, day4.isValid1("111111"));
        assertEquals(false, day4.isValid1("223450"));
        assertEquals(false, day4.isValid1("123789"));
        assertEquals("931", test(day4.getInput1(), day4::part1));
    }

    @Test
    public void test2() {
        final Aoc2019Day04 day4 = new Aoc2019Day04();
        assertEquals(true, day4.isValid2("112233"));
        assertEquals(false, day4.isValid2("123444"));
        assertEquals(true, day4.isValid2("111122"));
        assertEquals(true, day4.isValid2("112222"));
        assertEquals(false, day4.isValid2("111111"));
        assertEquals("609", test(day4.getInput1(), day4::part2));
    }
}