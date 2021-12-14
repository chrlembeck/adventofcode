package de.chrlembeck.aoc2016.day18;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Scanner;

public class Aoc2016Day18 extends AbstractAocBase {

    public static final char TRAP = '^';

    public static final char SAFE = '.';

    public static void main(final String[] args) {
        new Aoc2016Day18().run();
    }

    @Override
    public Long part1(final Scanner input) {
        String line = input.nextLine();
        return calculate(line, 40);
    }

    @Override
    public Long part2(final Scanner input) {
        String line = input.nextLine();
        return calculate(line, 400000);
    }

    private Long calculate(String line, int count) {
        long anz = 0;
        for (int i = 0; i < count; i++) {
            anz+= count(line, SAFE);
            String newLine = "" + compute(SAFE, line.charAt(0), line.charAt(1));
            for (int j = 1; j < line.length()-1; j++) {
                newLine = newLine + compute(line.charAt(j-1), line.charAt(j), line.charAt(j+1));
            }
            newLine = newLine + compute(line.charAt(line.length()-2), line.charAt(line.length()-1), SAFE);
            line = newLine;
        }
        return anz;
    }

    public static int count(String input, char ch) {
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }

    public static char compute(char left, char center, char right) {
        return (left == TRAP && center == TRAP && right == SAFE) || (center == TRAP && right == TRAP && left == SAFE)
                || (left == TRAP && center == SAFE && right == SAFE)
                || (right == TRAP && left == SAFE && center == SAFE) ? TRAP : SAFE;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2016/aoc2016day18.txt";
    }
}