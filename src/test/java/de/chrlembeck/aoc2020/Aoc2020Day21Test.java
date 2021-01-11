package de.chrlembeck.aoc2020;

import de.chrlembeck.aoc2020.day21.Aoc2020Day21;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2020Day21Test {

    @Test
    public void test1() {
        final Aoc2020Day21 day = new Aoc2020Day21();
        assertEquals("5", test("""
                               mxmxvkd kfcds sqjhc nhms (contains dairy, fish)
                               trh fvjkl sbzzf mxmxvkd (contains dairy)
                               sqjhc fvjkl (contains soy)
                               sqjhc mxmxvkd sbzzf (contains fish)""", day::part1));
        assertEquals("2020", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2020Day21 day = new Aoc2020Day21();
        assertEquals("mxmxvkd,sqjhc,fvjkl", test("""
                               mxmxvkd kfcds sqjhc nhms (contains dairy, fish)
                               trh fvjkl sbzzf mxmxvkd (contains dairy)
                               sqjhc fvjkl (contains soy)
                               sqjhc mxmxvkd sbzzf (contains fish)""", day::part2));
        assertEquals("bcdgf,xhrdsl,vndrb,dhbxtb,lbnmsr,scxxn,bvcrrfbr,xcgtv", test(day.getInput1(), day::part2));
    }
}