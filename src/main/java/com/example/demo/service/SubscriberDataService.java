package com.example.demo.service;

import com.example.demo.model.Subscriber;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriberDataService {

    private static final String SUBSCRIBERS_FILE = "src/main/resources/Subscribers.csv";

    public List<Subscriber> readSubscribers() throws IOException {
        List<Subscriber> subscribers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(SUBSCRIBERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 4) {
                    Subscriber subscriber = new Subscriber(data[0], "1".equals(data[1]), Double.parseDouble(data[2]), Double.parseDouble(data[3]));
                    subscribers.add(subscriber);
                }
            }
        }
        return subscribers;
    }

    public void writeSubscribers(List<Subscriber> subscribers) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SUBSCRIBERS_FILE))) {
            for (Subscriber subscriber : subscribers) {
                writer.println(subscriber.getPhoneNumber() + "," + (subscriber.isSubscribed() ? "1" : "0") + "," + subscriber.getbalance() + "," + subscriber.getOperationValue());
            }
        }
    }
}