package de.chrlembeck.aoc2019.day14;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Pattern;

public class Aoc2019Day14 extends AbstractAocBase {

    private static final Pattern REGEX = Pattern.compile("((\\d+ \\w+, )*(\\d+ \\w+)) => (\\d+) (\\w+)");

    public static void main(final String[] args) {
        new Aoc2019Day14().run();
    }

    @Override
    public Object part1(final Scanner input) {
        final Map<String, Formula> formulas = readFormulas(input);
        return calcNeededORE(formulas, BigInteger.ONE);
    }

    @Override
    public Object part2(final Scanner input) {
        final Map<String, Formula> formulas = readFormulas(input);

        final BigInteger onetrillion = new BigInteger("1000000000000");

        BigInteger upperLimit = BigInteger.ONE;
        BigInteger lowerLimit = null;
        while (calcNeededORE(formulas, upperLimit).compareTo(onetrillion) < 0) {
            lowerLimit = upperLimit;
            upperLimit = upperLimit.shiftLeft(1);
        }

        while (upperLimit.subtract(lowerLimit).compareTo(BigInteger.ONE) > 0) { // binary search
            final BigInteger middle = upperLimit.add(lowerLimit).divide(BigInteger.TWO);
            if (calcNeededORE(formulas, middle).compareTo(onetrillion) < 0) {
                lowerLimit = middle;
            } else {
                upperLimit = middle;
            }
        }
        return lowerLimit;
    }

    private BigInteger calcNeededORE(final Map<String, Formula> formulas, final BigInteger fuelQuantity) {
        final List<FormulaUnit> requirements = new LinkedList<>();
        requirements.add(new FormulaUnit(fuelQuantity, "FUEL"));
        BigInteger oreCounter = BigInteger.ZERO;
        final Map<String, BigInteger> restMap = new TreeMap<>();
        while (!requirements.isEmpty()) {
            final FormulaUnit unit = requirements.remove(0);
            BigInteger requiredQuantity = unit.getQuantity();

            BigInteger rest = restMap.get(unit.getName());
            if (rest != null) {
                final BigInteger tmp = rest.min(requiredQuantity);
                rest = rest.subtract(tmp);
                requiredQuantity = requiredQuantity.subtract(tmp);
                if (rest.compareTo(BigInteger.ZERO) == 0) {
                    restMap.remove(unit.getName());
                } else {
                    restMap.put(unit.getName(), rest);
                }
            }

            if ("ORE".equals(unit.getName())) {
                oreCounter = oreCounter.add(requiredQuantity);
                continue;
            }

            if (requiredQuantity.signum() > 0) {
                final Formula formula = formulas.get(unit.getName());
                final BigInteger factor = (requiredQuantity.add(formula.getResult().getQuantity()).subtract(BigInteger.ONE)).divide(formula.getResult().getQuantity());
                rest = factor.multiply(formula.getResult().getQuantity()).subtract(requiredQuantity);
                if (rest.compareTo(BigInteger.ZERO) > 0) {
                    restMap.put(formula.getResult().getName(), rest);
                }
                formula.getInput().stream().map(f -> new FormulaUnit(factor.multiply(f.getQuantity()), f.getName())).forEach(requirements::add);
            }
        }
        return oreCounter;
    }

    private Map<String, Formula> readFormulas(final Scanner input) {
        final Map<String, Formula> formulas = new TreeMap<>();
        input.findAll(REGEX)
                .forEach(mr -> formulas.put(mr.group(5), new Formula(new BigInteger(mr.group(4)), mr.group(5), mr.group(1))));
        return formulas;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2019/aoc2019day14.txt";
    }
}