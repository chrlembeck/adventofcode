package de.chrlembeck.aoc2016.day12;

import de.chrlembeck.aoc2016.day23.Toggle;
import de.chrlembeck.aoc2016.day25.Out;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static de.chrlembeck.aoccommon.AbstractAocBase.matchRegex;

public interface Operation {

    Pattern COPY = Pattern.compile("cpy (-?\\w+) (\\w+)");

    Pattern INC = Pattern.compile("inc (\\w+)");

    Pattern DEC = Pattern.compile("dec (\\w+)");

    Pattern JUMP = Pattern.compile("jnz (\\w+) (-?\\w+)");

    Pattern TGL = Pattern.compile("tgl (\\w+)");

    Pattern OUT = Pattern.compile("out (\\w+)");

    void execute(Environment env);

    Operation toggle();

    static List<Operation> readOperations(final Scanner input) {
        List<Operation> operations = new ArrayList<>();
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.startsWith("cpy")) {
                Matcher matcher = matchRegex(COPY, line);
                if (Character.isLetter(matcher.group(1).charAt(0))) {
                    operations.add(new Copy(Environment.Register.fromName(matcher.group(1)), Environment.Register.fromName(matcher.group(2))));
                } else {
                    operations.add(new Copy(new Const(Integer.parseInt(matcher.group(1))), Environment.Register.fromName(matcher.group(2))));
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
                    operations.add(new JumpNotZero(createExpression(matcher.group(1)), createExpression(matcher.group(2))));
                } else {
                    operations.add(new JumpNotZero(createExpression(matcher.group(1)), createExpression(matcher.group(2))));
                }
            }
            if (line.startsWith("tgl")) {
                Matcher matcher = matchRegex(TGL, line);
                operations.add(new Toggle(Environment.Register.fromName(matcher.group(1))));
            }
            if (line.startsWith("out")) {
                Matcher matcher = matchRegex(OUT, line);
                operations.add(new Out(createExpression(matcher.group(1))));
            }
        }

        return operations;
    }

    private static Expression createExpression(String exp) {
        if (Character.isLetter(exp.charAt(0))) {
            return Environment.Register.fromName(exp);
        } else {
            return new Const(Integer.parseInt(exp));
        }
    }
}