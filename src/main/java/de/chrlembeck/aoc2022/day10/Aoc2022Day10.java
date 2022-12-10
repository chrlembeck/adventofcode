package de.chrlembeck.aoc2022.day10;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2022Day10 extends AbstractAocBase {

    private final Pattern pattern = Pattern.compile("(noop|addx)( (-?\\d+))?");

    public static void main(final String[] args) {
        new Aoc2022Day10().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        List<Instruction> instructions = tokenStream(input, "\\n", pattern, this::parseInstruction).toList();
        AtomicInteger result = new AtomicInteger(0);
        State state = new State(instructions, (pc,cycle,x)->{if (cycle %40==20) {result.addAndGet(cycle*x);}});
        while (state.isRunning()) {
            state.runInstruction();
        }
        return result.get();
    }

    private Instruction parseInstruction(Matcher matcher) {
        String command = matcher.group(1);
        if ("noop".equals(command)) {
            return new Noop();
        } else if ("addx".equals(command)) {
            return new AddX(Integer.parseInt(matcher.group(3)));
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String part2(final Scanner input) {
        List<Instruction> instructions = tokenStream(input, "\\n", pattern, this::parseInstruction).toList();
        StringBuilder sb = new StringBuilder();
        State state = new State(instructions, (pc, cycle, x) -> {
            sb.append(Math.abs((cycle - 1) % 40 - x) <= 1 ? '#' : '.');
            if ((cycle) % 40 == 0) {
                sb.append('\n');
            }
        });
        while (state.isRunning()) {
            state.runInstruction();
        }
        return sb.toString();
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2022/aoc2022day10.txt";
    }

    static class State {

        private final List<Instruction> instructions;

        private int pc;

        private int cycle = 1;

        private int x = 1;

        private final CrtConsumer consumer;

        public State(final List<Instruction> instructions, final CrtConsumer consumer) {
            this.instructions = instructions;
            this.consumer = consumer;
        }

        public void cycle() {
            consumer.consume(pc, cycle++, x);
        }

        public void incX(int value) {
            x += value;
        }

        public boolean isRunning() {
            return pc < instructions.size();
        }

        public void runInstruction() {
            instructions.get(pc).execute(this);
        }

        public void incPC() {
            pc++;
        }
    }

    interface Instruction {

        void execute(State state);
    }

    static class Noop implements Instruction {

        @Override
        public void execute(State state) {
            state.cycle();
            state.incPC();
        }
    }

    static class AddX implements Instruction {

        private final int value;

        public AddX(final int value) {
            this.value = value;
        }

        @Override
        public void execute(final State state) {
            state.cycle();
            state.cycle();
            state.incX(value);
            state.incPC();
        }
    }

    @FunctionalInterface
    interface CrtConsumer {
        void consume(int pc, int cycle, int x);
    }
}