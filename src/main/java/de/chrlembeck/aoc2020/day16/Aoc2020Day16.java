package de.chrlembeck.aoc2020.day16;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Aoc2020Day16 extends AbstractAocBase {

    public static final Pattern REGEX = Pattern.compile("([\\w ]+): (\\d+)-(\\d+) or (\\d+)-(\\d+)");

    public static void main(final String[] args) {
        new Aoc2020Day16().run();
    }

    @Override
    public Object part1(final Scanner input) {
        final List<Condition> conditions = readConditions(input);
        for (int i = 0; i < 4; i++) {
            input.nextLine();
        }
        int errorRate = 0;
        while (input.hasNextLine()) {
            errorRate += new Ticket(input.nextLine()).errorRate(conditions);
        }
        return errorRate;
    }

    @Override
    public Object part2(final Scanner input) {
        final List<Condition> conditions = readConditions(input);
        input.nextLine();
        final Ticket myTicket = new Ticket(input.nextLine());
        for (int i = 0; i < 2; i++) {
            input.nextLine();
        }
        final List<Ticket> nearbyTickets = input.tokens().map(Ticket::new).filter(Ticket.matchesInAnyOrder(conditions)).collect(Collectors.toList());

        final List<Integer>[] possiblePositions = new List[conditions.size()];
        for (int conditionIndex = 0; conditionIndex < possiblePositions.length; conditionIndex++) {
            final List<Integer> positions = new ArrayList<>();
            possiblePositions[conditionIndex] = positions;
            for (int positionIdx = 0; positionIdx < possiblePositions.length; positionIdx++) {
                boolean fits = true;
                for (final Ticket ticket : nearbyTickets) {
                    if (!ticket.fitsAt(conditions.get(conditionIndex), positionIdx)) {
                        fits = false;
                    }
                }
                if (fits) {
                    positions.add(positionIdx);
                }
            }
        }
        reduceObvious(possiblePositions);
        BigInteger result = BigInteger.ONE;
        for (int i = 0; i < conditions.size(); i++) {
            final Condition condition = conditions.get(i);
            if (condition.startsWith("departure")) {
                result = result.multiply(BigInteger.valueOf(myTicket.getNumber(possiblePositions[i].get(0))));
            }
        }

        return result;
    }

    private void reduceObvious(final List<Integer>... possiblePositions) {
        boolean changed;
        do {
            changed = false;
            final List<Integer> singlePositions = new ArrayList<>();
            for (final List<Integer> conditionPosition: possiblePositions) {
                if (conditionPosition.size() == 1) {
                    singlePositions.add(conditionPosition.get(0));
                }
            }
            for (final Integer fixedPosition: singlePositions) {
                for (final List<Integer> conditionPosition: possiblePositions) {
                    if (conditionPosition.size() > 1) {
                        changed |= conditionPosition.remove(fixedPosition);
                    }
                }
            }
        } while (changed);
    }

    private List<Condition> readConditions(final Scanner input) {
        final List<Condition> conditions = new ArrayList<>();
        for (String line = input.nextLine(); line.length() > 0; line = input.nextLine()) {
            final Matcher matcher = REGEX.matcher(line);
            matcher.matches();
            conditions.add(new Condition(matcher.group(1), Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)),
                    Integer.parseInt(matcher.group(4)),
                    Integer.parseInt(matcher.group(5))));

        }
        return conditions;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2020/aoc2020day16.txt";
    }
}