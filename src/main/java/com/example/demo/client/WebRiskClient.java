package com.example.demo.client;

import com.example.demo.model.EvaluateRiskRequest;
import com.example.demo.model.EvaluateUriResponse;
import com.example.demo.model.WebRiskResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

// ...

@Component
public class WebRiskClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String apiKey; // Klucz API

    public WebRiskClient(@Value("${webrisk.api.key}") String apiKey) {
        this.apiKey = apiKey;
    }

    public EvaluateUriResponse evaluateUri(EvaluateRiskRequest request) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(request);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        String url = "https://webrisk.googleapis.com/v1eap1:evaluateUri?key=" + this.apiKey;

        String jsonResponse = restTemplate.postForObject(url, entity, String.class);
        return objectMapper.readValue(jsonResponse, EvaluateUriResponse.class);
    }
}

//    public WebRiskResponse evaluateUri(EvaluateRiskRequest request) throws IOException {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String requestBody = objectMapper.writeValueAsString(request);
//
//        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
//        String url = "https://webrisk.googleapis.com/v1eap1:evaluateUri?key=" + this.apiKey;
//
//        String jsonResponse = restTemplate.postForObject(url, entity, String.class);
//        return objectMapper.readValue(jsonResponse, WebRiskResponse.class);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        String requestBody = "{"
//                + "\"uri\": \"" + uri + "\","
//                + "\"threatTypes\": [\"SOCIAL_ENGINEERING\", \"MALWARE\"],"
//                + "\"allowScan\": true"
//                + "}";
//
//        System.out.println(requestBody);
//
//        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
//
//        String url = "https://webrisk.googleapis.com/v1eap1:evaluateUri?key=" + this.apiKey;
//        String jsonResponse = restTemplate.postForObject(url, request, String.class);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.readValue(jsonResponse, WebRiskResponse.class);

