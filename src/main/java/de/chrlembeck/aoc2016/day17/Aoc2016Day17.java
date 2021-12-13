package de.chrlembeck.aoc2016.day17;

import de.chrlembeck.aoccommon.AbstractAocBase;
import de.chrlembeck.util.algorithm.Algorithms;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class Aoc2016Day17 extends AbstractAocBase {

    public static final char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    static MessageDigest md5;

    static {
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new Error(e);
        }
    }

    public static void main(final String[] args) {
        new Aoc2016Day17().run();
    }

    @Override
    public String part1(final Scanner scanner) {
        String input = scanner.nextLine();
        List<State> result = Algorithms.breadthFirstSearch(new State(input, (byte) 0, (byte) 0, ""),
                sw -> successors(input, sw),
                e -> {
                },
                sw -> sw.getState().getX() == 3 && sw.getState().getY() == 3);
        return result.get(result.size() - 1).getPath();
    }

    @Override
    public Integer part2(final Scanner scanner) {
        String input = scanner.nextLine();
        StateHolder longest = new StateHolder();
        longest.state = new State(input, (byte) 0, (byte) 0, "");

        Consumer<Algorithms.StateWrapper<State>> cons = sw -> {
            State st = sw.getState();
            if (st.getX() == 3 && st.getY() == 3 && st.getPath().length() > longest.state.getPath().length()) {
                longest.state = st;
            }
        };

        Algorithms.breadthFirstSearch(new State(input, (byte) 0, (byte) 0, ""), sw -> successors(input, sw), cons, sw -> false);
        return longest.state.getPath().length();
    }

    public static List<State> successors(String input, Algorithms.StateWrapper<State> wrapper) {
        State state = wrapper.getState();
        if (state.getX() == 3 && state.getY() == 3) {
            return Collections.EMPTY_LIST;
        }
        String digest = md5(input + state.getPath());
        final char a = digest.charAt(0);
        final char b = digest.charAt(1);
        final char c = digest.charAt(2);
        final char d = digest.charAt(3);
        List<State> result = new ArrayList<>();
        if (state.getY() > 0 && a >= 'b' && a <= 'f') {
            result.add(new State(input, state.getX(), (byte) (state.getY() - 1), state.getPath() + "U"));
        }
        if (state.getY() < 3 && b >= 'b' && b <= 'f') {
            result.add(new State(input, state.getX(), (byte) (state.getY() + 1), state.getPath() + "D"));
        }
        if (state.getX() > 0 && c >= 'b' && c <= 'f') {
            result.add(new State(input, (byte) (state.getX() - 1), state.getY(), state.getPath() + "L"));
        }
        if (state.getX() < 3 && d >= 'b' && d <= 'f') {
            result.add(new State(input, (byte) (state.getX() + 1), state.getY(), state.getPath() + "R"));
        }
        return result;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2016/aoc2016day17.txt";
    }

    public static String md5(String input) {
        byte[] digest = md5.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(digits[(b & 0xf0) >> 4]);
            sb.append(digits[b & 0x0f]);
        }
        return sb.toString();
    }

    static class StateHolder {

        State state;
    }
}