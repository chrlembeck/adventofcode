package de.chrlembeck.aoc2019;

import de.chrlembeck.aoc2019.day05.Aoc2019Day05;
import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.util.Scanner;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2019Day05Test {

    @Test
    public void test1() {
        final Aoc2019Day05 day = new Aoc2019Day05();
        assertEquals("15386262", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2019Day05 day = new Aoc2019Day05();
        assertEquals("0", day.run(new Scanner("3,9,8,9,10,9,4,9,99,-1,8"), BigInteger.valueOf(7)).toString());
        assertEquals("1", day.run(new Scanner("3,9,8,9,10,9,4,9,99,-1,8"), BigInteger.valueOf(8)).toString());
        assertEquals("0", day.run(new Scanner("3,9,8,9,10,9,4,9,99,-1,8"), BigInteger.valueOf(9)).toString());


        String input = "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,"
                + "1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,"
                + "999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99";
        assertEquals("999", day.run(new Scanner(input), BigInteger.ZERO).toString());
        assertEquals("999", day.run(new Scanner(input), BigInteger.ONE).toString());
        assertEquals("999", day.run(new Scanner(input), BigInteger.TWO).toString());
        assertEquals("999", day.run(new Scanner(input), BigInteger.valueOf(3)).toString());
        assertEquals("999", day.run(new Scanner(input), BigInteger.valueOf(4)).toString());
        assertEquals("999", day.run(new Scanner(input), BigInteger.valueOf(5)).toString());
        assertEquals("999", day.run(new Scanner(input), BigInteger.valueOf(6)).toString());
        assertEquals("999", day.run(new Scanner(input), BigInteger.valueOf(7)).toString());
        assertEquals("1000", day.run(new Scanner(input), BigInteger.valueOf(8)).toString());
        assertEquals("1001", day.run(new Scanner(input), BigInteger.valueOf(9)).toString());
        assertEquals("1001", day.run(new Scanner(input), BigInteger.valueOf(10)).toString());
        assertEquals("1001", day.run(new Scanner(input), BigInteger.valueOf(11)).toString());
        assertEquals("10376124", test(day.getInput2(), day::part2));
    }
}