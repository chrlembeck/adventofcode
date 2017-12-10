package de.chrlembeck.aoc2017.day07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class Day7Test {

    static String test(final String input, final Function<Scanner, String> function) {
        try (Scanner scanner = new Scanner(input)) {
            return function.apply(scanner);
        }
    }

    @Test
    public void test1() {
        final Day7 day7 = new Day7();
        assertEquals("tknk", test("pbga (66)\n" +
                "xhth (57)\n" +
                "ebii (61)\n" +
                "havc (66)\n" +
                "ktlj (57)\n" +
                "fwft (72) -> ktlj, cntj, xhth\n" +
                "qoyq (66)\n" +
                "padx (45) -> pbga, havc, qoyq\n" +
                "tknk (41) -> ugml, padx, fwft\n" +
                "jptl (61)\n" +
                "ugml (68) -> gyxo, ebii, jptl\n" +
                "gyxo (61)\n" +
                "cntj (57)", day7::part1));
        assertEquals("bpvhwhh", test(day7.getInput1(), day7::part1));
    }

    @Test
    public void test2() {
        final Day7 day7 = new Day7();
        assertEquals("60", test("pbga (66)\n" +
                "xhth (57)\n" +
                "ebii (61)\n" +
                "havc (66)\n" +
                "ktlj (57)\n" +
                "fwft (72) -> ktlj, cntj, xhth\n" +
                "qoyq (66)\n" +
                "padx (45) -> pbga, havc, qoyq\n" +
                "tknk (41) -> ugml, padx, fwft\n" +
                "jptl (61)\n" +
                "ugml (68) -> gyxo, ebii, jptl\n" +
                "gyxo (61)\n" +
                "cntj (57)", day7::part2));
        assertEquals("256", test(day7.getInput2(), day7::part2));
    }

    public static void main(final String[] args) {
        final Pattern p = Pattern.compile("([a-z]+)\\s*\\((\\d+)\\)(\\s*\\-\\>(([a-z,\\s]*)))?");
        // final Matcher m = p.matcher("bsga (15) -> abcd, defg, hijkl");
        final Matcher m = p.matcher("bsga (15)");
        System.out.println(m.matches());
        for (int i = 0; i < m.groupCount(); i++) {
            System.out.println(i + ": " + m.group(i));
        }
    }
}