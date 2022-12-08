package de.chrlembeck.aoc2022;

import de.chrlembeck.aoc2022.day08.Aoc2022Day08;
import de.chrlembeck.aoccommon.AbstractAocBase;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2022Dayo08Test {

    private final AbstractAocBase testee = new Aoc2022Day08();

    @Test
    public void test1() {
        assertEquals("21", test("""
                30373
                25512
                65332
                33549
                35390""", testee::part1));
        assertEquals("1851", test(testee.getInput1(), testee::part1));
    }

    @Test
    public void test2() {
        assertEquals("8", test("""
                30373
                25512
                65332
                33549
                35390""", testee::part2));
        assertEquals("574080", test(testee.getInput1(), testee::part2));
    }
}
