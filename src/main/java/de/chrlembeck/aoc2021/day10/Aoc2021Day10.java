package de.chrlembeck.aoc2021.day10;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.*;

public class Aoc2021Day10 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2021Day10().run();
    }

    @Override
    public Number part1(final Scanner input) {
        return calc(input, 1);
    }

    @Override
    public Object part2(final Scanner input) {
        return calc(input, 2);
    }

    private int syntaxCheckerScore(char ch) {
        return switch (ch) {
            case ')' -> 3;
            case ']' -> 57;
            case '}' -> 1197;
            case '>' -> 25137;
            default -> throw new IllegalArgumentException();
        };
    }

    private int autocompletionScore(char ch) {
        return switch (ch) {
            case ')' -> 1;
            case ']' -> 2;
            case '}' -> 3;
            case '>' -> 4;
            default -> throw new IllegalArgumentException();
        };
    }

    private Number calc(final Scanner input, final int part) {
        List<Long> scores = new ArrayList<>();
        int syntaxChackScore = 0;
        autocompletion:
        while (input.hasNext()) {
            final String line = input.nextLine();
            Stack<Character> stack = new Stack<>();
            for (char ch : line.toCharArray()) {
                switch (ch) {
                    case '(':
                        stack.push(')');
                        break;
                    case '[':
                        stack.push(']');
                        break;
                    case '{':
                        stack.push('}');
                        break;
                    case '<':
                        stack.push('>');
                        break;
                    case ')', ']', '}', '>':
                        if (stack.pop() != ch) {
                            syntaxChackScore += syntaxCheckerScore(ch);
                            continue autocompletion;
                        }
                        break;
                    default:
                        throw new RuntimeException("Illegal character " + ch);
                }

            }
            if (!stack.empty()) {
                long autocomletionScore = 0;
                while (!stack.empty()) {
                    autocomletionScore = autocomletionScore * 5 + autocompletionScore(stack.pop());
                }
                scores.add(autocomletionScore);
            }

        }
        if (part == 1) {
            return syntaxChackScore;
        } else {
            Collections.sort(scores);
            return scores.get(scores.size() / 2);
        }
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2021/aoc2021day10.txt";
    }
}