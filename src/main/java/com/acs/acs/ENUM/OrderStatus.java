package com.acs.acs.ENUM;

public enum OrderStatus {
    INITIATED(0),
    CREATED(1),
    BACKORDER(18),
    ASSIGNED(2);

    private final int code;

    // Constructor
    OrderStatus(int code) {
        this.code = code;
    }

    // Getter for code
    public int getCode() {
        return code;
    }

    // Method to get enum from code
    public static OrderStatus fromCode(int code) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
