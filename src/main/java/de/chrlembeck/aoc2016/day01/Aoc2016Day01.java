package de.chrlembeck.aoc2016.day01;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Scanner;

public class Aoc2016Day01 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2016Day01().run();
    }

    @Override
    public Object part1(final Scanner input) {
        return startRobot(input, false);
    }

    public int startRobot(final Scanner input, final boolean stopAtTwiceVisitedPosition) {
        final Robot robot = new Robot();
        input.useDelimiter(", ");
        while (input.hasNext()) {
            final String token = input.next();
            if (Character.toUpperCase(token.charAt(0)) == 'L') {
                robot.left();
            } else {
                robot.right();
            }
            final int distance = Integer.parseInt(token.substring(1));
            for (int i = 0; i < distance; i++) {
                if (robot.walk() && stopAtTwiceVisitedPosition) {
                    return Math.abs(robot.getX()) + Math.abs(robot.getY());
                }
            }
        }
        return Math.abs(robot.getX()) + Math.abs(robot.getY());
    }

    @Override
    public Integer part2(final Scanner input) {
        return startRobot(input, true);
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2016/aoc2016day01.txt";
    }
}