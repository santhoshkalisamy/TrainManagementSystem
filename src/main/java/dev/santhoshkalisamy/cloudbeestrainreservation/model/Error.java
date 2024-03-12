package dev.santhoshkalisamy.cloudbeestrainreservation.model;
public class Error {
    private int errorCode;
    private String details;

    public Error(int errorCode, String details) {
        this.errorCode = errorCode;
        this.details = details;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
