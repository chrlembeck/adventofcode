package de.chrlembeck.aoc2015.day21;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;

public class Aoc2015Day21 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2015Day21().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        return simulate(input, true, Math::min, Integer.MAX_VALUE);
    }

    @Override
    public Integer part2(final Scanner input) {
        return simulate(input, false, Math::max, Integer.MIN_VALUE);
    }

    public int simulate(Scanner input, boolean win, BiFunction<Integer, Integer, Integer> resultAggregator, int startCost){
        int hitPoints = Integer.parseInt(input.nextLine().split(": ")[1]);
        int damage = Integer.parseInt(input.nextLine().split(": ")[1]);
        int amor = Integer.parseInt(input.nextLine().split(": ")[1]);
        Player boss = new Player(hitPoints, damage, amor);

        List<Item> weapons = List.of(
                new Item("Dagger", 8, 4, 0),
                new Item("Shortsword", 10, 5, 0),
                new Item("Warhammer", 25, 6, 0),
                new Item("Longsword", 40, 7, 0),
                new Item("Greataxe", 74, 8, 0)
        );

        List<Item> armors = List.of(
                new Item("Leater", 13, 0, 1),
                new Item("Chainmail", 31, 0, 2),
                new Item("Splintmail", 53, 0, 3),
                new Item("Bandemail", 75, 0, 4),
                new Item("Platemail", 102, 0, 5),
                new Item("none", 0, 0, 0)
        );

        List<Item> rings = List.of(
                new Item("Damage +1", 25, 1, 0),
                new Item("Damage +2", 50, 2, 0),
                new Item("Damage +3", 100, 3, 0),
                new Item("Defense +1", 20, 0, 1),
                new Item("Defense +2", 40, 0, 2),
                new Item("Defense +3", 80, 0, 3),
                new Item("none", 0, 0, 0),
                new Item("none", 0, 0, 0)
        );

        int resultCost = startCost;
        for (Item weapon : weapons) {
            for (Item armor : armors) {
                for (Item ring1 : rings) {
                    for (Item ring2 : rings) {
                        if (ring1 == ring2) {
                            continue;
                        }
                        int cost = weapon.getCost() + armor.getCost() + ring1.getCost() + ring2.getCost();
                        Player player = new Player(100, weapon.getDamage() + armor.getDamage() + ring1.getDamage() + ring2.getDamage(),
                                weapon.getArmor() + armor.getArmor() + ring1.getArmor() + ring2.getArmor());
                        if (win == simulate(boss, player)) {
                            resultCost = resultAggregator.apply(resultCost, cost);
                        }
                    }
                }
            }
        }
        return resultCost;
    }

    public boolean simulate(Player boss, Player player) {
        int bossHit = boss.hitPoints;
        int playherHit = player.hitPoints;
        while (true) {
            bossHit -= Math.max(1, player.damage - boss.armor);
            if (bossHit <= 0) {
                return true;
            }
            playherHit -= Math.max(1, boss.damage - player.armor);
            if (playherHit <= 0) {
                return false;
            }
        }
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2015/aoc2015day21.txt";
    }
}