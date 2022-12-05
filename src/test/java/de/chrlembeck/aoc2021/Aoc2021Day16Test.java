package de.chrlembeck.aoc2021;

import de.chrlembeck.aoc2021.day16.Aoc2021Day16;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2021Day16Test {

    @Test
    public void test1() {
        final Aoc2021Day16 day = new Aoc2021Day16();
        assertEquals("6", test("D2FE28", day::part1));
        assertEquals("16", test("8A004A801A8002F478", day::part1));
        assertEquals("12", test("620080001611562C8802118E34", day::part1));
        assertEquals("23", test("C0015000016115A2E0802F182340", day::part1));
        assertEquals("31", test("A0016C880162017C3686B18A3D4780", day::part1));
        assertEquals("927", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2021Day16 day = new Aoc2021Day16();
        assertEquals("3", test("C200B40A82", day::part2));
        assertEquals("54", test("04005AC33890", day::part2));
        assertEquals("7", test("880086C3E88112", day::part2));
        assertEquals("9", test("CE00C43D881120", day::part2));
        assertEquals("1", test("D8005AC2A8F0", day::part2));
        assertEquals("0", test("F600BC2D8F", day::part2));
        assertEquals("0", test("9C005AC2F8F0", day::part2));
        assertEquals("1", test("9C0141080250320F1802104A08", day::part2));
        assertEquals("1725277876501", test(day.getInput2(), day::part2));
    }
}