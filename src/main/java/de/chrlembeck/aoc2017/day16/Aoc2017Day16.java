package de.chrlembeck.aoc2017.day16;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2017Day16 extends AbstractAocBase {

    public static final Pattern REGEX = Pattern.compile("([sxp])(\\w+)(/(\\w+))?");

    public static void main(final String[] args) {
        new Aoc2017Day16().run();
    }

    @Override
    public String part1(final Scanner input) {
        final List<Command> commands = parseCommands(input);
        final TransformInfo trans = new TransformInfo();
        trans.string = new char[16];
        for (int i = 0; i < 16; i++) {
            trans.string[i] = (char)('a' + i);
        }
        trans.order = trans.string.clone();
        trans.spin = 0;
        for (final Command command: commands) {
            command.execute(trans);
        }
        return eval(trans.string, trans.order, trans.spin);
    }

    private List<Command> parseCommands(final Scanner input) {
        input.useDelimiter(",");
        final List<Command> commands = new ArrayList<>();
        while(input.hasNext()) {
            final String command = input.next();
            final Matcher matcher = REGEX.matcher(command);
            if (matcher.matches()) {
                switch (matcher.group(1)) {
                case "s":
                    commands.add(new SwitchCommand(Integer.parseInt(matcher.group(2))));
                    break;
                case "x":
                    final int intA = Integer.parseInt(matcher.group(2));
                    final int intB = Integer.parseInt(matcher.group(4));
                    commands.add(new XChangeCommand(intA, intB));
                    break;
                case "p":
                    final char charA = matcher.group(2).charAt(0);
                    final char charB = matcher.group(4).charAt(0);
                    commands.add(new PartnerCommand(charA, charB));
                    break;
                default:
                    throw new IllegalArgumentException(command);
                }
            } else {
                throw new IllegalArgumentException(command);
            }
        }
        return commands;
    }

    public String eval(final char[] string, final char[] order, final int spin) {
        final char[] result = new char[16];
        for (int i = 0; i < 16; i++) {
            final char character = string[(i + spin) % 16];
            int idx = 0;
            while (order[idx] != character) {
                idx++;
            }
            result[i] = (char)('a' + idx);
        }

        final StringBuilder builder = new StringBuilder();
        for (final char chr: result) {
            builder.append(chr);
        }

        return builder.toString();
    }

    @Override
    public String part2(final Scanner input) {
        final List<Command> commands = parseCommands(input);

        final TransformInfo trans = new TransformInfo();
        trans.string = new char[16];
        for (int i = 0;i < 16; i++) {
            trans.string[i] = (char)('a' + i);
        }
        trans.order = trans.string.clone();
        trans.spin = 0;
        int iter = 1_000_000_000;
        for (int i = 0; i < iter; i++) {
            for (final Command command: commands) {
                command.execute(trans);
            }
            if ("abcdefghijklmnop".equals(eval(trans.string, trans.order, trans.spin))) {
                iter = i+1+iter % (i+1);
            }
        }
        return eval(trans.string, trans.order, trans.spin);
    }

    public char[] apply(final int[] trans, final char... string) {
        final char[] newString = new char[16];
        for (int i = 0; i < 16; i++) {
            newString[trans[i]] = string[i];
        }
        return newString;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2017/aoc2017day16.txt";
    }

    static class TransformInfo {

        private int spin;

        private char[] order;

        private char[] string;
    }

    interface Command{
        void execute(final TransformInfo info);
    }

    class SwitchCommand implements Command {

        private final int offset;

        public SwitchCommand(final int offset) {
            this.offset = offset;
        }

        @Override
        public void execute(final TransformInfo info) {
            info.spin = (16 + info.spin - offset) % 16;
        }
    }

    class XChangeCommand implements Command {

        private final int intA;

        private final int intB;

        public XChangeCommand(final int intA, final int intB) {
            this.intA = intA;
            this.intB = intB;
        }

        @Override
        public void execute(final TransformInfo info) {
            final char tmp = info.string[(intA + info.spin) % 16];
            info.string[(intA + info.spin) % 16] = info.string[(intB + info.spin) % 16];
            info.string[(intB + info.spin) % 16] = tmp;
        }
    }

    class PartnerCommand implements Command {

        private final char charB;

        private final char charA;

        PartnerCommand(final char charA, final char charB) {
            this.charA =charA;
            this.charB =charB;
        }

        @Override
        public void execute(final TransformInfo info) {
            final char tmp = info.order[charA-'a'];
            info.order[charA - 'a'] = info.order[charB - 'a'];
            info.order[charB - 'a'] = tmp;
        }
    }
}