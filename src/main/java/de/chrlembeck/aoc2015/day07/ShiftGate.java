package de.chrlembeck.aoc2015.day07;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShiftGate implements Gate {

    public final static Pattern SHIFT = Pattern.compile("(\\w*)\\ (LSHIFT|RSHIFT)\\ (\\w+)\\ ->\\ ([a-z]*)");

    private boolean leftShift;

    private Gate right;

    private Gate left;

    public ShiftGate(final Gate left, final Gate right, final boolean leftShift) {
        this.right = right;
        this.left = left;
        this.leftShift = leftShift;
    }

    public static boolean accepts(final Map<String, Gate> program, final String line) {
        final Matcher matcher = SHIFT.matcher(line);
        if (matcher.matches()) {
            final Gate left = Aoc2015Day07.parse(matcher.group(1));
            final Gate right = Aoc2015Day07.parse(matcher.group(3));
            program.put(matcher.group(4), new ShiftGate(left, right, "LSHIFT".equals(matcher.group(2))));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int execute(final Map<String, Gate> program) {
        final int leftValue = left.execute(program);
        final int rightValue = right.execute(program);
        final int value = leftShift ? ((leftValue << rightValue) & 0xffff) : ((leftValue >> rightValue) & 0xffff);
        return value;
    }

    @Override
    public String toString() {
        return left + (leftShift ? " LSHIFT " : " RSHIFT ") + right;
    }

}