package de.chrlembeck.aoc2016.day14;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Scanner;
import java.util.TreeMap;

public class Aoc2016Day14 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2016Day14().run();
    }

    @Override
    public Long part1(final Scanner input) {
        return calc(input, false);
    }

    @Override
    public Long part2(final Scanner input) {
        return calc(input, true);
    }

    private long calc(final Scanner input, boolean step2) {
        String salt = input.nextLine();
        TreeMap<Long, Data> dataMap = new TreeMap<>();
        long index = -1;
        int count = 0;
        while (count < 64) {
            index++;
            Data data = dataMap.computeIfAbsent(index, idx -> new Data(salt, idx, step2));
            if (data.getTripletChar() != null) {
                for (int i = 1; i < 1000; i++) {
                    if (dataMap.computeIfAbsent(index + i, idx -> new Data(salt, idx, step2)).hasQuint(data.getTripletChar())) {
                        count++;
                        break;
                    }
                }
            }
        }
        return index;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2016/aoc2016day14.txt";
    }
}