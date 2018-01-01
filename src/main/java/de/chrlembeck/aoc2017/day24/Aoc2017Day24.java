package de.chrlembeck.aoc2017.day24;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.chrlembeck.aoccommon.AbstractAocBase;

// 1057 to low
public class Aoc2017Day24 extends AbstractAocBase {

    Pattern regex = Pattern.compile("(\\d+)/(\\d+)");

    public static void main(final String[] args) {
        new Aoc2017Day24().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        List<Component> components = readComponents(input);
        return calcMax(0, components);
    }

    private List<Component> readComponents(final Scanner input) {
        List<Component> components = new ArrayList<>();
        while (input.hasNextLine()) {
            Matcher matcher = matchRegex(regex, input.nextLine());
            components.add(new Component(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))));
        }
        return components;
    }

    private int calcMax(final int currentPort,  final List<Component> components) {
        int max = 0;
        for (int i = components.size() - 1; i >= 0; i--) {
            final Component component = components.get(i);
            final Component rotated = component.rotateToFit(currentPort);
            if (rotated != null) {
                components.remove(i);
                max = Math.max(max, rotated.getStrength() + calcMax(rotated.getPortB(), components));
                components.add(i, component);
            }
        }
        return max;
    }

    private Bridge calcLongest(final int currentPort, final List<Component> components) {
        Bridge max = new Bridge(0, 0);
        for (int i = components.size() - 1; i >= 0; i--) {
            final Component component = components.get(i);
            final Component rotated = component.rotateToFit(currentPort);
            if (rotated != null) {
                components.remove(i);
                Bridge subBridge = calcLongest(rotated.getPortB(), components);
                subBridge.strength += rotated.getStrength();
                subBridge.length++;
                max = max(max, subBridge);
                components.add(i, component);
            }
        }
        return max;
    }

    private Bridge max(Bridge a, Bridge b) {
        if (a.length > b.length) {
            return a;
        }
        if (a.length < b.length) {
            return b;
        }
        if (a.strength > b.strength) {
            return a;
        }
        return b;
    }

    @Override
    public Integer part2(final Scanner input) {
        List<Component> components = readComponents(input);
        return calcLongest(0, components).getStrength();
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2017/aoc2017day24.txt";
    }
}