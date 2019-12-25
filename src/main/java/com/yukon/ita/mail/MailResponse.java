package com.yukon.ita.mail;

public class MailResponse {
    private String message;
    private static boolean status;

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public static boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

}
