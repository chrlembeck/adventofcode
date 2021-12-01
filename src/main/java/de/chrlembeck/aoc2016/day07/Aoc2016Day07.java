package de.chrlembeck.aoc2016.day07;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.*;

public class Aoc2016Day07 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2016Day07().run();
    }

    @Override
    public Long part1(final Scanner input) {
        return input.tokens().map(IP7::new).filter(IP7::supportsTLS).count();
    }

    @Override
    public Long part2(final Scanner input) {
        return input.tokens().map(IP7::new).filter(IP7::supportsSSL).count();
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2016/aoc2016day07.txt";
    }

    static class IP7 {

        private final List<String> hypernetSequences;

        private final List<String> supernetSequences;

        public IP7(String line) {
            supernetSequences = new ArrayList<>();
            hypernetSequences = new ArrayList<>();
            int left;
            int right;
            do {
                left = line.indexOf('[');
                right = line.indexOf(']');

                if (left >= 0) {
                    supernetSequences.add(line.substring(0, left));
                } else {
                    supernetSequences.add(line);
                }
                if (right >= 0) {
                    hypernetSequences.add(line.substring(left + 1, right));
                }
                if (left >= 0 || right >= 0) {
                    line = line.substring(right + 1);
                }
            } while (left >= 0 || right >= 0);
        }

        public boolean supportsTLS() {
            for (final String token : hypernetSequences) {
                if (hasABBA(token)) {
                    return false;
                }
            }
            for (final String token : supernetSequences) {
                if (hasABBA(token)) {
                    return true;
                }
            }
            return false;
        }

        public boolean supportsSSL() {
            final Set<String> abas = new TreeSet<>();
            for (final String token : supernetSequences) {
                collectABA(abas, token);
            }
            for (final String aba : abas) {
                final String bab = Character.toString(aba.charAt(1)) + aba.charAt(0) + aba.charAt(1);
                for (final String token : hypernetSequences) {
                    if (token.contains(bab)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public static boolean hasABBA(final String token) {
            if (token.length() < 4) {
                return false;
            }
            for (int i = 0; i < token.length() - 3; i++) {
                if (token.charAt(i) == token.charAt(i + 3) && token.charAt(i + 1) == token.charAt(i + 2) && token.charAt(i) != token.charAt(i + 1)) {
                    return true;
                }
            }
            return false;
        }

        public static void collectABA(final Collection<String> abas, final String token) {
            for (int i = 0; i < token.length() - 2; i++) {
                if (token.charAt(i) == token.charAt(i + 2) && token.charAt(i) != token.charAt(i + 1)) {
                    abas.add(token.substring(i, i + 2));
                }
            }
        }
    }
}