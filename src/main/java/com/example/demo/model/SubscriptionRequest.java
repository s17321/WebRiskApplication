package com.example.demo.model;

public class SubscriptionRequest {
    private String phoneNumber;

    public SubscriptionRequest(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
