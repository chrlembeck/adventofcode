package de.chrlembeck.aoc2017.day24;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 1057 to low
public class Aoc2017Day24 extends AbstractAocBase {

    private final static Pattern REGEX = Pattern.compile("(\\d+)/(\\d+)");

    public static void main(final String[] args) {
        new Aoc2017Day24().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        final List<Component> components = readComponents(input);
        return calcMax(0, components);
    }

    private List<Component> readComponents(final Scanner input) {
        final List<Component> components = new ArrayList<>();
        while (input.hasNextLine()) {
            final Matcher matcher = matchRegex(REGEX, input.nextLine());
            components.add(new Component(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))));
        }
        return components;
    }

    private int calcMax(final int currentPort, final List<Component> components) {
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
                final Bridge subBridge = calcLongest(rotated.getPortB(), components);
                subBridge.incStrength(rotated.getStrength());
                subBridge.incLength();
                max = max(max, subBridge);
                components.add(i, component);
            }
        }
        return max;
    }

    private Bridge max(final Bridge bridgeA, final Bridge bridgeB) {
        if (bridgeA.getLength() > bridgeB.getLength()) {
            return bridgeA;
        }
        if (bridgeA.getLength() < bridgeB.getLength()) {
            return bridgeB;
        }
        if (bridgeA.getStrength() > bridgeB.getStrength()) {
            return bridgeA;
        }
        return bridgeB;
    }

    @Override
    public Integer part2(final Scanner input) {
        final List<Component> components = readComponents(input);
        return calcLongest(0, components).getStrength();
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2017/aoc2017day24.txt";
    }
}