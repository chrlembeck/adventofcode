package de.chrlembeck.aoc2016.day04;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Scanner;

public class Aoc2016Day04 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2016Day04().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        return input.useDelimiter("\n").tokens().map(Room::new).filter(Room::isValid).mapToInt(Room::getId).sum();
    }

    @Override
    public Integer part2(final Scanner input) {
        return input.useDelimiter("\n").tokens().map(Room::new).filter(r -> r.decrypt().equals("northpole object storage")).findAny().get().getId();
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2016/aoc2016day04.txt";
    }
}