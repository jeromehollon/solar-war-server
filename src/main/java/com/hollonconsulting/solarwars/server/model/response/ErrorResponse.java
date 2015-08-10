package com.hollonconsulting.solarwars.server.model.response;

import java.util.Objects;

public class ErrorResponse {
    protected Integer code;
    protected Status status;
    protected String message;

    public ErrorResponse() {
    }

    /**
     * Non-default constructor
     * @param code    Represents the error code.
     * @param status  Represents the error status.
     * @param message Is the error message returned.
     */
    public ErrorResponse(Integer code, Status status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ErrorResponse response = (ErrorResponse) obj;
        return Objects.equals(code, response.code)
                && Objects.equals(status, response.status)
                && Objects.equals(message, response.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, status, message);
    }
}
