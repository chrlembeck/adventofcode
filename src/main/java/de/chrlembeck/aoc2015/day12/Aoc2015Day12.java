package de.chrlembeck.aoc2015.day12;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.*;

public class Aoc2015Day12 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2015Day12().run();
    }

    @Override
    public Long part1(final Scanner input) {
        String line = input.nextLine();
        Position position = new Position();
        JsonElement element = parseJsonElement(line, position);
        return element.sumNumbers(false);
    }

    @Override
    public Long part2(final Scanner input) {
        String line = input.nextLine();
        Position position = new Position();
        JsonElement element = parseJsonElement(line, position);
        return element.sumNumbers(true);
    }

    private JsonElement parseJsonElement(String text, Position position) {
        if (text.charAt(position.getPos()) == '\"') {
            return parseString(text, position);
        } else if (text.charAt(position.getPos()) == '[') {
            return parseArray(text, position);
        }
        if (text.charAt(position.getPos()) == '{') {
            return parseObject(text, position);
        } else {
            return parseConstant(text, position);
        }
    }

    private JsonElement parseObject(String text, Position position) {
        Map<String, JsonElement> attributes = new TreeMap<>();
        position.inc(1); // '{'
        if (text.charAt(position.getPos()) != '}') {
            JsonString name = parseString(text, position);
            position.inc(1); // ':'
            JsonElement value = parseJsonElement(text, position);
            attributes.put(name.getValue(), value);
            while (position.getPos() < text.length() && text.charAt(position.getPos()) == ',') {
                position.inc(1); // ','
                name = parseString(text, position);
                position.inc(1); // ':'
                value = parseJsonElement(text, position);
                attributes.put(name.getValue(), value);
            }
        }
        position.inc(1); // '}'
        return new JsonObject(attributes);
    }

    private JsonElement parseArray(String text, Position position) {
        List<JsonElement> elements = new ArrayList<>();
        position.inc(1); // '['
        elements.add(parseJsonElement(text, position));
        while (position.getPos() < text.length() && text.charAt(position.getPos()) == ',') {
            position.inc(1); // ','
            elements.add(parseJsonElement(text, position));
        }
        position.inc(1); // ']'

        return new JsonArray(elements);
    }

    private JsonString parseString(String text, Position position) {
        position.inc(1);
        int start = position.getPos();
        while (position.getPos() < text.length() && text.charAt(position.getPos()) != '\"') {
            position.inc(1);
        }
        position.inc(1);
        return new JsonString(text.substring(start, position.getPos()-1));
    }

    private JsonElement parseConstant(String text, Position position) {
        boolean neg = false;
        if (text.charAt(position.getPos()) == '-') {
            neg = true;
            position.inc(1);
        }
        int value = 0;
        while (position.getPos() < text.length() && Character.isDigit(text.charAt(position.getPos()))) {
            value = value*10 + (text.charAt(position.getPos()) - '0');
            position.inc(1);
        }
        return new JsonConst(neg ? -value : value);
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2015/aoc2015day12.txt";
    }
}