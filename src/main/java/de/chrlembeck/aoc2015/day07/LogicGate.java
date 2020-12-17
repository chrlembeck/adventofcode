package de.chrlembeck.aoc2015.day07;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogicGate implements Gate {

    public static final Pattern LOGIC = Pattern.compile("(\\w*)\\ (AND|OR)\\ (\\w*)\\ ->\\ ([a-z]*)");

    private final Gate left;

    private final Gate right;

    private final boolean isAnd;

    public LogicGate(final Gate left, final Gate right, final boolean isAnd) {
        this.left = left;
        this.right = right;
        this.isAnd = isAnd;
    }

    public static boolean accepts(final Map<String, Gate> program, final String line) {
        final Matcher matcher = LOGIC.matcher(line);
        if (matcher.matches()) {
            final Gate left = Aoc2015Day07.parse(matcher.group(1));
            final Gate right = Aoc2015Day07.parse(matcher.group(3));
            program.put(matcher.group(4),
                    new LogicGate(left, right, "AND".equals(matcher.group(2))));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int execute(final Map<String, Gate> program) {
        final int leftValue = this.left.execute(program);
        final int rightValue = this.right.execute(program);
        return isAnd ? (leftValue & rightValue) : (leftValue | rightValue);
    }

    @Override
    public String toString() {
        return left + (isAnd ? " AND " : " OR ") + right;
    }
}