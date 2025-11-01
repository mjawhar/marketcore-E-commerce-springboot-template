package com.marketcore.model;

public enum StockStatus {
    INSTOCK,
    OUTOFSTOCK;

    public static StockStatus fromValue(String value) {
        if (value == null) {
            return INSTOCK;
        }

        switch (value.toLowerCase()) {
            case "instock":
                return INSTOCK;
            case "outofstock":
                return OUTOFSTOCK;
            default:
                throw new IllegalArgumentException("Unknown stock status: " + value);
        }
    }

    public String getValue() {
        return this.name().toLowerCase();
    }
}
