package de.chrlembeck.aoc2015.day07;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NotGate implements Gate {

    public static final Pattern NOT = Pattern.compile("NOT\\ ([a-z]*)\\ ->\\ ([a-z]*)");

    private final Gate operand;

    public NotGate(final Gate operand) {
        this.operand = operand;
    }

    public static boolean accepts(final Map<String, Gate> program, final String line) {
        final Matcher matcher = NOT.matcher(line);
        if (matcher.matches()) {
            program.put(matcher.group(2), new NotGate(Aoc2015Day07.parse(matcher.group(1))));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int execute(final Map<String, Gate> program) {
        final int value = operand.execute(program);
        return 0xffff & ~value;
    }

    @Override
    public String toString() {
        return "NOT " + operand;
    }
}