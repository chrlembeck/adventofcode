package de.chrlembeck.aoc2021.day12;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {

    private final String name;

    private boolean visited;

    private List<Edge> edges = new ArrayList<>();

    public Node(String name) {
        this.name = name;
    }

    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }

    public List<Edge> getEdges() {
        return edges;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node node = (Node) o;
        return Objects.equals(name, node.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public boolean isVisited() {
        return visited;
    }

    public void visit() {
        visited = true;
    }

    public void unvisit() {
        visited = false;
    }

    public boolean visitable() {
        return !visited || Character.isUpperCase(name.charAt(0));
    }

    public boolean isEndNode() {
        return name.equals("end");
    }

    public boolean isSmall() {
        return Character.isLowerCase(name.charAt(0));
    }

    public String getName() {
        return name;
    }

    public boolean isStartNode() {
        return name.equals("start");
    }
}