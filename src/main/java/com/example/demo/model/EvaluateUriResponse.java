package com.example.demo.model;

import java.util.List;

public class EvaluateUriResponse {
    private List<Score> scores;

    public EvaluateUriResponse(List<Score> scores) {
        this.scores = scores;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }
}