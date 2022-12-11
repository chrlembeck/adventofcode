package de.chrlembeck.aoc2022.day11;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;

import static de.chrlembeck.aoc2022.day11.Aoc2022Day11.MonkeyOperation.*;
import static java.lang.Integer.parseInt;

public class Aoc2022Day11 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2022Day11().run();
    }

    @Override
    public Long part1(final Scanner input) {
        Monkey[] monkeys = parseMonkeys(input);
        return calc(monkeys, 20, w -> w / 3);
    }

    @Override
    public Long part2(final Scanner input) {
        Monkey[] monkeys = parseMonkeys(input);
        long modulus = Arrays.stream(monkeys).mapToLong(Monkey::getCheckValue).reduce(1, (a,b) -> a*b);
        return calc(monkeys, 10000, w -> w % modulus);
    }

    private long calc(final Monkey[] monkeys, int rounds, Function<Long, Long> worryFunction) {
        for (int i = 0; i < rounds; i++) {
            for (Monkey monkey : monkeys) {
                monkey.inspect(worryFunction);
            }
        }
        Arrays.sort(monkeys, Comparator.comparing(Monkey::getInspectCount).reversed());
        return monkeys[0].getInspectCount() * monkeys[1].getInspectCount();
    }

    private Monkey[] parseMonkeys(Scanner input) {
        Map<Integer, Monkey> monkeyMap = new TreeMap<>();
        while (input.hasNextLine()) {
            parseMonkey(input, monkeyMap);
            if (input.hasNextLine()) {
                input.nextLine();
            }
        }
        return monkeyMap.values().toArray(Monkey[]::new);
    }

    private void parseMonkey(Scanner input, Map<Integer, Monkey> monkeys) {
        Monkey monkey = monkeys.computeIfAbsent(parseInt(matchRegex("Monkey (\\d+):", input.nextLine()).group(1)), Monkey::new);
        String items = matchRegex("  Starting items: (\\d+(, \\d+)*)", input.nextLine()).group(1);
        Arrays.stream(items.split(", ")).map(Long::valueOf).forEach(monkey::addItem);
        Matcher opMatcher = matchRegex("  Operation: new = old ([\\+\\*]) (\\d+|old)", input.nextLine());
        monkey.setOperation(opMatcher.group(1).equals("+") ? ADD : (opMatcher.group(2).equals("old") ? SQR : MULTIPLY));
        if (!(monkey.operation == SQR)) {
            monkey.setOperationValue(parseInt(opMatcher.group(2)));
        }
        monkey.setCheckValue(parseInt(matchRegex("  Test: divisible by (\\d+)", input.nextLine()).group(1)));
        monkey.setTrueMonkey(monkeys.computeIfAbsent(parseInt(matchRegex("    If true: throw to monkey (\\d+)", input.nextLine()).group(1)), Monkey::new));
        monkey.setFalseMonkey(monkeys.computeIfAbsent(parseInt(matchRegex("    If false: throw to monkey (\\d+)", input.nextLine()).group(1)), Monkey::new));
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2022/aoc2022day11.txt";
    }

    static class Monkey {

        private final Queue<Long> items = new LinkedList<>();

        private long inspectCount;

        private MonkeyOperation operation;

        private Long operationValue;

        private long checkValue;

        private Monkey trueMonkey;

        private Monkey falseMonkey;

        public Monkey(int id) {
        }

        public void setOperation(MonkeyOperation operation) {
            this.operation = operation;
        }

        public void setOperationValue(long operationValue) {
            this.operationValue = operationValue;
        }

        public void setCheckValue(int checkValue) {
            this.checkValue = checkValue;
        }

        public void setTrueMonkey(Monkey trueMonkey) {
            this.trueMonkey = trueMonkey;
        }

        public long getCheckValue() {
            return checkValue;
        }

        public void setFalseMonkey(Monkey falseMonkey) {
            this.falseMonkey = falseMonkey;
        }

        public void addItem(Long item) {
            items.offer(item);
        }

        public long getInspectCount() {
            return inspectCount;
        }

        public void inspect(Function<Long, Long> worryFunction) {
            while (!items.isEmpty()) {
                inspectCount++;
                long item = items.remove();
                item = worryFunction.apply(operation.operate(item, operationValue));
                ((item % checkValue) == 0 ? trueMonkey : falseMonkey).addItem(item);
            }
        }
    }

    enum MonkeyOperation {
        ADD, MULTIPLY, SQR;

        public long operate(long value, Long operationValue) {
            return switch (this) {
                case ADD -> value + operationValue;
                case MULTIPLY -> value * operationValue;
                case SQR -> value * value;
            };
        }
    }
}