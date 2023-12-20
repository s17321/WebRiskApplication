package com.example.demo.model;

import java.util.List;

public class WebRiskResponse {
    private List<Score> scores;

    public WebRiskResponse(List<Score> scores) {
        this.scores = scores;
    }

    public List<Score> getScores() {
        return scores;
    }
    // Gettery, settery i ewentualnie konstruktor

    public static class Score {
        private String threatType;
        private String confidenceLevel;

        public Score(String threatType, String confidenceLevel) {
            this.threatType = threatType;
            this.confidenceLevel = confidenceLevel;
        }

        public String getThreatType() {
            return threatType;
        }

        public String getConfidenceLevel() {
            return confidenceLevel;
        }
// Gettery, settery i ewentualnie konstruktor
    }
}
