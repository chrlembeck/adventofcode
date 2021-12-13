package de.chrlembeck.aoc2021.day12;

public class Edge {

    private final Node node1;

    private final Node node2;

    public Edge(Node node1, Node node2) {
        this.node1 = node1;
        this.node2 = node2;
    }

    public Node other(Node node) {
        return node1.equals(node) ? node2 : node1;
    }

    @Override
    public String toString() {
        return node1 + "-" + node2;
    }

    public boolean contains(Node node) {
        return node1.equals(node) || node2.equals(node);
    }
}