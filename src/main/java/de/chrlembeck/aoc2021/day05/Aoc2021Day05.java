package de.chrlembeck.aoc2021.day05;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2021Day05 extends AbstractAocBase {

    public static final Pattern REGEX = Pattern.compile("(\\d+),(\\d+) -> (\\d+),(\\d+)");

    public static void main(final String[] args) {
        new Aoc2021Day05().run();
    }

    @Override
    public Long part1(final Scanner input) {
        return calc(input, (x1, y1, x2, y2) -> x1 == x2 || y1 == y2);
    }

    @Override
    public Long part2(final Scanner input) {
        return calc(input, (a, b, c, d) -> true);
    }

    private long calc(final Scanner input, final LineCondition lineCondition) {
        final Map<Point, Integer> hits = new HashMap<>();
        while (input.hasNext()) {
            final Matcher matcher = matchRegex(REGEX, input.nextLine());
            final int posX1 = Integer.parseInt(matcher.group(1));
            final int posY1 = Integer.parseInt(matcher.group(2));
            final int posX2 = Integer.parseInt(matcher.group(3));
            final int posY2 = Integer.parseInt(matcher.group(4));
            final int dist = Math.max(Math.abs(posX2 - posX1), Math.abs(posY2 - posY1));
            if (lineCondition.check(posX1, posY1, posX2, posY2)) {
                for (int idx = 0; idx <= dist; idx++) {
                    hits.compute(new Point(posX1 + idx * Integer.signum(posX2 - posX1), posY1 + idx * Integer.signum(posY2 - posY1)), (p, z) -> z == null ? 1 : z + 1);
                }
            }
        }
        return hits.values().stream().filter(z -> z > 1).count();
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2021/aoc2021day05.txt";
    }

    interface LineCondition {

        boolean check(int posX1, int posY1, int posX2, int posY2);
    }
}