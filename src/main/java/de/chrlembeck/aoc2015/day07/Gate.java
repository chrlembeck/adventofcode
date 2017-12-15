package de.chrlembeck.aoc2015.day07;

import java.util.Map;

public interface Gate {

    int execute(Map<String, Gate> program);
}
