package de.chrlembeck.aoc2022.day13;

import de.chrlembeck.aoccommon.AbstractAocBase;
import de.chrlembeck.util.lang.StringUtils;

import java.util.*;

public class Aoc2022Day13 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2022Day13().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        int result = 0;
        int idx = 0;
        while (input.hasNextLine()) {
            idx++;
            PacketData data1 = parsePacketData(new Input(input.nextLine()));
            PacketData data2 = parsePacketData(new Input(input.nextLine()));
            if (data1.compareTo(data2) < 0) {
                result += idx;
            }
            if (input.hasNextLine()) {
                input.nextLine();
            }
        }
        return result;
    }

    @Override
    public Integer part2(final Scanner input) {
        List<PacketData> packets = new ArrayList<>();
        input.useDelimiter("\\n").tokens().filter(s -> !StringUtils.isEmpty(s)).map(Input::new).map(this::parsePacketData).forEach(packets::add);
        PacketData d1 = parsePacketData(new Input("[[2]]"));
        PacketData d2 = parsePacketData(new Input("[[6]]"));
        packets.add(d1);
        packets.add(d2);
        packets.sort(Comparator.naturalOrder());
        return (packets.indexOf(d1) + 1) * (packets.indexOf(d2) + 1);
    }

    private PacketData parsePacketData(Input input) {
        if (input.peek() == '[') {
            return parseList(input);
        } else {
            return parseInteger(input);
        }
    }

    private PacketData parseList(Input input) {
        input.consume();
        if (input.peek() == ']') {
            input.consume();
            return new ListPacketData();
        }
        List<PacketData> values = new ArrayList<>();
        values.add(parsePacketData(input));
        while (input.peek() == ',') {
            input.consume();
            values.add(parsePacketData(input));
        }
        input.consume();
        return new ListPacketData(values);
    }

    private IntegerPacketData parseInteger(Input input) {
        int value = 0;
        while (Character.isDigit(input.peek())) {
            value = value * 10 + (input.consume() - '0');
        }
        return new IntegerPacketData(value);
    }

    public sealed interface PacketData extends Comparable<PacketData> permits IntegerPacketData, ListPacketData {
    }

    record IntegerPacketData(int value) implements PacketData {

        @Override
        public int compareTo(PacketData other) {
            if (other instanceof IntegerPacketData ipd) {
                return Integer.compare(value, ipd.value);
            } else {
                return new ListPacketData(this).compareTo(other);
            }
        }
    }

    final static class ListPacketData implements PacketData {

        private final List<PacketData> values = new ArrayList<>();

        public ListPacketData(PacketData... data) {
            Collections.addAll(values, data);
        }

        public ListPacketData(List<PacketData> data) {
            this.values.addAll(data);
        }

        @Override
        public int compareTo(PacketData other) {
            if (other instanceof IntegerPacketData ipd) {
                return compareTo(new ListPacketData(ipd));
            }
            ListPacketData lpd = (ListPacketData) other;
            for (int idx = 0; idx < Math.min(values.size(), lpd.values.size()); idx++) {
                int cmp = values.get(idx).compareTo(lpd.values.get(idx));
                if (cmp != 0) {
                    return cmp;
                }
            }
            return Integer.compare(values.size(), lpd.values.size());
        }
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2022/aoc2022day13.txt";
    }

    static class Input {

        private int pos;

        private final String data;

        public Input(String data) {
            this.data = data;
        }

        private char peek() {
            return data.charAt(pos);
        }

        public char consume() {
            return data.charAt(pos++);
        }
    }
}