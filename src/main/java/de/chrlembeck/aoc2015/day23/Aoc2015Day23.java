package de.chrlembeck.aoc2015.day23;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2015Day23 extends AbstractAocBase {

    private final Pattern pattern = Pattern.compile("(\\w{3}) ([ab]|[+-]?\\d+)(, ([+-]?\\d+))?");

    public static void main(final String[] args) {
        new Aoc2015Day23().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        List<Operation> operations = tokenStream(input, "\\n", pattern, this::parseOperation).toList();
        State state = new State();
        while (state.pc < operations.size()) {
            Operation operation = operations.get(state.pc);
            operation.operate(state);
        }
        return state.registerB;
    }

    @Override
    public Integer part2(final Scanner input) {
        List<Operation> operations = tokenStream(input, "\\n", pattern, this::parseOperation).toList();
        State state = new State();
        state.registerA = 1;
        while (state.pc < operations.size()) {
            Operation operation = operations.get(state.pc);
            operation.operate(state);
        }
        return state.registerB;
    }


    private Operation parseOperation(Matcher matcher) {
        Operation operation = new Operation();
        operation.operation = matcher.group(1);
        String first = matcher.group(2);
        if (first.length() == 1 && Character.isLetter(first.charAt(0))) {
            operation.register = first;
        } else {
            operation.value = Integer.parseInt(first);
        }
        String second = matcher.group(4);
        if (second != null && second.length() > 0) {
            operation.value = Integer.parseInt(second);
        }
        return operation;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2015/aoc2015day23.txt";
    }

    static class Operation {
        String operation;
        Integer value;
        String register;

        public void operate(State state) {
            switch (operation) {
                case "hlf":
                    if (register.equals("a")) {
                        state.registerA /= 2;
                    } else {
                        state.registerB /= 2;
                    }
                    state.pc++;
                    break;
                case "tpl":
                    if (register.equals("a")) {
                        state.registerA *= 3;
                    } else {
                        state.registerB *= 3;
                    }
                    state.pc++;
                    break;
                case "inc":
                    if (register.equals("a")) {
                        state.registerA++;
                    } else {
                        state.registerB++;
                    }
                    state.pc++;
                    break;
                case "jmp":
                    state.pc += value;
                    break;
                case "jie":
                    if (register.equals("a") && (state.registerA % 2 == 0)) {
                        state.pc += value;
                    } else if (register.equals("b") && (state.registerB % 2 == 0)) {
                        state.pc += value;
                    } else {
                        state.pc++;
                    }
                    break;
                case "jio":
                    if (register.equals("a") && state.registerA == 1) {
                        state.pc += value;
                    } else if (register.equals("b") && state.registerB == 1) {
                        state.pc += value;
                    } else {
                        state.pc++;
                    }
                    break;
                default:
                    throw new IllegalStateException("unknown operation: " + operation);
            }
        }
    }

    static class State {
        int pc = 0;
        int registerA = 0;
        int registerB = 0;
    }
}