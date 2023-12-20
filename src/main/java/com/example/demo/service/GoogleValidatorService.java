package com.example.demo.service;

import com.example.demo.model.EvaluateRiskRequest;
import com.google.cloud.webrisk.v1.WebRiskServiceClient;
import com.google.webrisk.v1.SearchUrisRequest;
import com.google.webrisk.v1.SearchUrisResponse;
import com.google.webrisk.v1.ThreatType;
import java.io.IOException;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class GoogleValidatorService {
//
//    public void validate(String uri) throws IOException {
////
//
//        ThreatType threatType = ThreatType.MALWARE;
//
//        searchUri(uri, threatType);
//    }
//
//    public static void searchUri(String uri, ThreatType threatType) throws IOException {
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpEntity<EvaluateRiskRequest> request = new HttpEntity<>(new Foo("bar"));
//        Foo foo = restTemplate.postForObject(fooResourceUrl, request, Foo.class);
//        Assertions.assertNotNull(foo);
//        Assertions.assertEquals(foo.getName(), "bar");
//    }
}