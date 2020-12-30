package de.chrlembeck.aoc2020.day14;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2020Day14 extends AbstractAocBase {

    public static final Pattern REGEX = Pattern.compile("mem\\[(\\d+)\\] = (\\d+)");

    public static void main(final String[] args) {
        new Aoc2020Day14().run();
    }

    @Override
    public Object part1(final Scanner input) {
        long zeros = 0;
        long ones = 0;
        Map<Long, Long> memory = new TreeMap<>();
        while (input.hasNextLine()) {
            String line = input.nextLine();
            Matcher matcher = REGEX.matcher(line);
            if (matcher.matches()) {
                memory.put(Long.valueOf(matcher.group(1)), (Long.valueOf(matcher.group(2)) & zeros) | ones);
            } else {
                zeros = 0xffffffff;
                ones = 0;
                for (int i = 0; i < 36; i++) {
                    final char character = line.charAt(line.length() - i - 1);
                    if (character == '0') {
                        zeros ^= 1l << i;
                    } else if (character == '1') {
                        ones |= 1l << i;
                    }
                }
            }
        }
        return memory.values().stream().mapToLong(Long::longValue).sum();
    }

    @Override
    public Object part2(final Scanner input) {
        Map<Long, Long> memory = new TreeMap<>();
        String mask = null;
        while (input.hasNextLine()) {
            String line = input.nextLine();
            Matcher matcher = REGEX.matcher(line);
            if (matcher.matches()) {
                writeValue(memory, mask, Long.parseLong(matcher.group(1)), Long.valueOf(matcher.group(2)));
            } else {
                mask = line.substring(7);
            }
        }
        return memory.values().stream().mapToLong(Long::longValue).sum();
    }

    private void writeValue(final Map<Long, Long> memory, final String mask, final long address, final Long value) {
        if (mask.contains("X")) {
            final int idx = mask.length() - mask.indexOf("X") - 1;
            writeValue(memory, mask.replaceFirst("X", "0"), address & (0xffffffff ^ (1l << idx)), value);
            writeValue(memory, mask.replaceFirst("X", "1"), address, value);
        } else {
            memory.put(address | Long.parseLong(mask, 2), value);
        }
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2020/aoc2020day14.txt";
    }
}