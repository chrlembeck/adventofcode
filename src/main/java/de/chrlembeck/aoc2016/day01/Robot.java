package de.chrlembeck.aoc2016.day01;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Robot {

    private int posX;

    private int posY;

    private final Set<Point> history;

    private Direction direction = Direction.NORTH;

    public Robot() {
        history = new HashSet<>();
        history.add(new Point(posX, posY));
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
                posY--;
                break;
            case SOUTH:
                posY++;
                break;
            case EAST:
                posX++;
                break;
            case WEST:
                posX--;
                break;
        }
        return !history.add(new Point(posX, posY));
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}