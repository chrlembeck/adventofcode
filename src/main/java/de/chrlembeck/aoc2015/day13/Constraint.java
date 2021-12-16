package de.chrlembeck.aoc2015.day13;

import java.util.regex.MatchResult;

public class Constraint {

    private String name1;

    private String name2;

    private int value;

    public Constraint(MatchResult matcher) {
        name1 = matcher.group(1);
        boolean gain = "gain".equals(matcher.group(2));
        value = gain ? Integer.parseInt(matcher.group(3)) : -Integer.parseInt(matcher.group(3));
        name2 = matcher.group(4);
    }

    public String getName1() {
        return name1;
    }

    public String getName2() {
        return name2;
    }

    public int getValue() {
        return value;
    }

    public boolean fits(String name1, String name2) {
        return (this.name1.equals(name1) && this.name2.equals(name2)) || (this.name1.equals(name2) && this.name2.equals(name1));
    }
}