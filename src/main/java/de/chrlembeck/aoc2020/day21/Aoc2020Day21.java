package de.chrlembeck.aoc2020.day21;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Aoc2020Day21 extends AbstractAocBase {

    private static final Pattern REGEX = Pattern.compile("(\\w+( \\w+)*) \\(contains (\\w+(, \\w+)*)\\)");

    private static final Predicate<? super Collection<?>> MORE_THAN_ONE = collection -> collection.size() > 1;

    private static final Predicate<? super Collection<?>> EXACT_ONE = collection -> collection.size() == 1;

    public static void main(final String[] args) {
        new Aoc2020Day21().run();
    }

    @Override
    public Object part1(final Scanner input) {
        final List<Food> foods = input.useDelimiter("\n").tokens().map(this::toFood).collect(Collectors.toList());
        return inertIngredients(foods).stream().mapToLong(ingredient -> foods.stream().filter(f -> f.containsIngredient(ingredient)).count()).sum();
    }

    @Override
    public Object part2(final Scanner input) {
        final List<Food> foods = input.useDelimiter("\n").tokens().map(this::toFood).collect(Collectors.toList());
        final Set<String> inertIngredients = inertIngredients(foods);
        foods.stream().forEach(food -> food.removeIngredients(inertIngredients));
        final Map<String, Set<String>> allergenToIngredient = new TreeMap<>();
        foods.stream().map(Food::getAllergens)
                .flatMap(Set::stream)
                .forEach(allergen -> allergenToIngredient
                        .put(allergen, intersection(foods.stream().filter(food -> food.containsAllergen(allergen)).map(Food::getIngredients))));

        while (allergenToIngredient.values().stream().anyMatch(MORE_THAN_ONE)) {
            allergenToIngredient.values().stream()
                    .filter(EXACT_ONE)
                    .forEach(singleIngredient -> allergenToIngredient.values().stream().filter(MORE_THAN_ONE)
                            .forEach(ingredients -> ingredients.removeAll(singleIngredient)));
        }
        return allergenToIngredient.values().stream().map(set -> set.stream().findAny().get()).collect(Collectors.joining(","));
    }

    private Set<String> inertIngredients(final List<Food> foods) {
        final Set<String> inertIngredients = foods.stream().map(Food::getIngredients).flatMap(Set::stream).collect(Collectors.toSet());
        foods.stream()
                .map(Food::getAllergens)
                .flatMap(Set::stream)
                .forEach(allergen -> inertIngredients
                        .removeAll(intersection(foods.stream().filter(food -> food.containsAllergen(allergen)).map(Food::getIngredients))));
        return inertIngredients;
    }

    private Food toFood(final String line) {
        final Matcher matcher = REGEX.matcher(line);
        matcher.matches();
        return new Food(matcher.group(1).split(" "), matcher.group(3).split(", "));
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2020/aoc2020day21.txt";
    }

    public <T> Set<T> intersection(final Stream<Set<T>> stream) {
        return stream.reduce((a, b) -> {
            final Set<T> result = new TreeSet<>(a);
            result.retainAll(b);
            return result;
        }).orElseThrow();
    }
}