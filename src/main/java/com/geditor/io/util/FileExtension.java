package com.geditor.io.util;

import java.util.Arrays;

/**
 * Created by marcin on 19.03.16.
 */
public enum FileExtension {
    PPMP3,
    PPMP6;

    public static FileExtension valueOfIgnoreCase(String value) {
        return Arrays.stream(values()).filter(fileExtension -> fileExtension.name().equalsIgnoreCase(value)).findAny().orElse(null);
    }
}
