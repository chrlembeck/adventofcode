package de.chrlembeck.aoc2021.day18;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.math.BigInteger;
import java.util.Optional;
import java.util.Scanner;

public class Aoc2021Day18 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2021Day18().run();
    }

    @Override
    public Object part1(final Scanner input) {
        return add(readNumbers(input)).magnitude();
    }

    @Override
    public Object part2(final Scanner input) {
        String[] tokens = input.useDelimiter("\\n").tokens().toArray(String[]::new);
        BigInteger max = BigInteger.ZERO;
        for (int i = 0; i < tokens.length - 1; i++) {
            for (int j = i + 1; j < tokens.length; j++) {
                max = max.max(add(parseLine(new Input(tokens[i])), parseLine(new Input(tokens[j]))).magnitude());
                max = max.max(add(parseLine(new Input(tokens[j])), parseLine(new Input(tokens[i]))).magnitude());
            }
        }
        return max;
    }

    public SnailfishNumber add(SnailfishNumber... numbers) {
        SnailfishNumber sum = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            reduce(sum = new SnailfishPair(sum, numbers[i]));
        }
        return sum;
    }

    public SnailfishNumber[] readNumbers(Scanner input) {
        return input.useDelimiter("\\n").tokens().map(Input::new).map(this::parseLine).toArray(SnailfishNumber[]::new);
    }

    private void reduce(SnailfishNumber number) {
        boolean splitted;
        do {
            boolean exploded;
            do {
                exploded = false;
                 Optional<SnailfishPair> pair = number.findExplodable(0);
                 if (pair.isPresent()) {
                    pair.get().explode();
                    exploded = true;
                }
            } while (exploded);
            splitted = false;
            Optional<SnailfishConstant> constant = number.findSplittable();
            if (constant.isPresent()) {
                constant.get().split();
                splitted = true;
            }
        } while (splitted);
    }

    public SnailfishNumber parseLine(Input input) {
        return input.nextChar() == '[' ? parsePair(input) : parseNumber(input);
    }

    private SnailfishNumber parsePair(final Input input) {
        input.skip('[');
        final SnailfishNumber left = parseLine(input);
        input.skip(',');
        final SnailfishNumber right = parseLine(input);
        input.skip(']');
        return new SnailfishPair(left, right);
    }

    private SnailfishNumber parseNumber(Input input) {
        StringBuilder sb = new StringBuilder();
        while (Character.isDigit(input.nextChar())) {
            sb.append(input.consume());
        }
        return new SnailfishConstant(new BigInteger(sb.toString()));
    }

    public interface SnailfishNumber {

        void setParent(SnailfishPair snailfishPair);

        Optional<SnailfishPair> findExplodable(int level);

        Optional<SnailfishConstant> findSplittable();

        BigInteger magnitude();
    }

    static class SnailfishConstant implements SnailfishNumber {
        private BigInteger value;

        private SnailfishPair parent;

        public SnailfishConstant(BigInteger value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value.toString();
        }

        @Override
        public void setParent(SnailfishPair parent) {
            this.parent = parent;
        }

        public void split() {
            BigInteger left = value.divide(BigInteger.TWO);
            BigInteger right = value.subtract(left);
            parent.replace(this, new SnailfishPair(new SnailfishConstant(left), new SnailfishConstant(right)));
        }

        void add(SnailfishConstant other) {
            this.value = value.add(other.value);
        }

        @Override
        public Optional<SnailfishPair> findExplodable(int level) {
            return Optional.empty();
        }

        @Override
        public Optional<SnailfishConstant> findSplittable() {
            return value.compareTo(BigInteger.TEN) >= 0 ? Optional.of(this) : Optional.empty();
        }

        @Override
        public BigInteger magnitude() {
            return value;
        }
    }

    static class SnailfishPair implements SnailfishNumber {

        private SnailfishNumber left;

        private SnailfishNumber right;

        private SnailfishPair parent;

        public SnailfishPair(SnailfishNumber left, SnailfishNumber right) {
            this.left = left;
            this.right = right;
            left.setParent(this);
            right.setParent(this);
        }

        @Override
        public String toString() {
            return "[%s,%s]".formatted(left, right);
        }

        @Override
        public void setParent(SnailfishPair parent) {
            this.parent = parent;
        }

        public void explode() {
            parent.findLeft(this).ifPresent(n -> n.add((SnailfishConstant) left));
            parent.findRight(this).ifPresent(n -> n.add((SnailfishConstant) right));
            parent.replace(this, new SnailfishConstant(BigInteger.ZERO));
        }

        private void replace(SnailfishNumber old, SnailfishNumber replacement) {
            if (left == old) {
                left.setParent(null);
                left = replacement;
                left.setParent(this);
            } else if (right == old) {
                right.setParent(null);
                right = replacement;
                right.setParent(this);
            }
        }

        private Optional<SnailfishConstant> findLeft(SnailfishPair src) {
            if (left == src) {
                return parent == null ? Optional.empty() : parent.findLeft(this);
            } else {
                SnailfishNumber sn = left;
                while (sn instanceof SnailfishPair pair) {
                    sn = pair.right;
                }
                return Optional.of((SnailfishConstant) sn);
            }
        }

        private Optional<SnailfishConstant> findRight(SnailfishPair src) {
            if (right == src) {
                return parent == null ? Optional.empty() : parent.findRight(this);
            } else {
                SnailfishNumber sn = right;
                while (sn instanceof SnailfishPair pair) {
                    sn = pair.left;
                }
                return Optional.of((SnailfishConstant) sn);
            }
        }

        @Override
        public Optional<SnailfishPair> findExplodable(final int level) {
            return level == 4 ? Optional.of(this) : left.findExplodable(level + 1).or(() -> right.findExplodable(level + 1));
        }

        @Override
        public Optional<SnailfishConstant> findSplittable() {
            return left.findSplittable().or(right::findSplittable);
        }

        @Override
        public BigInteger magnitude() {
            return BigInteger.valueOf(3).multiply(left.magnitude()).add(BigInteger.TWO.multiply(right.magnitude()));
        }
    }

    static class Input {

        int pos = 0;

        String text;

        public Input(String text) {
            this.text = text;
        }

        public char nextChar() {
            return text.charAt(pos);
        }

        public char consume() {
            return text.charAt(pos++);
        }

        public void skip(char ch) {
            char next = consume();
            if (next != ch) {
                throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2021/aoc2021day18.txt";
    }
}