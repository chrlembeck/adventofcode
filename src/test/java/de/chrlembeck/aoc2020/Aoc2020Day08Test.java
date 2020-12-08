package de.chrlembeck.aoc2020;

import de.chrlembeck.aoc2020.day08.Aoc2020Day08;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2020Day08Test {

    @Test
    public void test1() {
        final Aoc2020Day08 day = new Aoc2020Day08();
        assertEquals("5", test("""
                                nop +0
                                acc +1
                                jmp +4
                                acc +3
                                jmp -3
                                acc -99
                                acc +1
                                jmp -4
                                acc +6""", day::part1));
        assertEquals("1134", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2020Day08 day = new Aoc2020Day08();
        assertEquals("8", test("""
                                nop +0
                                acc +1
                                jmp +4
                                acc +3
                                jmp -3
                                acc -99
                                acc +1
                                jmp -4
                                acc +6""", day::part2));
        assertEquals("1205", test(day.getInput1(), day::part2));
    }
}