package de.chrlembeck.aoc2020.day21;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class Food {

    private final Set<String> ingredients;

    private final Set<String> allergens;

    public Food(final String[] ingredients, final String... allergens) {
        this.ingredients = new TreeSet<>();
        Arrays.stream(ingredients).forEach(this.ingredients::add);
        this.allergens = new TreeSet<>();
        Arrays.stream(allergens).forEach(this.allergens::add);
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public Set<String> getAllergens() {
        return allergens;
    }

    public boolean containsIngredient(final String ingredient) {
        return ingredients.contains(ingredient);
    }

    public boolean containsAllergen(final String allergen) {
        return allergens.contains(allergen);
    }

    public void removeIngredients(final Set<String> inertIngredients) {
        ingredients.removeAll(inertIngredients);
    }
}