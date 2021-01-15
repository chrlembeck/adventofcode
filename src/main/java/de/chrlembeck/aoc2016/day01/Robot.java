package de.chrlembeck.aoc2016.day01;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Robot {

    private int x;

    private int y;

    private final Set<Point> history;

    private Direction direction = Direction.NORTH;

    public Robot() {
        history = new HashSet<>();
        history.add(new Point(x, y));
    }

    public void left(){
        direction = direction.left();
    }

    public void right(){
        direction = direction.right();
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean walk() {
        switch (direction) {
            case NORTH:
                y--;
                break;
            case SOUTH:
                y++;
                break;
            case EAST:
                x++;
                break;
            case WEST:
                x--;
                break;
        }
        return !history.add(new Point(x, y));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}