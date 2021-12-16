package de.chrlembeck.aoc2015.day14;

import java.util.regex.MatchResult;

public class Reindeer {

    private String name;

    private int speed;

    private int flyTime;

    private int restTime;

    public Reindeer(MatchResult matcher) {
        name = matcher.group(1);
        speed = Integer.parseInt(matcher.group(2));
        flyTime = Integer.parseInt(matcher.group(3));
        restTime = Integer.parseInt(matcher.group(4));
    }

    public String getName() {
        return name;
    }

    public int getDistance(int time) {
        int cycles = time / (flyTime + restTime);
        int remainder = time % (flyTime + restTime);
        return (cycles * flyTime + Math.min(remainder, flyTime)) * speed;
    }
}
