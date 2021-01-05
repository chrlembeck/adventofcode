package de.chrlembeck.aoc2020.day18;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.function.Function;

public class Aoc2020Day18 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2020Day18().run();
    }

    @Override
    public Object part1(final Scanner input) {
        return input.useDelimiter("\n")
                .tokens()
                .map(s -> s.replaceAll(" ", ""))
                .map(IndexedReader::new)
                .map(this::evaluateBinOp)
                .reduce(BigInteger.ZERO, BigInteger::add);
    }

    @Override
    public Object part2(final Scanner input) {
        return input.useDelimiter("\n")
                .tokens()
                .map(s -> s.replaceAll(" ", ""))
                .map(IndexedReader::new)
                .map(this::evaluateProduct)
                .reduce(BigInteger.ZERO, BigInteger::add);
    }

    private BigInteger evaluateBinOp(final IndexedReader input) {
        BigInteger result = evaluateNumber(input, this::evaluateBinOp);
        while (!input.isEOL() && input.next() != ')') {
            final char operator = input.next();
            input.inc();
            final BigInteger right = evaluateNumber(input, this::evaluateBinOp);
            result = switch (operator) {
                case '+' -> result.add(right);
                case '*' -> result.multiply(right);
                default -> throw new IllegalArgumentException("unexpected operator " + operator);
            };
        }
        return result;
    }

    private BigInteger evaluateProduct(final IndexedReader input) {
        BigInteger result = evaluateSum(input);
        while (!input.isEOL() && input.next() == '*') {
            input.inc();
            result = result.multiply(evaluateSum(input));
        }
        return result;
    }

    private BigInteger evaluateSum(final IndexedReader input) {
        BigInteger result = evaluateNumber(input, this::evaluateProduct);
        while (!input.isEOL() && input.next() == '+') {
            input.inc();
            result = result.add(evaluateNumber(input, this::evaluateProduct));
        }
        return result;
    }

    private BigInteger evaluateNumber(final IndexedReader input, final Function<IndexedReader, BigInteger> termFunction) {
        if (input.next() == '(') {
            input.inc();
            final BigInteger result = termFunction.apply(input);
            input.inc();
            return result;
        } else {
            final StringBuilder number = new StringBuilder();
            while (!input.isEOL() && Character.isDigit(input.next())) {
                number.append(input.next());
                input.inc();
            }
            return new BigInteger(number.toString());
        }
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2020/aoc2020day18.txt";
    }
}