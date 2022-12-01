package de.chrlembeck.aoc2015;

import de.chrlembeck.aoc2015.day25.Aoc2015Day25;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2015Day25Test {

    @Test
    public void test1() {
        final Aoc2015Day25 day = new Aoc2015Day25();
        assertEquals("20151125", test("To continue, please consult the code grid in the manual.  Enter the code at row 1, column 1.", day::part1));
        assertEquals("31916031", test("To continue, please consult the code grid in the manual.  Enter the code at row 2, column 1.", day::part1));
        assertEquals("18749137", test("To continue, please consult the code grid in the manual.  Enter the code at row 1, column 2.", day::part1));
        assertEquals("1534922", test("To continue, please consult the code grid in the manual.  Enter the code at row 6, column 5.", day::part1));
        assertEquals("8997277", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
    }
}