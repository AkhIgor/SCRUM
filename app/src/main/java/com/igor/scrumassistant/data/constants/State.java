package com.igor.scrumassistant.data.constants;

import android.support.annotation.NonNull;

public enum State {
    OPEN("OPEN"),
    IN_WORK("IN_WORK"),
    DONE("DONE"),
    DECLINED("DECLINED");

    private String value;

    State(String value) {
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

    public static State getEnum(String value) {
        for (State v : values())
            if (v.getValue().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
