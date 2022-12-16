package de.chrlembeck.aoc2021.day24;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface Expression {

    Optional<Range> getRange();

    static Expression createMod(Expression left, Expression right) {
        if (left instanceof IntValue i && i.getValue().compareTo(BigInteger.ZERO) == 0) {
            return i;
        }
        if (left instanceof IntValue l && right instanceof IntValue r) {
            return new IntValue(l.getValue().mod(r.getValue()));
        }
        return createAdd(left, createMul(createMul(new IntValue(BigInteger.valueOf(-1)), right), createDiv(left, right)));
    }

    static Expression createAdd(Expression... addends) {
        List<Expression> unfolded = new ArrayList<>();
        Queue<Expression> queue = new LinkedList<>(List.of(addends));
        while (!queue.isEmpty()) {
            Expression a = queue.poll();
            if (a instanceof Sum add) {
                queue.addAll(add.getAddends());
            } else {
                unfolded.add(a);
            }
        }

        IntValue constant = IntValue.ZERO;
        List<Expression> newAddends = new ArrayList<>();
        Map<Variable, IntValue> vars = new HashMap<>();
        for (Expression addend : unfolded) {
            if (addend instanceof IntValue i) {
                constant = constant.add(i);
            } else if (addend instanceof Product mul && mul.getFactors().size() == 2 && mul.getFactors().get(0) instanceof Variable var && mul.getFactors().get(1) instanceof IntValue i) {
                vars.merge(var, i, IntValue::add);
            } else {
                newAddends.add(addend);
            }
        }
        for (Map.Entry<Variable, IntValue> e: vars.entrySet()) {
            newAddends.add(createMul(e.getValue(), e.getKey()));
        }

        if (constant.getValue().compareTo(BigInteger.ZERO) != 0) {
            newAddends.add(constant);
        }
        if (newAddends.size() == 0) {
            return IntValue.ZERO;
        } else if (newAddends.size() == 1) {
            return newAddends.get(0);
        } else {
            return new Sum(newAddends);
        }
    }

    static Expression createMul(Expression... factors) {
        List<Expression> unfolded = new ArrayList<>();
        Queue<Expression> queue = new LinkedList<>(List.of(factors));
        while (!queue.isEmpty()) {
            Expression f = queue.poll();
            if (f instanceof Product mul) {
                queue.addAll(mul.getFactors());
            } else {
                unfolded.add(f);
            }
        }

        IntValue constant = IntValue.ONE;
        List<Expression> newFactors = new ArrayList<>();
        for (Expression factor : unfolded) {
            if (factor instanceof IntValue i) {
                constant = constant.mul(i);
            } else {
                newFactors.add(factor);
            }
        }
        if (constant.getValue().compareTo(BigInteger.ZERO) == 0) {
            return IntValue.ZERO;
        }
        if (constant.getValue().compareTo(BigInteger.ONE) != 0) {
            newFactors.add(constant);
        }

        Optional<Sum> sum = newFactors.stream().filter(Sum.class::isInstance).map(Sum.class::cast).findAny();
        if (newFactors.size() > 1 && sum.isPresent()) {
            newFactors.remove(sum.get());
            List<Expression> newAddends = new ArrayList<>();
            for (Expression addend : sum.get().getAddends()) {
                for (Expression factor : newFactors) {
                    newAddends.add(createMul(addend, factor));
                }
            }
            return createAdd(newAddends.toArray(Expression[]::new));
        }

        if (newFactors.size() == 0) {
            return IntValue.ZERO;
        } else if (newFactors.size() == 1) {
            return newFactors.get(0);
        } else {
            return new Product(newFactors);
        }
    }

    static Expression createDiv(Expression numerator, Expression denominator) {
        if (numerator instanceof IntValue i && i.getValue().compareTo(BigInteger.ZERO) == 0) {
            return IntValue.ZERO;
        } else if (numerator instanceof IntValue n && denominator instanceof IntValue d) {
            return new IntValue(n.getValue().divide(d.getValue()));
        } else if (denominator instanceof IntValue d && d.getValue().compareTo(BigInteger.ONE) == 0) {
            return numerator;
        } else if (denominator instanceof IntValue d && numerator.isDividableBy(d.getValue())) {
            return numerator.divideSpecBy(d.getValue());
        } else if (denominator instanceof IntValue d && numerator instanceof Sum add && add.isGreaterOrEqualToZeroAndLessThan(d.getValue())) {
            return IntValue.ZERO;
        } else if (denominator instanceof IntValue && numerator.getVariables().size() == 1 && new Div(numerator, denominator).hasSameValueForEach(numerator.getVariables().iterator().next())) {
            return new IntValue(new Div(numerator, denominator).evaluate(Map.of(numerator.getVariables().iterator().next(), BigInteger.ONE)));
        } else if (denominator instanceof IntValue d && numerator instanceof Sum add && add.getAddends().stream().anyMatch(a -> a.isDividableBy(d.getValue()))) {
            List<Expression> dividable = add.getAddends().stream().filter(a -> a.isDividableBy(d.getValue())).map(a -> a.divideSpecBy(d.getValue())).toList();
            List<Expression> notDividable = add.getAddends().stream().filter(a -> !a.isDividableBy(d.getValue())).toList();
            Expression res1 = createDiv(createAdd(notDividable.toArray(Expression[]::new)), denominator);
            Expression res2 = createAdd(dividable.toArray(Expression[]::new));
            return createAdd(res1, res2);
        } else {
            return new Div(numerator, denominator);
        }
    }

    BigInteger evaluate(Map<Variable, BigInteger> values);

    default Set<Variable> getVariables() {
        Set<Variable> variables = new HashSet<>();
        collectVariables(variables);
        return variables;
    }

    void collectVariables(Set<Variable> variables);

    Expression divideSpecBy(BigInteger value);

    boolean isDividableBy(BigInteger value);

    default boolean hasSameValueForEach(Variable variable) {
        return IntStream.rangeClosed(1,9)
                .mapToObj(i -> Map.of(variable, BigInteger.valueOf(i)))
                .map(this::evaluate).collect(Collectors.toSet()).size() == 1;
    }
}