package com.hollonconsulting.solarwars.server.model.response;

public enum Status {
    SUCCESS("success"), FAIL("fail"), ERROR("error");

    private final String type;

    Status(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
