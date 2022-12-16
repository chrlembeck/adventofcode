package de.chrlembeck.aoc2021.day24;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public interface Expression {

    Optional<Range> getRange();

    static Expression createMod(Expression left, Expression right) {
        if (left instanceof IntValue i && i.value() == 0) {
            return i;
        }
        if (left instanceof IntValue l && right instanceof IntValue r) {
            return new IntValue(l.value() % (r.value()));
        }
        return createAdd(left, createMul(createMul(new IntValue(-1), right), createDiv(left, right)));
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
        for (Map.Entry<Variable, IntValue> e : vars.entrySet()) {
            newAddends.add(createMul(e.getValue(), e.getKey()));
        }

        if (constant.value() != 0) {
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
        if (constant.value() == 0) {
            return IntValue.ZERO;
        }
        if (constant.value() != 1) {
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
        if (numerator instanceof IntValue i && i.value() == 0) {
            return IntValue.ZERO;
        } else if (numerator instanceof IntValue n && denominator instanceof IntValue d) {
            return new IntValue(n.value()/d.value());
        } else if (denominator instanceof IntValue d && d.value()==1) {
            return numerator;
        } else if (denominator instanceof IntValue d && numerator.isDividableBy(d.value())) {
            return numerator.divideSpecBy(d.value());
        } else if (denominator instanceof IntValue d && numerator instanceof Sum add && add.isGreaterOrEqualToZeroAndLessThan(d.value())) {
            return IntValue.ZERO;
        } else if (denominator instanceof IntValue && numerator.getVariables().size() == 1 && new Div(numerator, denominator).hasSameValueForEach(numerator.getVariables().iterator().next())) {
            return new IntValue(new Div(numerator, denominator).evaluate(Map.of(numerator.getVariables().iterator().next(), 1l)));
        } else if (denominator instanceof IntValue d && numerator instanceof Sum add && add.getAddends().stream().anyMatch(a -> a.isDividableBy(d.value()))) {
            List<Expression> dividable = add.getAddends().stream().filter(a -> a.isDividableBy(d.value())).map(a -> a.divideSpecBy(d.value())).toList();
            List<Expression> notDividable = add.getAddends().stream().filter(a -> !a.isDividableBy(d.value())).toList();
            Expression res1 = createDiv(createAdd(notDividable.toArray(Expression[]::new)), denominator);
            Expression res2 = createAdd(dividable.toArray(Expression[]::new));
            return createAdd(res1, res2);
        } else {
            return new Div(numerator, denominator);
        }
    }

    long evaluate(Map<Variable, Long> values);

    default Set<Variable> getVariables() {
        Set<Variable> variables = new HashSet<>();
        collectVariables(variables);
        return variables;
    }

    void collectVariables(Set<Variable> variables);

    Expression divideSpecBy(long value);

    boolean isDividableBy(long value);

    default boolean hasSameValueForEach(Variable variable) {
        return LongStream.rangeClosed(1, 9)
                .mapToObj(i -> Map.of(variable, i))
                .map(this::evaluate).collect(Collectors.toSet()).size() == 1;
    }
}