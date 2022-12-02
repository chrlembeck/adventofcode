package de.chrlembeck.aoc2015.day15;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Aoc2015Day15 extends AbstractAocBase {

    private static final Pattern REGEX = Pattern.compile(
            "(\\w+): capacity (-?\\d+), durability (-?\\d+), flavor (-?\\d+), texture (-?\\d+), calories (-?\\d+)");

    public static void main(final String[] args) {
        new Aoc2015Day15().run();
    }

    @Override
    public Long part1(final Scanner input) {
        return calc(input, false);
    }

    @Override
    public Long part2(final Scanner input) {
        return calc(input, true);
    }

    private long calc(final Scanner input, final boolean reduceCalories) {
        List<Ingedient> ingredients = input.findAll(REGEX).map(Ingedient::new).collect(Collectors.toList());
        MaxFinder max = new MaxFinder();

        Consumer<int[]> mixer = test -> {
            long capacity = 0;
            long durability = 0;
            long flavor = 0;
            long texture = 0;
            long calories = 0;
            for (int i = 0; i < ingredients.size(); i++) {
                capacity += test[i] * ingredients.get(i).getCapacity();
                durability += test[i] * ingredients.get(i).getDurability();
                flavor += test[i] * ingredients.get(i).getFlavor();
                texture += test[i] * ingredients.get(i).getTexture();
                calories += test[i] * ingredients.get(i).getCalories();
            }
            long score = (!reduceCalories || calories == 500) && (capacity > 0 && durability > 0 && flavor > 0 && texture > 0) ? capacity * durability * flavor * texture : 0;
            max.accept(score);
        };

        createRecipes(new int[ingredients.size()], 0, mixer);
        return max.max;
    }

    private void createRecipes(int[] recipe, int col, Consumer<int[]> reciepeConsumer) {
        int remaining = 100;
        for (int i = 0; i < col; i++) {
            remaining -= recipe[i];
        }
        if (col == recipe.length - 1) {
            recipe[col] = remaining;
            reciepeConsumer.accept(recipe);
        } else {
            for (int i = 0; i <= remaining; i++) {
                recipe[col] = i;
                createRecipes(recipe, col + 1, reciepeConsumer);
            }
        }

    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2015/aoc2015day15.txt";
    }

    static class MaxFinder {
        long max = Long.MIN_VALUE;

        void accept(long value) {
            max = Math.max(max, value);
        }
    }
}