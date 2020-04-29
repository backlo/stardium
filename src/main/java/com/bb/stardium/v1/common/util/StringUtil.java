package com.bb.stardium.v1.common.util;

import java.util.Objects;

public class StringUtil {

    public static boolean isBlank(final String string) {
        return Objects.isNull(string) || string.isEmpty();
    }
}
