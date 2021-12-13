package de.chrlembeck.aoc2016.day19;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Scanner;

public class Aoc2016Day19 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2016Day19().run();
    }

    @Override
    public Integer part1(final Scanner scanner) {
        Integer input = scanner.nextInt();
        Item start = new Item(1);
        Item current = start;
        for (int i = 2; i <= input; i++) {
            current.next = new Item(i);
            current = current.next;
        }
        current.next = start;

        current = start;
        while (current.next != current) {
            current.next = current.next.next;
            current = current.next;
        }
        return current.value;
    }

    @Override
    public Integer part2(final Scanner scanner) {
        Integer input = scanner.nextInt();

        Item start = new Item(1);
        Item current = start;
        for (int i = 2; i <= input; i++) {
            current.next = new Item(i);
            current = current.next;
        }
        current.next = start;

        current = start;
        Item looser = current;
        for (int i = 0; i < input/2-1; i++) {
            looser = looser.next;
        }
        boolean twoSteps = input % 2 == 1;
        while (current.next != current) {
            looser.next = looser.next.next;
            if (twoSteps) {
                looser = looser.next;
            }
            twoSteps = !twoSteps;
            current = current.next;
        }
        return current.value;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2016/aoc2016day19.txt";
    }

    static class Item {
        Item next;
        int value;
        public Item(int value) {
            this.value = value;
        }
    }
}