package de.chrlembeck.aoc2021.day17;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;
import static java.lang.Math.max;

public class Aoc2021Day17 extends AbstractAocBase {

    private final Pattern pattern = Pattern.compile("target area: x=(\\d+)..(\\d+), y=(-?\\d+)..(-?\\d+)");

    public static void main(final String[] args) {
        new Aoc2021Day17().run();
    }

    @Override
    public Object part1(final Scanner input) {
        return calc(input).part1;
    }

    @Override
    public Object part2(final Scanner input) {
        return calc(input).part2;
    }

    private Result calc(Scanner input) {
        Matcher matcher = matchRegex(pattern, input.nextLine());
        Target target = new Target(parseInt(matcher.group(1)), parseInt(matcher.group(2)), parseInt(matcher.group(3)), parseInt(matcher.group(4)));

        int maxDx = target.xMax();
        int minDx = 0;
        int count = 0;
        while (minDx * (minDx + 1) / 2 < target.xMin) {
            minDx++;
        }
        Integer bestY = null;
        for (int dx = minDx; dx <= maxDx; dx++) {
            int x = 0;
            boolean hit = false;
            int tdx = dx;
            while (tdx > 0) {
                x = x + tdx--;
                hit |= x >= target.xMin && x <= target.xMax;
            }
            if (!hit) {
                continue;
            }
            loop:
            for (int dy = target.yMin; dy <= -target.yMin; dy++) {
                int y = 0;
                x = 0;
                int tdy = dy;
                tdx = dx;
                int yMax = y;
                while (y >= target.yMin) {
                    y = y + tdy--;
                    x = x + tdx;
                    tdx = max(tdx - 1, 0);
                    yMax = max(yMax, y);
                    if (y <= target.yMax && y >= target.yMin && x >= target.xMin && x <= target.xMax) {
                        bestY = bestY == null ? dy : max(bestY, yMax);
                        count++;
                        continue loop;
                    }
                }
            }
        }
        return new Result(bestY, count);
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2021/aoc2021day17.txt";
    }

    record Target(int xMin, int xMax, int yMin, int yMax) {}

    record Result(int part1, int part2){}
}