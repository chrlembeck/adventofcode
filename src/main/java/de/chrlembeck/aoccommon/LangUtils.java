package de.chrlembeck.aoccommon;

public final class LangUtils {

    private LangUtils() {
    }

    public static <T> T replaceIfNull(final T value, final T defaultValue) {
        return value == null ? defaultValue : value;
    }

}