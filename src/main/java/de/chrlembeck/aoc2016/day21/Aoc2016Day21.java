package de.chrlembeck.aoc2016.day21;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Aoc2016Day21 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2016Day21().run();
    }

    @Override
    public String part1(final Scanner input) {
        String text = "abcdefgh";
        List<ScramblingOperation> operations = new ArrayList<>();
        while (input.hasNextLine()) {
            operations.add(ScramblingOperation.createOperation(input.nextLine()));
        }
        for (ScramblingOperation op : operations) {
            text = op.scramble(text);
        }
        return text;
    }

    @Override
    public String part2(final Scanner input) {
        String text = "fbgdceah";
        List<ScramblingOperation> operations = new ArrayList<>();
        while (input.hasNextLine()) {
            operations.add(ScramblingOperation.createOperation(input.nextLine()));
        }
        Collections.reverse(operations);
        for (ScramblingOperation op : operations) {
            text = op.unscramble(text);
        }
        return text;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2016/aoc2016day21.txt";
    }
}