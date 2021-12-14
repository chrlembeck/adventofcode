package de.chrlembeck.aoc2021.day14;

public class Rule {

    private final String lhs;

    private final String insertion;

    public Rule(String lhs, String insertion) {
        this.lhs = lhs;
        this.insertion = insertion;
    }

    public String getLhs() {
        return lhs;
    }

    public String getResult() {
        return insertion + lhs.substring(1,2);
    }

    public String getInsertion() {
        return insertion;
    }
}