package de.chrlembeck.aoc2020.day02;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Aoc2020Day02 extends AbstractAocBase {

    private static final Pattern REGEX = Pattern.compile("(\\d+)-(\\d+) (\\w): (\\w+)");

    public static void main(final String[] args) {
        new Aoc2020Day02().run();
    }

    @Override
    public Object part1(final Scanner input) {
        return input.findAll(REGEX)
                .filter(mr -> checkPassword1(Integer.parseInt(mr.group(1)), Integer.parseInt(mr.group(2)), mr.group(3).charAt(0), mr.group(4))).count();
    }

    private boolean checkPassword1(int min, int max, char letter, String password) {
        int counter = 0;
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) == letter) {
                counter++;
            }
        }
        return counter <= max && counter >= min;
    }

    @Override
    public Object part2(final Scanner input) {
        return input.findAll(REGEX)
                .filter(mr -> checkPassword2(Integer.parseInt(mr.group(1)), Integer.parseInt(mr.group(2)), mr.group(3).charAt(0), mr.group(4))).count();
    }

    private boolean checkPassword2(int first, int second, char letter, String password) {
        return password.charAt(first - 1) == letter ^ password.charAt(second - 1) == letter;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2020/aoc2020day02.txt";
    }
}