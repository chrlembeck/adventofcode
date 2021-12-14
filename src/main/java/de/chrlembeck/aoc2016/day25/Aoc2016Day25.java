package de.chrlembeck.aoc2016.day25;

import de.chrlembeck.aoc2016.day12.Environment;
import de.chrlembeck.aoc2016.day12.Operation;
import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.IntPredicate;

public class Aoc2016Day25 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2016Day25().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        List<Operation> operations = Operation.readOperations(input);
        OutputTester tester = null;
        int inputValue = 0;
        do {
            inputValue++;
            tester = new OutputTester(10000);
            Environment env = new Environment(new ArrayList<>(operations));
            env.setRegister(Environment.Register.A, inputValue);
            env.setOutputChannel(tester);
            env.run();
        } while (tester.limit != 0);
        return inputValue;
    }

    @Override
    public String part2(final Scanner input) {
        return "";
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2016/aoc2016day25.txt";
    }

    static class OutputTester implements IntPredicate {

        boolean zeroExpected;

        int limit;

        public OutputTester(int limit) {
            this.limit = limit;
        }

        @Override
        public boolean test(int value) {
            zeroExpected = !zeroExpected;
            if (--limit == 0) {
                return false;
            }
            return value == (zeroExpected ? 0 : 1);
        }
    }
}