package com.marketcore.model.enums;

public enum ProductType {
    NEW("Neuf"),
    USED("D'occasion");

    private final String displayName;

    ProductType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
