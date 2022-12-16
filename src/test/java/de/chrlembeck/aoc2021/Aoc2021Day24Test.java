package de.chrlembeck.aoc2021;

import de.chrlembeck.aoc2021.day24.Aoc2021Day24;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2021Day24Test {

    private static final String INPUT = """
                inp w
                mul x 0
                add x z
                mod x 26
                div z 1
                add x 15
                eql x w
                eql x 0
                mul y 0
                add y 25
                mul y x
                add y 1
                mul z y
                mul y 0
                add y w
                add y 15
                mul y x
                add z y
                inp w
                mul x 0
                add x z
                mod x 26
                div z 1
                add x 15
                eql x w
                eql x 0
                mul y 0
                add y 25
                mul y x
                add y 1
                mul z y
                mul y 0
                add y w
                add y 10
                mul y x
                add z y
                inp w
                mul x 0
                add x z
                mod x 26
                div z 1
                add x 12
                eql x w
                eql x 0
                mul y 0
                add y 25
                mul y x
                add y 1
                mul z y
                mul y 0
                add y w
                add y 2
                mul y x
                add z y
                inp w
                mul x 0
                add x z
                mod x 26
                div z 1
                add x 13
                eql x w
                eql x 0
                mul y 0
                add y 25
                mul y x
                add y 1
                mul z y
                mul y 0
                add y w
                add y 16
                mul y x
                add z y
                inp w
                mul x 0
                add x z
                mod x 26
                div z 26
                add x -12
                eql x w
                eql x 0
                mul y 0
                add y 25
                mul y x
                add y 1
                mul z y
                mul y 0
                add y w
                add y 12
                mul y x
                add z y
                inp w
                mul x 0
                add x z
                mod x 26
                div z 1
                add x 10
                eql x w
                eql x 0
                mul y 0
                add y 25
                mul y x
                add y 1
                mul z y
                mul y 0
                add y w
                add y 11
                mul y x
                add z y
                inp w
                mul x 0
                add x z
                mod x 26
                div z 26
                add x -9
                eql x w
                eql x 0
                mul y 0
                add y 25
                mul y x
                add y 1
                mul z y
                mul y 0
                add y w
                add y 5
                mul y x
                add z y
                inp w
                mul x 0
                add x z
                mod x 26
                div z 1
                add x 14
                eql x w
                eql x 0
                mul y 0
                add y 25
                mul y x
                add y 1
                mul z y
                mul y 0
                add y w
                add y 16
                mul y x
                add z y
                inp w
                mul x 0
                add x z
                mod x 26
                div z 1
                add x 13
                eql x w
                eql x 0
                mul y 0
                add y 25
                mul y x
                add y 1
                mul z y
                mul y 0
                add y w
                add y 6
                mul y x
                add z y
                inp w
                mul x 0
                add x z
                mod x 26
                div z 26
                add x -14
                eql x w
                eql x 0
                mul y 0
                add y 25
                mul y x
                add y 1
                mul z y
                mul y 0
                add y w
                add y 15
                mul y x
                add z y
                inp w
                mul x 0
                add x z
                mod x 26
                div z 26
                add x -11
                eql x w
                eql x 0
                mul y 0
                add y 25
                mul y x
                add y 1
                mul z y
                mul y 0
                add y w
                add y 3
                mul y x
                add z y
                inp w
                mul x 0
                add x z
                mod x 26
                div z 26
                add x -2
                eql x w
                eql x 0
                mul y 0
                add y 25
                mul y x
                add y 1
                mul z y
                mul y 0
                add y w
                add y 12
                mul y x
                add z y
                inp w
                mul x 0
                add x z
                mod x 26
                div z 26
                add x -16
                eql x w
                eql x 0
                mul y 0
                add y 25
                mul y x
                add y 1
                mul z y
                mul y 0
                add y w
                add y 10
                mul y x
                add z y
                inp w
                mul x 0
                add x z
                mod x 26
                div z 26
                add x -14
                eql x w
                eql x 0
                mul y 0
                add y 25
                mul y x
                add y 1
                mul z y
                mul y 0
                add y w
                add y 13
                mul y x
                add z y""";

    @Test
    public void test1() {
        final Aoc2021Day24 day = new Aoc2021Day24();
        assertEquals("89959794919939", test(INPUT, day::part1));
        assertEquals("29991993698469", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2021Day24 day = new Aoc2021Day24();
        assertEquals("17115131916112", test(INPUT, day::part2));
        assertEquals("14691271141118", test(day.getInput2(), day::part2));
    }
}