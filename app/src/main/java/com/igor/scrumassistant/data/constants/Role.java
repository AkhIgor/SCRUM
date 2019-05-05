package com.igor.scrumassistant.data.constants;

import android.support.annotation.NonNull;

public enum Role {
    DEVELOPER("DEVELOPER"),
    DESIGNER("DESIGNER"),
    ANALYTIC("ANALYTIC"),
    PRODUCT_OWNER("PRODUCT_OWNER"),
    SCRUM_MASTER("SCRUM_MASTER");

    private String value;

    Role(String value) {
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

    public static Role getEnum(String value) {
        for (Role v : values())
            if (v.getValue().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
