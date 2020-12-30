package de.chrlembeck.aoc2015.day19;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2015Day19 extends AbstractAocBase {

    public static final Pattern REGEX = Pattern.compile("(\\w+) => (\\w+)");

    public static void main(final String[] args) {
        new Aoc2015Day19().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        final Map<String, String> reverseMap = readReverseMap(input);
        final String molecule = reverse(input.nextLine());

        final Set<String> newMolecules = new TreeSet<>();
        for (int i = 0; i < molecule.length(); i++) {
            for (final Entry<String, String> entry : reverseMap.entrySet()) {
                if (molecule.substring(i).startsWith(entry.getValue())) {
                    newMolecules.add(molecule.substring(0, i) + entry.getKey()
                            + molecule.substring(i + entry.getValue().length()));
                }
            }
        }
        return newMolecules.size();
    }

    private Map<String, String> readReverseMap(final Scanner input) {
        final Map<String, String> reverseMap = new TreeMap<>();
        while (input.hasNextLine()) {
            final String line = input.nextLine();
            if (line.length() == 0) {
                break;
            }
            final Matcher matcher = matchRegex(REGEX, line);
            reverseMap.put(reverse(matcher.group(2)), reverse(matcher.group(1)));
        }
        return reverseMap;
    }

    @Override
    public Integer part2(final Scanner input) {
        final Map<String, String> reverseMap = readReverseMap(input);
        String oldMolecule;
        String molecule = reverse(input.nextLine());
        final Pattern regex = Pattern.compile("(" + String.join("|", reverseMap.keySet()) + ")");
        int stepCount = 0;
        do {
            oldMolecule = molecule;
            final Matcher matcher = regex.matcher(oldMolecule);
            final StringBuffer buffer = new StringBuffer();
            while (matcher.find()) {
                matcher.appendReplacement(buffer, reverseMap.get(matcher.group()));
                stepCount++;
            }
            matcher.appendTail(buffer);
            molecule = buffer.toString();
        } while (!molecule.equals(oldMolecule));
        return stepCount;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2015/aoc2015day19.txt";
    }

    public static String reverse(final String input) {
        return new StringBuilder(input).reverse().toString();
    }
}