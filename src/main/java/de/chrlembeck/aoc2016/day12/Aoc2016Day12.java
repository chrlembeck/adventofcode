package de.chrlembeck.aoc2016.day12;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2016Day12 extends AbstractAocBase {

    public static final Pattern COPY = Pattern.compile("cpy (\\w+) (\\w+)");

    public static final Pattern INC = Pattern.compile("inc (\\w+)");

    public static final Pattern DEC = Pattern.compile("dec (\\w+)");

    public static final Pattern JUMP = Pattern.compile("jnz (\\w+) (-?\\d+)");

    public static void main(final String[] args) {
        new Aoc2016Day12().run();
    }

    private List<Operation> readOperations(final Scanner input) {
        List<Operation> operations = new ArrayList<>();
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.startsWith("cpy")) {
                Matcher matcher = matchRegex(COPY, line);
                if (Character.isLetter(matcher.group(1).charAt(0))) {
                    operations.add(new Copy(Environment.Register.fromName(matcher.group(1)), Environment.Register.fromName(matcher.group(2))));
                } else {
                    operations.add(new CopyConst(Integer.parseInt(matcher.group(1)), Environment.Register.fromName(matcher.group(2))));
                }
            }
            if (line.startsWith("inc")) {
                Matcher matcher = matchRegex(INC, line);
                operations.add(new Inc(Environment.Register.fromName(matcher.group(1))));
            }
            if (line.startsWith("dec")) {
                Matcher matcher = matchRegex(DEC, line);
                operations.add(new Dec(Environment.Register.fromName(matcher.group(1))));
            }
            if (line.startsWith("jnz")) {
                Matcher matcher = matchRegex(JUMP, line);
                if (Character.isLetter(matcher.group(1).charAt(0))) {
                    operations.add(new JumpNotZero(Environment.Register.fromName(matcher.group(1)), Integer.parseInt(matcher.group(2))));
                } else {
                    operations.add(new Jump(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))));
                }
            }
        }

        return operations;
    }

    @Override
    public Integer part1(final Scanner input) {
        Environment env = new Environment(readOperations(input));
        env.run();
        return env.readRegister(Environment.Register.A);
    }

    @Override
    public Integer part2(final Scanner input) {
        Environment env = new Environment(readOperations(input));
        env.setRegister(Environment.Register.C, 1);
        env.run();
        return env.readRegister(Environment.Register.A);
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2016/aoc2016day12.txt";
    }
}