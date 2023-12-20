package com.example.demo.service;

import com.example.demo.client.WebRiskClient;
import com.example.demo.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class SmsService {
    @Autowired
    private WebRiskClient webRiskClient;

    @Autowired
    private SubscriberDataService subscriberDataService;

    public String sendSms(SMSData smsData) throws IOException {
        System.out.println(smsData.getMessage());
        String url = UrlExtractor.extractURLFromJSON(smsData.getMessage());

        try {
            EvaluateRiskRequest riskRequest = new EvaluateRiskRequest(
                    url,
                    Arrays.asList("SOCIAL_ENGINEERING", "MALWARE"),
                    true
            );
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(riskRequest);
            System.out.println(json);

            EvaluateUriResponse response = webRiskClient.evaluateUri(riskRequest);

            // Aktualizacja salda subskrybenta
            updateSubscriberBalance(smsData.getRecipient(), 2.0); // 'recipient' to numer telefonu subskrybenta

            return processWebRiskResponse(response);
        } catch (IOException e) {
            return "Wystąpił błąd podczas sprawdzania URL: " + e.getMessage();
        }
    }

    private void updateSubscriberBalance(String phoneNumber, double operationValue) throws IOException {
        List<Subscriber> subscribers = subscriberDataService.readSubscribers();
        for (Subscriber subscriber : subscribers) {
            if (subscriber.getPhoneNumber().equals(phoneNumber) && subscriber.isSubscribed()) {
                subscriber.setBalance(subscriber.getbalance() + operationValue);
                subscriber.setOperationValue(operationValue);
                break;
            }
        }
        subscriberDataService.writeSubscribers(subscribers);
    }

    public String processWebRiskResponse(EvaluateUriResponse response) {
        StringBuilder result = new StringBuilder();
        for (Score score : response.getScores()) {
            result.append("Threat Type: ").append(score.getThreatType())
                    .append(", Confidence Level: ").append(score.getConfidenceLevel()).append("\n");
        }
        return result.toString();
    }
}

//    @Autowired
//    private GoogleValidatorService validatorService;
//    public String sendSms(SMSData smsData) throws IOException {
//        System.out.println(smsData.getMessage());
//        String url = UrlExtractor.extractURLFromJSON(smsData.getMessage());
//        System.out.println(url);
//
//        try {
//            EvaluateRiskRequest riskRequest = new EvaluateRiskRequest(
//                    url,
//                    Arrays.asList("SOCIAL_ENGINEERING", "MALWARE"),
//                    true
//            );
//            WebRiskResponse response = webRiskClient.evaluateUri(riskRequest);
//            processWebRiskResponse(response);
//            return "URL został sprawdzony: " + response.toString();
//        } catch (IOException e) {
//            return "Wystąpił błąd podczas sprawdzania URL: " + e.getMessage();
//        }
//    }
//
//// Kontynuacja wysyłania SMS lub podejmowanie innych działań
//
//    public void processWebRiskResponse(WebRiskResponse response) {
//        for (WebRiskResponse.Score score : response.getScores()) {
//            if ("HIGH".equals(score.getConfidenceLevel()) || "VERY_HIGH".equals(score.getConfidenceLevel())) {
//                System.out.println("URL jest potencjalnie niebezpieczny: " + score.getThreatType());
//                // Możesz podjąć dalsze działania, takie jak ostrzeżenie użytkownika, zablokowanie URL-a itp.
//            }
//        }
//    }

//        try {
//            validatorService.validate(url);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//}
