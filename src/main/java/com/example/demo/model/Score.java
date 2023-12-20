package com.example.demo.model;

import com.google.webrisk.v1.ThreatInfo;
import com.google.webrisk.v1.ThreatType;
import com.google.webrisk.v1.ThreatInfo.Confidence.ConfidenceLevel;


public class Score {
    private ThreatType threatType;
    private ConfidenceLevel confidenceLevel;

    // Konstruktory, gettery i settery

    public ThreatType getThreatType() {
        return threatType;
    }

    public void setThreatType(ThreatType threatType) {
        this.threatType = threatType;
    }

    public ThreatInfo.Confidence.ConfidenceLevel getConfidenceLevel() {
        return confidenceLevel;
    }

    public void setConfidenceLevel(ThreatInfo.Confidence.ConfidenceLevel confidenceLevel) {
        this.confidenceLevel = confidenceLevel;
    }
}
