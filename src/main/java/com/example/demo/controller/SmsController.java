package com.example.demo.controller;

import com.example.demo.model.SmsMessage;
import com.example.demo.model.Subscriber;
import com.example.demo.model.SubscriptionRequest;
import com.example.demo.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class SmsController {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/subscriber/{phoneNumber}")
    public ResponseEntity<String> checkSubscription(@PathVariable String phoneNumber) {
        try {
            boolean isSubscribed = subscriptionService.isSubscribed(phoneNumber);
            if (isSubscribed) {
                return ResponseEntity.ok("Numer " + phoneNumber + " jest zapisany do subskrypcji.");
            } else {
                return ResponseEntity.ok("Numer " + phoneNumber + " nie jest zapisany do subskrypcji.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Błąd przy sprawdzaniu subskrypcji: " + e.getMessage());
        }
    }

    @PostMapping("/receiveSMS")
    public ResponseEntity<String> receiveSMS(@RequestBody SmsMessage smsMessage) {
        try {
            if ("START".equalsIgnoreCase(smsMessage.getContent())) {
                subscriptionService.subscribe(smsMessage);
                return ResponseEntity.ok("Subskrypcja aktywowana dla numeru: " + smsMessage.getPhoneNumber());
            } else if ("STOP".equalsIgnoreCase(smsMessage.getContent())) {
                subscriptionService.unsubscribe(smsMessage);
                return ResponseEntity.ok("Subskrypcja dezaktywowana dla numeru: " + smsMessage.getPhoneNumber());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nieznana treść wiadomości: " + smsMessage.getContent());
            }
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Błąd serwera: " + e.getMessage());
        }
    }
}
