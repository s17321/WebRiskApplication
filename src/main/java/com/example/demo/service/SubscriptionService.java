package com.example.demo.service;

import com.example.demo.model.SmsMessage;
import com.example.demo.model.Subscriber;
import com.example.demo.model.SubscriptionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionService {
    @Autowired
    private SubscriberDataService subscriberDataService;
    private static final String SUBSCRIBERS_FILE = "src/main/resources/Subscribers.csv";

    public void subscribe(SmsMessage smsMessage) throws IOException {
        if ("START".equalsIgnoreCase(smsMessage.getContent())) {
            List<Subscriber> subscribers = subscriberDataService.readSubscribers();
            Subscriber existingSubscriber = findSubscriberByPhoneNumber(subscribers, smsMessage.getPhoneNumber());

            if (existingSubscriber != null) {
                if (existingSubscriber.isSubscribed()) {
                    return; // Użytkownik już subskrybuje usługę
                }
                existingSubscriber.setSubscribed(true);
            } else {
                subscribers.add(new Subscriber(smsMessage.getPhoneNumber(), true, 0, 0));
            }
            subscriberDataService.writeSubscribers(subscribers);
        }
    }

    public void unsubscribe(SmsMessage smsMessage) throws IOException {
        if ("STOP".equalsIgnoreCase(smsMessage.getContent())) {
            List<Subscriber> subscribers = subscriberDataService.readSubscribers();
            Subscriber existingSubscriber = findSubscriberByPhoneNumber(subscribers, smsMessage.getPhoneNumber());

            if (existingSubscriber != null && existingSubscriber.isSubscribed()) {
                existingSubscriber.setSubscribed(false);
                subscriberDataService.writeSubscribers(subscribers);  // Aktualizacja listy subskrybentów
            } else if (existingSubscriber != null && !existingSubscriber.isSubscribed()) {
                // Użytkownik już zrezygnował z subskrypcji
                throw new IllegalStateException("Użytkownik już zrezygnował z subskrypcji.");
            } else {
                // Użytkownik nie był zapisany
                throw new IllegalStateException("Użytkownik nie był zapisany do subskrypcji.");
            }
        }
    }

    private Subscriber findSubscriberByPhoneNumber(List<Subscriber> subscribers, String phoneNumber) {
        return subscribers.stream().filter(s -> s.getPhoneNumber().equals(phoneNumber)).findFirst().orElse(null);
    }

    public boolean isSubscribed(String phoneNumber) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(SUBSCRIBERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 4 && data[0].equals(phoneNumber)) {
                    return "1".equals(data[1]);
                }
            }
        }
        return false; // Numer telefonu nie znaleziony w pliku
    }
}