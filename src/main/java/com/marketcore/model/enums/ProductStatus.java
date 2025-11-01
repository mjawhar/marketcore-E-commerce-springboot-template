package com.marketcore.model.enums;

public enum ProductStatus {
    NEW("Nouveau"),
    IN_PROGRESS("En cours"),
    VALIDATED("Validé");

    private final String displayName;

    ProductStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
