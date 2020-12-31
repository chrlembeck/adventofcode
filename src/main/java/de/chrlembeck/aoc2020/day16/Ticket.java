package de.chrlembeck.aoc2020.day16;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class Ticket {

    private int[] numbers;

    public Ticket(String line) {
        this.numbers = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
    }

    public int errorRate(Collection<Condition> conditions) {
        return Arrays.stream(numbers).filter(valueMatches(conditions).negate()).sum();
    }

    public static IntPredicate valueMatches(Collection<Condition> conditions) {
        return number -> conditions.stream().anyMatch(c -> c.contains(number));
    }

    public static Predicate<Ticket> matchesInAnyOrder(Collection<Condition> conditions) {
        return t-> Arrays.stream(t.numbers).allMatch(valueMatches(conditions));
    }

    public static Predicate<Ticket> matchesOrdered(Condition[] conditions) {
        return ticket -> {
            for (int i = 0; i < ticket.numbers.length; i++) {
                if (!conditions[i].contains(ticket.numbers[i])) {
                    return false;
                }
            }
            return true;
        };
    }

    public boolean fitsAt(Condition condition, int positionIdx) {
        return condition.contains(numbers[positionIdx]);
    }

    public int getNumber(int idx) {
        return numbers[idx];
    }
}