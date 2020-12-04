package de.chrlembeck.aoccommon;

public final class LangUtils {

    private LangUtils() {
    }

    public static <T> T isNull(final T value, final T defaultValue) {
        return value == null ? defaultValue : value;
    }
}