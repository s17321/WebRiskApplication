package com.example.demo.model;

import java.io.*;

public class Subscriber implements Serializable {
    private String phoneNumber;
    private boolean isSubscribed;
    private double balance;
    private double operationValue;

    public Subscriber(String phoneNumber, boolean isSubscribed, double balance, double operationValue) {
        this.phoneNumber = phoneNumber;
        this.isSubscribed = isSubscribed;
        this.balance = balance;
        this.operationValue = operationValue;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSubscribed(boolean subscribed) {
        isSubscribed = subscribed;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setOperationValue(double operationValue) {
        this.operationValue = operationValue;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isSubscribed() {
        return isSubscribed;
    }

    public double getbalance() {
        return balance;
    }

    public double getOperationValue() {
        return operationValue;
    }
}