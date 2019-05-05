package com.igor.scrumassistant.data.constants;

import android.support.annotation.NonNull;


public enum Priority {
    LOW("LOW"),
    MEDIUM("MEDIUM"),
    HIGH("HIGH"),
    CRITICAL("CRITICAL");

    private String value;

    Priority(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    @NonNull
    public String toString() {
        return this.getValue();
    }

    public static Priority getEnum(String value) {
        for (Priority v : values())
            if (v.getValue().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
