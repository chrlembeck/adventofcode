package de.chrlembeck.aoc2016.day09;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Scanner;

public class Aoc2016Day09 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2016Day09().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        String file = input.nextLine();
        int fileLength = 0;
        int idx = file.indexOf('(');
        while (idx != -1) {
            fileLength += idx;
            file = file.substring(idx + 1);
            idx = file.indexOf(')');
            final String rep = file.substring(0, idx);
            file = file.substring(idx + 1);
            final String[] reps = rep.split("x");
            final int length = Integer.parseInt(reps[0]);
            file = file.substring(length);
            fileLength += Integer.parseInt(reps[1]) * length;
            idx = file.indexOf('(');
        }
        fileLength += file.length();

        return fileLength;
    }

    @Override
    public Long part2(final Scanner input) {
        final String file = input.nextLine();
        return length(file);
    }

    public static long length(String file) {
        int idx = file.indexOf('(');
        if (idx == -1) {
            return file.length();
        }
        if (idx > 0) {
            return idx + length(file.substring(idx));
        }
        if (idx == 0) {
            idx = file.indexOf(')');
            final String rep = file.substring(1, idx);
            file = file.substring(idx + 1);
            final String[] reps = rep.split("x");
            final int length = Integer.parseInt(reps[0]);
            final int count = Integer.parseInt(reps[1]);
            final String inner = file.substring(0, length);
            file = file.substring(length);
            return count * length(inner) + length(file);
        }
        throw new IllegalStateException();
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2016/aoc2016day09.txt";
    }
}