package de.chrlembeck.aoccommon;

import java.math.BigInteger;
import java.util.Arrays;

public final class LangUtils {

    private LangUtils() {
    }

    public static <T> T isNull(final T value, final T defaultValue) {
        return value == null ? defaultValue : value;
    }

    /**
     * Least common multiple od natural numbers.
     *
     * @param firstNumber first argument.
     * @param moreNumbers second or more arguments.
     * @return The least common multiple of the given arguments.
     */
    public static BigInteger lcm(final BigInteger firstNumber, final BigInteger... moreNumbers) {
        if (moreNumbers.length == 0) {
            return firstNumber;
        }
        if (moreNumbers.length == 1) {
            return firstNumber.multiply(moreNumbers[0]).divide(firstNumber.gcd(moreNumbers[0]));
        }
        return lcm(firstNumber, lcm(moreNumbers[moreNumbers.length - 1], Arrays.copyOf(moreNumbers, moreNumbers.length - 1)));
    }
}