package de.chrlembeck.aoc2021.day12;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.regex.Pattern;

public class Aoc2021Day12 extends AbstractAocBase {

    private static final Pattern REGEX = Pattern.compile("(\\w+)-(\\w+)");

    public static void main(final String[] args) {
        new Aoc2021Day12().run();
    }

    @Override
    public Object part1(final Scanner input) {
        return findRoutes(new Route(), readNodes(input), Route::visitForbidden);
    }

    @Override
    public Object part2(final Scanner input) {
        return findRoutes(new Route(), readNodes(input), Route::secondVisitForbidden);
    }

    private Node readNodes(final Scanner input) {
        Map<String, Node> nodes = new TreeMap<>();
        List<Edge> edges = new ArrayList<>();
        input.findAll(REGEX).forEach(mr -> {
            Node node1 = nodes.computeIfAbsent(mr.group(1), Node::new);
            Node node2 = nodes.computeIfAbsent(mr.group(2), Node::new);
            Edge edge = new Edge(node1, node2);
            edges.add(edge);
            node1.addEdge(edge);
            node2.addEdge(edge);
        });
        return nodes.get("start");
    }

    private int findRoutes(final Route route, final Node currentNode, final BiFunction<Route, Node, Boolean> check) {
        route.push(currentNode);
        final AtomicInteger counter = new AtomicInteger();
        currentNode.getEdges().stream().filter(edge -> {
                    Node other = edge.other(currentNode);
                    return !other.isStartNode() && !check.apply(route, other);
                }
        ).forEach(nextEdge -> {
            Node nextNode = nextEdge.other(currentNode);
            if (nextNode.isEndNode()) {
                counter.incrementAndGet();
            } else {
                counter.addAndGet(findRoutes(route, nextNode, check));
            }
        });
        route.pop();
        return counter.get();
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2021/aoc2021day12.txt";
    }
}