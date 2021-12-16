package de.chrlembeck.aoc2015.day16;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Aoc2015Day16 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2015Day16().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        input.useDelimiter("\n");
        List<Sue> sues = input.tokens().map(Sue::new).collect(Collectors.toList());
        Sue sue = sues.stream().filter(s -> s.matches(3, 7, 2, 3, 0, 0, 5, 3, 2, 1)).findAny().get();
        return sue.getNr();
    }

    @Override
    public Integer part2(final Scanner input) {
        input.useDelimiter("\n");
        List<Sue> sues = input.tokens().map(Sue::new).collect(Collectors.toList());
        Sue sue = sues.stream().filter(s -> s.matches2(3, 7, 2, 3, 0, 0, 5, 3, 2, 1)).findAny().get();
        return sue.getNr();
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2015/aoc2015day16.txt";
    }
}