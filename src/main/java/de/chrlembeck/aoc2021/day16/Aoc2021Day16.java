package de.chrlembeck.aoc2021.day16;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Aoc2021Day16 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2021Day16().run();
    }

    @Override
    public Object part1(final Scanner scanner) {
        Input input = new Input(convertToBits(scanner.nextLine()));
        Packet tree = parseInput(input);
        return tree.versionSum();
    }

    private Packet parseInput(Input input) {
        int version = input.parseVersion();
        int type = input.parseType();
        if (type == 4) {
            return parseLiteral(version, input);
        } else {
            return parseOperator(version, type, input);
        }

        //throw new IllegalArgumentException("type = " + type);
    }

    private Packet parseOperator(int version, int type, Input input) {
        if (input.read(1).equals("0")) {
            // length type id 0
            int lengthInBits = Integer.parseInt(input.read(15), 2);
            int pos = input.getPos();
            List<Packet> subPackets = new LinkedList<>();
            while (input.getPos() < pos + lengthInBits) {
                subPackets.add(parseInput(input));
            }
            return new Operator(version, type, subPackets.toArray(Packet[]::new));
        } else {
            // length type id 1
            int numberOfSubPackets = Integer.parseInt(input.read(11), 2);
            Packet[] subPackets = new Packet[numberOfSubPackets];
            for (int i = 0; i < numberOfSubPackets; i++) {
                subPackets[i] = parseInput(input);
            }
            return new Operator(version, type, subPackets);
        }
    }

    private Literal parseLiteral(int version, Input input) {
        StringBuilder value = new StringBuilder();
        boolean last = false;
        do {
            last = input.read(1).equals("0");
            value.append(input.read(4));
        } while (!last);
        return new Literal(version, new BigInteger(value.toString(), 2));
    }


    private String convertToBits(String input) {
        StringBuilder sb = new StringBuilder();
        for (char ch: input.toCharArray()) {
            String num = Integer.toBinaryString(Integer.parseInt(ch + "", 16));
            while (num.length() < 4) {
                num = '0' + num;
            }
            sb.append(num);
        }
        return sb.toString();
    }

    @Override
    public Object part2(final Scanner scanner) {
        Input input = new Input(convertToBits(scanner.nextLine()));
        Packet tree = parseInput(input);
        return tree.operate();
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2021/aoc2021day16.txt";
    }

    static class Operator implements Packet {
        private final int version;
        private final int type;
        private final Packet[] subPackets;

        public Operator(int version, int type, Packet[] subPackets) {
            this.version = version;
            this.type = type;
            this.subPackets = subPackets;
        }

        @Override
        public int versionSum() {
            int sum = version;
            for (Packet sub : subPackets) {
                sum += sub.versionSum();
            }
            return sum;
        }

        @Override
        public BigInteger operate() {
            switch (type) {
                case 0:
                    return Arrays.stream(subPackets).map(Packet::operate).reduce(BigInteger::add).orElseThrow();
                case 1:
                    return Arrays.stream(subPackets).map(Packet::operate).reduce(BigInteger::multiply).orElseThrow();
                case 2:
                    return Arrays.stream(subPackets).map(Packet::operate).reduce(BigInteger::min).orElseThrow();
                case 3:
                    return Arrays.stream(subPackets).map(Packet::operate).reduce(BigInteger::max).orElseThrow();
                case 5:
                    return subPackets[0].operate().compareTo(subPackets[1].operate())>0?BigInteger.ONE:BigInteger.ZERO;
                case 6:
                    return subPackets[0].operate().compareTo(subPackets[1].operate())<0?BigInteger.ONE:BigInteger.ZERO;
                case 7:
                    return subPackets[0].operate().compareTo(subPackets[1].operate())==0?BigInteger.ONE:BigInteger.ZERO;
                default:
                    throw new IllegalArgumentException("unknown type " + type);
            }
        }
    }

    static class Literal implements Packet {

        private final int version;

        private final BigInteger value;

        public Literal(int version, BigInteger value) {
            this.version = version;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Lit(%d, %d)".formatted(version, value);
        }

        @Override
        public int versionSum() {
            return version;
        }

        @Override
        public BigInteger operate() {
            return value;
        }
    }

    static class Input {
        int pos = 0;
        String bits;

        public Input(String bits) {
            this.bits = bits;
        }

        public int parseVersion() {
            int version = Integer.parseInt(bits.substring(pos, pos + 3), 2);
            pos += 3;
            return version;
        }

        public int parseType() {
            int version = Integer.parseInt(bits.substring(pos, pos + 3), 2);
            pos += 3;
            return version;
        }

        public String read(int anz) {
            return bits.substring(pos, pos += anz);
        }

        @Override
        public String toString() {
            return bits.substring(pos);
        }

        public int getPos() {
            return pos;
        }
    }

    public interface Packet {
        int versionSum();

        BigInteger operate();
    }
}