package com.acs.acs.ENUM;

public enum ReceiveStatus {
    RECEIVED(1, "Received"), STOW(2, "Stow"), RELABEL(3, "Relabel"),
    PICKED(5, "PickedUp"), COMPLETE(6, "Complete");

    private Integer id;

    private String fieldName;

    ReceiveStatus(Integer id, String fieldName) {
        this.id = id;
        this.fieldName = fieldName;
    }

    public Integer getId() {
        return this.id;
    }

    public String getFieldName() {
        return this.fieldName;
    }
}
