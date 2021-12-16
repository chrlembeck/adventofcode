package de.chrlembeck.aoccommon;

import java.math.BigInteger;
import java.util.Arrays;

public final class MathUtil {

    private MathUtil() {
        // utility class
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

    public static Integer min(final int first, final int... others) {
        int result = first;
        for (final int other : others) {
            result = Math.min(result, other);
        }
        return result;
    }

    public static BigInteger euclid(final BigInteger operandA, final BigInteger operandB) {
        if (operandB.signum() == 0) {
            return operandA;
        }
        return euclid(operandB, operandA.mod(operandB));
    }

    /**
     * L&ouml;st Gleichungsaufgaben der Art:
     * <ul>
     *     <li>x &equiv; factors<sub>1</sub> (mod modulos<sub>1</sub>)</li>
     *     <li>x &equiv; factors<sub>2</sub> (mod modulos<sub>2</sub>)</li>
     *     <li>&#x205D;</li>
     *     <li>x &equiv; factors<sub>n</sub> (mod modulos<sub>n</sub>)</li>
     * </ul>
     * <factors href="https://de.wikipedia.org/wiki/Chinesischer_Restsatz">Chinesischer Restsatz Wikipedia</factors>
     */
    @SuppressWarnings("PMD.UseVarargs")
    public static BigInteger[] chineeseRemainder(final BigInteger[] factors, final BigInteger[] modulos) {
        if (factors.length != modulos.length) {
            throw new IllegalArgumentException("Arrays do not have the same size.");
        }
        if (factors.length == 1) {
            // this is simple...
            return new BigInteger[] { factors[0], modulos[0] };
        }
        if (factors.length == 2) {
            // do the real computation
            final BigInteger[] euclideanResult = extendedEuclidean(modulos[0], modulos[1]);
            final BigInteger gcd = euclideanResult[2];
            final BigInteger firstFactor = euclideanResult[0];

            BigInteger newA = factors[0].subtract(firstFactor.multiply(modulos[0]).multiply(factors[0].subtract(factors[1]).divide(gcd)));
            final BigInteger newM = modulos[0].multiply(modulos[1]).divide(gcd);

            newA = newA.mod(newM);
            return new BigInteger[]{newA, newM};
        } else {
            // divide and conquer
            final BigInteger[] leftA = Arrays.copyOfRange(factors, 0, factors.length / 2);
            final BigInteger[] leftM = Arrays.copyOfRange(modulos, 0, modulos.length / 2);
            final BigInteger[] rightA = Arrays.copyOfRange(factors, factors.length / 2, factors.length);
            final BigInteger[] rightM = Arrays.copyOfRange(modulos, modulos.length / 2, modulos.length);
            final BigInteger[] left = chineeseRemainder(leftA, leftM);
            final BigInteger[] right = chineeseRemainder(rightA, rightM);
            return chineeseRemainder(new BigInteger[] { left[0], right[0] }, new BigInteger[] { left[1], right[1] });
        }
    }

    /**
     * Calculates integer factors x and y and the greatest common divisor of the two arguments argA and b so that argA&sdot;x+b&sdot;y=gcd(argA,b).
     *
     * @param argA first integer for the equation.
     * @param argB second integer for the equation.
     * @return array of three BigIntegers result={x,y,gcdAB}, where x is the first factor of the equation, y the second and gcdAB is the gratest common divisor of argA and b.
     */
    public static BigInteger[] extendedEuclidean(final BigInteger argA, final BigInteger argB) {
        BigInteger coefficientS = BigInteger.ZERO;
        BigInteger remainder = argB;
        BigInteger oldS = BigInteger.ONE;
        BigInteger oldRemainder = argA;
        BigInteger temp;
        BigInteger quotient;
        while (remainder.signum() != 0) {
            quotient = oldRemainder.divide(remainder);
            temp = remainder;
            remainder = oldRemainder.subtract(quotient.multiply(remainder));
            oldRemainder = temp;
            temp = coefficientS;
            coefficientS = oldS.subtract(quotient.multiply(coefficientS));
            oldS = temp;
        }
        BigInteger bezoutT;
        if (argB.signum() == 0) {
            bezoutT = BigInteger.ZERO;
        } else {
            bezoutT = oldRemainder.subtract(oldS.multiply(argA)).divide(argB);
        }
        return new BigInteger[] { oldS, bezoutT, oldRemainder };
    }

    public static int max(int[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("no element passed.");
        }
        int max = Integer.MIN_VALUE;
        for (int value:values) {
            max = Math.max(max, value);
        }
        return max;
    }
}