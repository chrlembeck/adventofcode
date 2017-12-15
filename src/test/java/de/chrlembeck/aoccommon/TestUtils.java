package de.chrlembeck.aoccommon;

import java.util.Scanner;
import java.util.function.Function;

public class TestUtils {

    public static String test(final String input, final Function<Scanner, Object> function) {
        try (Scanner scanner = new Scanner(input)) {
            final Object result = function.apply(scanner);
            return result == null ? null : result.toString();
        }
    }
}