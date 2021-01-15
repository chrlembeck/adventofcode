package de.chrlembeck.aoc2016;

import de.chrlembeck.aoc2016.day06.Aoc2016Day06;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2016Day06Test {

    @Test
    public void test1() {
        final Aoc2016Day06 day = new Aoc2016Day06();
        assertEquals("easter", test("""
                                    eedadn
                                    drvtee
                                    eandsr
                                    raavrd
                                    atevrs
                                    tsrnev
                                    sdttsa
                                    rasrtv
                                    nssdts
                                    ntnada
                                    svetve
                                    tesnvt
                                    vntsnd
                                    vrdear
                                    dvrsen
                                    enarar""", day::part1));
        assertEquals("qzedlxso", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2016Day06 day = new Aoc2016Day06();
        assertEquals("advent", test("""
                                    eedadn
                                    drvtee
                                    eandsr
                                    raavrd
                                    atevrs
                                    tsrnev
                                    sdttsa
                                    rasrtv
                                    nssdts
                                    ntnada
                                    svetve
                                    tesnvt
                                    vntsnd
                                    vrdear
                                    dvrsen
                                    enarar""", day::part2));
        assertEquals("ucmifjae", test(day.getInput2(), day::part2));
    }
}