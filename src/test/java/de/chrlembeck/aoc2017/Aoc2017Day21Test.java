package de.chrlembeck.aoc2017;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import de.chrlembeck.aoc2017.day21.Aoc2017Day21;

@RunWith(JUnitPlatform.class)
public class Aoc2017Day21Test {

    @Test
    public void test1() {
        final Aoc2017Day21 day21 = new Aoc2017Day21();
        assertEquals(null, test("", day21::part1));
        assertEquals(null, test(day21.getInput1(), day21::part1));
    }

    @Test
    public void test2() {
        final Aoc2017Day21 day21 = new Aoc2017Day21();
        assertEquals(null, test("", day21::part2));
        assertEquals(null, test(day21.getInput2(), day21::part2));
    }
}