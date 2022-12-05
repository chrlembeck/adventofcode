package de.chrlembeck.aoc2022.day05;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

public class Aoc2022Day05 extends AbstractAocBase {

    private final static Pattern movePattern = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");

    public static void main(final String[] args) {
        new Aoc2022Day05().run();
    }

    @Override
    public String part1(final Scanner input) {
        return calc(input, (count, from, to) -> {
            for (int i = 0; i < count; i++) {
                to.push(from.pop());
            }
        });
    }

    @Override
    public String part2(final Scanner input) {
        return calc(input, (count, from, to) -> {
            Stack<String> buffer = new Stack<>();
            for (int i = 0; i < count; i++) {
                buffer.push(from.pop());
            }
            while (!buffer.empty()) {
                to.push(buffer.pop());
            }
        });
    }


    public String calc(Scanner input, MoveOp moveOp) {
        Stack<String> buffer = new Stack<>();
        String line;
        while ((line = input.nextLine()).contains("[")) {
            buffer.push(line);
        }
        String[] names = line.trim().split("\\s+");
        System.out.println(Arrays.toString(names));
        Map<Integer, Stack<String>> stacks = new TreeMap<>();
        Map<Integer, Integer> positions = new TreeMap<>();
        for (String name: names) {
            stacks.put(valueOf(name), new Stack<>());
            positions.put(valueOf(name), line.indexOf(name));
        }
        while (!buffer.isEmpty()) {
            line = buffer.pop();
            for (Map.Entry<Integer, Integer> entry: positions.entrySet()) {
                if (line.length() >= entry.getValue() && Character.isLetter(line.charAt(entry.getValue()))) {
                    stacks.get(entry.getKey()).push(line.charAt(entry.getValue())+"");
                }
            }
        }
        input.nextLine();

        tokenStream(input, "\\n", movePattern, this::move).forEach(
                move -> {
                    Stack<String> from = stacks.get(move.from);
                    Stack<String> to = stacks.get(move.to);
                    moveOp.move(move.count, from, to);
                }
        );


        StringBuilder sb = new StringBuilder();
        for (String name: names) {
            Stack<String> stack = stacks.get(Integer.valueOf(name));
            if (!stack.isEmpty()){
                sb.append(stack.peek());
            }

        }
        return sb.toString();
    }

    private Move move(Matcher matcher) {
        return new Move(parseInt(matcher.group(1)), valueOf(matcher.group(2)), valueOf(matcher.group(3)));
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2022/aoc2022day05.txt";
    }

    static record Move(int count, Integer from, Integer to){}

    interface MoveOp {
        void move(int count, Stack<String> from ,Stack<String> to);
    }
}