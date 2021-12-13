package de.chrlembeck.aoc2021;

import de.chrlembeck.aoc2021.day12.Aoc2021Day12;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2021Day12Test {

    @Test
    public void test1() {
        final Aoc2021Day12 day = new Aoc2021Day12();
        assertEquals("10", test("""
                                start-A
                                start-b
                                A-c
                                A-b
                                b-d
                                A-end
                                b-end""", day::part1));
        assertEquals("19", test("""
                                dc-end
                                HN-start
                                start-kj
                                dc-start
                                dc-HN
                                LN-dc
                                HN-end
                                kj-sa
                                kj-HN
                                kj-dc""", day::part1));
        assertEquals("226", test("""
                                fs-end
                                he-DX
                                fs-he
                                start-DX
                                pj-DX
                                end-zg
                                zg-sl
                                zg-pj
                                pj-he
                                RW-he
                                fs-DX
                                pj-RW
                                zg-RW
                                start-pj
                                he-WI
                                zg-he
                                pj-fs
                                start-RW""", day::part1));
        assertEquals("5254", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2021Day12 day = new Aoc2021Day12();
        assertEquals("36", test("""
                                start-A
                                start-b
                                A-c
                                A-b
                                b-d
                                A-end
                                b-end""", day::part2));
        assertEquals("103", test("""
                                dc-end
                                HN-start
                                start-kj
                                dc-start
                                dc-HN
                                LN-dc
                                HN-end
                                kj-sa
                                kj-HN
                                kj-dc""", day::part2));
        assertEquals("3509", test("""
                                fs-end
                                he-DX
                                fs-he
                                start-DX
                                pj-DX
                                end-zg
                                zg-sl
                                zg-pj
                                pj-he
                                RW-he
                                fs-DX
                                pj-RW
                                zg-RW
                                start-pj
                                he-WI
                                zg-he
                                pj-fs
                                start-RW""", day::part2));
        assertEquals("149385", test(day.getInput2(), day::part2));
    }
}