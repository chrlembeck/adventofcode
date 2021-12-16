package de.chrlembeck.aoc2015.day15;

import java.util.regex.MatchResult;

public class Ingedient {

    private String name;

    private int capacity;

    private int durability;

    private int flavor;

    private int texture;

    private int calories;

    public Ingedient(MatchResult matcher) {
        name = matcher.group(1);
        capacity = Integer.parseInt(matcher.group(2));
        durability = Integer.parseInt(matcher.group(3));
        flavor = Integer.parseInt(matcher.group(4));
        texture = Integer.parseInt(matcher.group(5));
        calories = Integer.parseInt(matcher.group(6));
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getDurability() {
        return durability;
    }

    public int getFlavor() {
        return flavor;
    }

    public int getTexture() {
        return texture;
    }

    public int getCalories() {
        return calories;
    }
}