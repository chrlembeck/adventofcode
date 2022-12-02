package de.chrlembeck.aoc2022.day01;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.max;

public class Aoc2022Day01 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2022Day01().run();
    }

    @Override
    public Long part1(final Scanner input) {
        long max = 0;
        long current = 0;

        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.isEmpty())  {
                current = 0;
            } else {
                current += Long.parseLong(line);
                max = max(current, max);
            }
        }
        return max;
    }

    @Override
    public Long part2(final Scanner input) {
        long current = 0;
        List<Long> calories = new ArrayList<>();
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.isEmpty())  {
                calories.add(current);
                current = 0;
            } else {
                current += Long.parseLong(line);
            }
        }
        calories.add(current);
        Collections.sort(calories, Collections.reverseOrder());
        return calories.get(0) + calories.get(1) + calories.get(2);
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2022/aoc2022day01.txt";
    }
}