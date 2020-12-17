package de.chrlembeck.aoc2015.day07;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefineGate implements Gate {

    public static final Pattern DEFINE = Pattern.compile("(\\w+)\\ ->\\ ([a-z]*)");

    private final String varName;

    public DefineGate(final String varName) {
        this.varName = varName;
    }

    public static boolean accepts(final Map<String, Gate> program, final String line) {
        final Matcher matcher = DEFINE.matcher(line);
        if (matcher.matches()) {
            final Gate left = Aoc2015Day07.parse(matcher.group(1));
            final String right = matcher.group(2);
            program.put(right, left);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int execute(final Map<String, Gate> program) {
        final Gate gate = program.get(this.varName);
        final int value = gate.execute(program);
        if (!(gate instanceof Constant)) {
            program.put(varName, new Constant(value));
        }
        return value;
    }

    @Override
    public String toString() {
        return varName;
    }
}