package com.example.demo.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SMSData {
    private String sender;
    private String recipient;
    private String message;


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
