package de.chrlembeck.aoc2021.day24;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static de.chrlembeck.aoc2021.day24.BooleanConstant.FALSE;
import static de.chrlembeck.aoc2021.day24.BooleanConstant.TRUE;

public interface BooleanCondition {

    static BooleanCondition createEqual(Expression left, Expression right) {
        if (left == right || left.equals(right)) {
            return TRUE;
        } else if (left instanceof IntValue i && right instanceof IntValue r) {
            return (i.getValue().compareTo(r.getValue()) == 0) ? TRUE : FALSE;
        } else if (left instanceof Variable && right instanceof IntValue i) {
            if (i.getValue().compareTo(BigInteger.ZERO) <= 0 || i.getValue().compareTo(BigInteger.TEN) >= 0) {
                return FALSE;
            }
        } else if (right instanceof Variable && left instanceof IntValue i) {
            if (i.getValue().compareTo(BigInteger.ZERO) <= 0 || i.getValue().compareTo(BigInteger.TEN) >= 0) {
                return FALSE;
            }
        } else if (left instanceof Variable && right.getRange().isPresent() && right.getRange().get().isOutsideVariableRange()) {
            return FALSE;
        } else if (right instanceof Variable && left.getRange().isPresent() && left.getRange().get().isOutsideVariableRange()) {
            return FALSE;
        } else if (right instanceof IntValue i && left.getRange().isPresent() && !left.getRange().get().contains(i.getValue())) {
            return FALSE;
        }
        return new EqualCondition(left, right);
    }

    static BooleanCondition createNotEqual(Expression left, Expression right) {
        if (left == right || left.equals(right)) {
            return FALSE;
        } else if (left instanceof IntValue i && right instanceof IntValue r) {
            return (i.getValue().compareTo(r.getValue()) == 0) ? FALSE : TRUE;
        }
        return createOr(createLessThan(left, right), createLessThan(right, left));
    }

    static BooleanCondition createLessThan(Expression left, Expression right) {
        if (left == right || left.equals(right)) {
            return FALSE;
        } else if (left instanceof IntValue l && right instanceof IntValue r) {
            return (l.getValue().compareTo(r.getValue()) < 0) ? TRUE : FALSE;
        } else if (left instanceof Variable && right.getRange().isPresent() && right.getRange().get().upper().compareTo(BigInteger.ONE) <= 0) {
            return FALSE;
        } else if (left instanceof Variable && right.getRange().isPresent() && right.getRange().get().lower().compareTo(BigInteger.valueOf(9)) >= 0) {
            return TRUE;
        } else if (right instanceof Variable && left.getRange().isPresent() && left.getRange().get().lower().compareTo(BigInteger.valueOf(9)) >= 0) {
            return FALSE;
        } else if (right instanceof Variable && left.getRange().isPresent() && left.getRange().get().upper().compareTo(BigInteger.ONE) <= 0) {
            return TRUE;
        }
        return new LessThanCondition(left, right);
    }

    static BooleanCondition createAnd(BooleanCondition... conditions) {
        Queue<BooleanCondition> queue = new LinkedList<>(List.of(conditions));
        List<BooleanCondition> unfolded = new ArrayList<>();
        while (!queue.isEmpty()) {
            BooleanCondition cond = queue.poll();
            if (cond instanceof AndCondition and) {
                queue.addAll(and.getConditions());
            } else {
                unfolded.add(cond);
            }
        }
        List<BooleanCondition> newConditions = new ArrayList<>();
        for (BooleanCondition condition : unfolded) {
            if (condition == FALSE) {
                return FALSE;
            } else if (condition == TRUE) {
                continue;
            } else {
                newConditions.add(condition);
            }
        }
        if (newConditions.size() == 0) {
            return TRUE;
        } else if (newConditions.size() == 1) {
            return newConditions.get(0);
        } else {
            return new AndCondition(newConditions);
        }
    }

    static BooleanCondition createOr(BooleanCondition... conditions) {
        Queue<BooleanCondition> queue = new LinkedList<>(List.of(conditions));
        List<BooleanCondition> unfolded = new ArrayList<>();
        while (!queue.isEmpty()) {
            BooleanCondition cond = queue.poll();
            if (cond instanceof OrCondition or) {
                queue.addAll(or.getConditions());
            } else {
                unfolded.add(cond);
            }
        }
        List<BooleanCondition> newConditions = new ArrayList<>();
        for (BooleanCondition condition : unfolded) {
            if (condition == TRUE) {
                return TRUE;
            } else if (condition != FALSE) {
                newConditions.add(condition);
            }
        }
        if (newConditions.size() == 0) {
            return FALSE;
        } else if (newConditions.size() == 1) {
            return newConditions.get(0);
        } else {
            return new OrCondition(newConditions);
        }
    }
}