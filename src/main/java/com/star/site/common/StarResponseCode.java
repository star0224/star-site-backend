package com.star.site.common;

public enum StarResponseCode {
    SUCCESS(1, "SUCCESS"),
    ERROR(0, "ERROR"),
    ILLEGAL_PERMISSION(2, "ILLEGAL_PERMISSION")
    ;

    private final int Code;

    private final String desc;

    StarResponseCode(int code, String desc) {
        Code = code;
        this.desc = desc;
    }

    public int getCode() {
        return Code;
    }

    public String getDesc() {
        return desc;
    }
}
