package com.example.demo.model;

public class SmsMessage {
    private String phoneNumber;
    private String content;

    public SmsMessage(String phoneNumber, String content) {
        this.phoneNumber = phoneNumber;
        this.content = content;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getContent() {
        return content;
    }
}