package de.chrlembeck.aoc2019.day14;

import java.math.BigInteger;

public class FormulaUnit {

    private final BigInteger quantity;

    private final String name;

    public FormulaUnit(final BigInteger quantity, final String name) {
        this.quantity = quantity;
        this.name = name;
    }

    public BigInteger getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }
}