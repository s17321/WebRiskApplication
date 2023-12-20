package com.example.demo.controller;

import com.example.demo.model.SMSData;
import com.example.demo.service.SmsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class HelloController {

    @Autowired
    private SmsService smsService;

    @GetMapping("/hello")
    public String hello() {

//        smsService.sendSms(smsData);
        return "Hello!!!";
    }

    @PostMapping("/SendSMS")
    public ResponseEntity<String> sendSMS(@RequestBody SMSData smsData) {

        try {
            String result = smsService.sendSms(smsData);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
