package com.example.demo.model;

import java.util.List;

public class EvaluateRiskRequest {
    private String uri;
    private List<String> threatTypes;
    private boolean allowScan;

    public String getUri() {
        return uri;
    }

    public List<String> getThreatTypes() {
        return threatTypes;
    }

    public boolean isAllowScan() {
        return allowScan;
    }

    public EvaluateRiskRequest(String uri, List<String> threatTypes, boolean allowScan) {
        this.uri = uri;
        this.threatTypes = threatTypes;
        this.allowScan = allowScan;

    }
    @Override
    public String toString() {
        return "EvaluateRiskRequest{" +
                "uri='" + uri + '\'' +
                ", threatTypes=" + threatTypes +
                ", allowScan=" + allowScan +
                '}';
    }
}