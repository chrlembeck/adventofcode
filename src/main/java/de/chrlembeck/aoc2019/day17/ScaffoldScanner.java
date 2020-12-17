package de.chrlembeck.aoc2019.day17;

import java.math.BigInteger;
import java.util.function.Consumer;

public class ScaffoldScanner implements Consumer<BigInteger> {

    StringBuilder sb = new StringBuilder();

    @Override
    public void accept(BigInteger bigInteger) {
        char ch = (char) bigInteger.intValueExact();
        sb.append(ch);
    }

    public String getScaffold() {
        return sb.toString();
    }

    public int getAlignmentParameters() {
        int alignmentParamter = 0;
        String[] field = sb.toString().split("\n");
        for (int y = 1; y < field.length - 1; y++) {
            for (int x = 1; x < field[0].length() - 1; x++) {
                if (field[y].charAt(x) == '#' &&
                        field[y - 1].charAt(x) == '#' &&
                        field[y + 1].charAt(x) == '#' &&
                        field[y].charAt(x - 1) == '#' &&
                        field[y].charAt(x + 1) == '#') {
                    alignmentParamter += x * y;
                }
            }
        }
        return alignmentParamter;
    }
}