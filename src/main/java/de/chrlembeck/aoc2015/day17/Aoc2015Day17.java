package de.chrlembeck.aoc2015.day17;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Aoc2015Day17 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2015Day17().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        CountConsumer counter = new CountConsumer();
        fill(150, input.tokens().map(Integer::valueOf).collect(Collectors.toList()), new ArrayList<>(), counter);
        return counter.counter;
    }

    private void fill(int volume, List<Integer> containers, List<Integer> usedContainers, Consumer<Integer[]> resultConsumer) {
        List<Integer> unused = new ArrayList<>();
        while (!containers.isEmpty()) {
            Integer container = containers.remove(0);
            unused.add(container);
            usedContainers.add(container);
            if (container < volume) {
                fill(volume - container, containers, usedContainers, resultConsumer);
            } else if (container == volume){
                resultConsumer.accept(usedContainers.toArray(Integer[]::new));
            }
            usedContainers.remove(usedContainers.size()-1);
        }
        containers.addAll(unused);
    }


    @Override
    public Integer part2(final Scanner input) {
        MinCountConsumer counter = new MinCountConsumer();
        fill(150, input.tokens().map(Integer::valueOf).collect(Collectors.toList()), new ArrayList<>(), counter);
        return counter.counter;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2015/aoc2015day17.txt";
    }

    static class CountConsumer implements Consumer<Integer[]> {

        int counter;

        @Override
        public void accept(Integer[] containers) {
            counter++;
        }
    }

    static class MinCountConsumer implements Consumer<Integer[]> {

        int minSize = Integer.MAX_VALUE;

        int counter;

        @Override
        public void accept(Integer[] containers) {
            if (containers.length == minSize) {
                counter++;
            } else if (containers.length < minSize) {
                counter = 1;
                minSize = containers.length;
            }
        }
    }
}