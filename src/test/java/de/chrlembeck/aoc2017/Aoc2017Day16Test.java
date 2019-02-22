package de.chrlembeck.aoc2017;

import de.chrlembeck.aoc2017.day16.Aoc2017Day16;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2017Day16Test {

    @Test
    public void test1() {
        final Aoc2017Day16 day = new Aoc2017Day16();
        assertEquals("pabcdefghijklmno", test("s1", day::part1));
        assertEquals("nopabcdefghijklm", test("s3", day::part1));
        assertEquals("abcedfghijklmnop", test("x3/4", day::part1));
        assertEquals("aecdbfghijklmnop", test("pe/b", day::part1));
        assertEquals("pabdecfghijkmlno", test("s1,x3/4,x4/5,pl/m", day::part1));
        assertEquals("pabdcefghijkmlno", test("s1,x3/4,x4/5,pl/m,pe/c", day::part1));
        assertEquals("pabdecfghijkmlno", test("s1,x3/4,x4/5,pl/m,pe/c,x4/5", day::part1));

        assertEquals("abcdefghijplmnok", test("x15/10", day::part1));
        assertEquals("abhdefgcijplmnok", test("x15/10,ph/c", day::part1));
        assertEquals("abhoefgcijplmndk", test("x15/10,ph/c,x3/14", day::part1));
        assertEquals("kabhoefgcijplmnd", test("x15/10,ph/c,x3/14,s1", day::part1));
        assertEquals("kafhoebgcijplmnd", test("x15/10,ph/c,x3/14,s1,pf/b", day::part1));
        assertEquals("lafhoebgcijpkmnd", test("x15/10,ph/c,x3/14,s1,pf/b,x12/0", day::part1));
        assertEquals("lafmoebgcijpkhnd", test("x15/10,ph/c,x3/14,s1,pf/b,x12/0,ph/m", day::part1));


        assertEquals("bacdefghijklmnop", test("pa/b", day::part1));
        assertEquals("cabdefghijklmnop", test("pa/b,pb/c", day::part1));

        assertEquals("ceijbfoamgkdnlph", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2017Day16 day = new Aoc2017Day16();
        //        assertEquals(null, test("", day::part2));
        assertEquals("pnhajoekigcbflmd", test(day.getInput2(), day::part2));
    }
}