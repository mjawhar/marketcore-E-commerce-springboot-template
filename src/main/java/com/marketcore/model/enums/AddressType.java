package com.marketcore.model.enums;

public enum AddressType {
    FACTURATION("Facturation"),
    LIVRAISON("Livraison");

    private final String displayName;

    AddressType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
