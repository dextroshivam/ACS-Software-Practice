package com.acs.acs.ENUM;

public enum RoleType {

    GENERAL,
    ADMIN,
    SUPERADMIN

//    ADMIN(0),
//    DONOR(1),
//    YACHAK(2),
//    SUPER_ADMIN(3),
//    USER(4),
//    GUEST(5);
//
//    private final int value;
//
//    Role(int value) {
//        this.value = value;
//    }
//
//    public int getValue() {
//        return value;
//    }
//
//    public static Role fromValue(int value) {
//        for (Role role : Role.values()) {
//            if (role.getValue() == value) {
//                return role;
//            }
//        }
//        throw new IllegalArgumentException("Invalid role value: " + value);
//    }
}
