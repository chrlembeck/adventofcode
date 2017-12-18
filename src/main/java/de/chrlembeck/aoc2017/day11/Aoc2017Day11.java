package de.chrlembeck.aoc2017.day11;

import java.util.Scanner;

import de.chrlembeck.aoccommon.AbstractAocBase;
import de.chrlembeck.aoccommon.Result;

public class Aoc2017Day11 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2017Day11().run();
    }

    @Override
    public String part1(final Scanner input) {
        return calc(input).getPart1().toString();
    }

    public static int calcDist(int xPos, int yPos) {
        xPos = Math.abs(xPos);
        yPos = Math.abs(yPos);
        yPos = Math.max(0, yPos - xPos);
        return xPos + yPos / 2;
    }

    @Override
    public String part2(final Scanner input) {
        return calc(input).getPart2().toString();
    }

    public Result<Integer> calc(final Scanner input) {
        input.useDelimiter(",");
        int xPos = 0, yPos = 0, maxDist = 0, dist = 0;
        while (input.hasNext()) {
            final String direction = input.next();
            switch (direction) {
                case "n":
                    yPos -= 2;
                    break;
                case "s":
                    yPos += 2;
                    break;
                case "ne":
                    yPos--;
                    xPos++;
                    break;
                case "nw":
                    yPos--;
                    xPos--;
                    break;
                case "se":
                    yPos++;
                    xPos++;
                    break;
                case "sw":
                    yPos++;
                    xPos--;
                    break;
                default:
                    throw new IllegalArgumentException(direction);
            }
            dist = calcDist(xPos, yPos);
            maxDist = Math.max(maxDist, dist);
        }
        return new Result<>(dist, maxDist);
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2017/aoc2017day11.txt";
    }
}
