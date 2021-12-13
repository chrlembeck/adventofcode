package de.chrlembeck.aoc2021.day12;

import java.util.Stack;

public class Route {

    private final Stack<Node> nodes = new Stack<>();

    private boolean smallCaveVisitiedTwice;

    private String twiceVisitedCave;

    public void push(final Node node) {
        if (node.isSmall() && nodes.contains(node)) {
            smallCaveVisitiedTwice = true;
            twiceVisitedCave = node.getName();
        }
        nodes.push(node);
    }

    public boolean visitForbidden(final Node node) {
        return node.isSmall() && nodes.contains(node);
    }

    public boolean secondVisitForbidden(final Node node) {
        return smallCaveVisitiedTwice && node.isSmall() && nodes.contains(node);
    }

    public void pop() {
        Node node = nodes.pop();
        if (smallCaveVisitiedTwice && node.isSmall() && node.getName().equals(twiceVisitedCave)) {
            smallCaveVisitiedTwice = false;
            twiceVisitedCave = null;
        }
    }

    @Override
    public String toString() {
        return nodes.toString();
    }
}