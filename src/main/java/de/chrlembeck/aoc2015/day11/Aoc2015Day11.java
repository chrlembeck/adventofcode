package de.chrlembeck.aoc2015.day11;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Scanner;

public class Aoc2015Day11 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2015Day11().run();
    }

    @Override
    public String part1(final Scanner input) {
        final char[] password = input.nextLine().toCharArray();
        nextValid(password);
        return new String(password);
    }

    @Override
    public String part2(final Scanner input) {
        final char[] password = input.nextLine().toCharArray();
        nextValid(password);
        nextValid(password);
        return new String(password);
    }

    public char[] nextValid(final char[] password) {
        do {
            next(password);
        } while (!valid(password));
        return password;
    }

    public char[] next(final char[] password) {
        password[password.length - 1]++;
        int pos = password.length - 1;
        while (password[pos] > 'z' && pos >=0) {
            password[pos] = 'a';
            pos--;
            password[pos]++;
        }
        return password;
    }

    public boolean valid(final char[] password) {
        for (final char character : password) {
            if (character == 'i' || character == 'o' || character == 'l') {
                return false;
            }
        }
        boolean rule1 = false;
        for (int i = 2; i < password.length; i++) {
            if ((password[i-2] == password[i-1]-1) && (password[i-1] == password[i]-1)) {
                rule1 = true;
            }
        }
        int pairs = 0;
        for (int i = 1; i < password.length; i++) {
            if (password[i-1] == password[i]) {
                pairs++;
                i++;
            }
        }
        return rule1 && pairs >= 2;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2015/aoc2015day11.txt";
    }
}