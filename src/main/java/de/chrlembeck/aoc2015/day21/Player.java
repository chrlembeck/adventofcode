package de.chrlembeck.aoc2015.day21;

public class Player {

    final int hitPoints;

    final int damage;

    final int armor;

    public Player(int hitPoints, int damage, int armor) {
        this.hitPoints = hitPoints;
        this.damage = damage;
        this.armor = armor;
    }

    public Player(Player origin) {
        this.hitPoints = origin.hitPoints;
        this.damage = origin.damage;
        this.armor = origin.armor;
    }

}
