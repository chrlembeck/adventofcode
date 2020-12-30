package de.chrlembeck.aoccommon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.math.BigInteger;

public class MathUtilTest {

    @Test
    public void euclid() {
        BigInteger[] euclid = MathUtil.extendedEuclidean(BigInteger.valueOf(240), BigInteger.valueOf(46));
        Assertions.assertNotNull(euclid);
        Assertions.assertEquals(3, euclid.length);
        Assertions.assertEquals(0, euclid[0].compareTo(BigInteger.valueOf(-9)));
        Assertions.assertEquals(0, euclid[1].compareTo(BigInteger.valueOf(47)));
        Assertions.assertEquals(0, euclid[2].compareTo(BigInteger.valueOf(2)));
    }

    @Test
    public void chineeseRemainder() {
        BigInteger a[] = {
                BigInteger.ONE,
                BigInteger.ONE,
                BigInteger.ONE,
                BigInteger.ONE,
                BigInteger.ONE,
                BigInteger.ZERO
        };
        BigInteger[] m = {
                BigInteger.valueOf(2),
                BigInteger.valueOf(3),
                BigInteger.valueOf(4),
                BigInteger.valueOf(5),
                BigInteger.valueOf(6),
                BigInteger.valueOf(7)
        };
        BigInteger[] chinRem = MathUtil.chineeseRemainder(a, m);
        Assertions.assertNotNull(chinRem);
        Assertions.assertEquals(2, chinRem.length);
        Assertions.assertEquals(0, chinRem[0].compareTo(BigInteger.valueOf(301)));
        Assertions.assertEquals(0, chinRem[1].compareTo(BigInteger.valueOf(420)));
    }

    @Test
    public void chineeseRemainder2() {
        BigInteger a[] = {
                BigInteger.ZERO,
                BigInteger.valueOf(12),
                BigInteger.valueOf(55),
                BigInteger.valueOf(25),
                BigInteger.valueOf(12)
        };
        BigInteger[] m = {
                BigInteger.valueOf(7),
                BigInteger.valueOf(13),
                BigInteger.valueOf(59),
                BigInteger.valueOf(31),
                BigInteger.valueOf(19)
        };
        BigInteger[] chinRem = MathUtil.chineeseRemainder(a, m);
        Assertions.assertNotNull(chinRem);
        Assertions.assertEquals(2, chinRem.length);
        Assertions.assertEquals(0, chinRem[0].compareTo(BigInteger.valueOf(1068781)));
        Assertions.assertEquals(0, chinRem[1].compareTo(BigInteger.valueOf(3162341)));
    }
}