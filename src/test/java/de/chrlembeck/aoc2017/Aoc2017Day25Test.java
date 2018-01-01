package de.chrlembeck.aoc2017;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import de.chrlembeck.aoc2017.day25.Aoc2017Day25;
import de.chrlembeck.aoccommon.FileUtil;

@RunWith(JUnitPlatform.class)
public class Aoc2017Day25Test {

    @Test
    public void test1() {
        final Aoc2017Day25 day = new Aoc2017Day25();
        assertEquals("3", test(FileUtil.readString("/input/aoc2017/aoc2017day25test.txt"), day::part1));
        assertEquals("3554", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2017Day25 day = new Aoc2017Day25();
        assertEquals(null, test("", day::part2));
        assertEquals(null, test(day.getInput2(), day::part2));
    }
}