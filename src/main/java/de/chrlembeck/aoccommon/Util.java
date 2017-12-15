package de.chrlembeck.aoccommon;

public final class Util {

    private Util() {
    }

    public static <T> T isNull(final T value, final T defaultValue) {
        return value == null ? defaultValue : value;
    }
}