package de.chrlembeck.aoc2020.day12;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Scanner;

public class Aoc2020Day12 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2020Day12().run();
    }

    private Robot ship;

    private Position waypoint;

    @Override
    public Object part1(final Scanner input) {
        ship = new Robot(new Position(0, 0), Direction.EAST);
        input.findAll("([NSEWLRF])(\\d+)").forEachOrdered(mr -> ship = walk1(ship, mr.group(1), Integer.parseInt(mr.group(2))));
        return ship.getManhattan();
    }

    @Override
    public Object part2(final Scanner input) {
        ship = new Robot(new Position(0, 0), Direction.EAST);
        waypoint = new Position(0, 0).north(1).east(10);
        input.findAll("([NSEWLRF])(\\d+)").forEachOrdered(mr -> walk2(mr.group(1), Integer.parseInt(mr.group(2))));
        return ship.getManhattan();
    }

    private static Robot walk1(final Robot ship, final String action, final int value) {
        return switch (action) {
            case "N" -> ship.north(value);
            case "S" -> ship.south(value);
            case "E" -> ship.east(value);
            case "W" -> ship.west(value);
            case "L" -> ship.left(value);
            case "R" -> ship.right(value);
            case "F" -> ship.forward(value);
            default -> throw new IllegalArgumentException();
        };
    }

    private void walk2(final String action, final int value) {
        switch (action) {
            case "N":
                waypoint = waypoint.north(value);
                break;
            case "S":
                waypoint = waypoint.south(value);
                break;
            case "E":
                waypoint = waypoint.east(value);
                break;
            case "W":
                waypoint = waypoint.west(value);
                break;
            case "L":
                waypoint = waypoint.rotateLeft(value);
                break;
            case "R":
                waypoint = waypoint.rotateRight(value);
                break;
            case "F":
                ship = ship.translate(waypoint.getX() * value, waypoint.getY() * value);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2020/aoc2020day12.txt";
    }
}