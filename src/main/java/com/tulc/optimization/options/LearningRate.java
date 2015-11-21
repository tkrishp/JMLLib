package com.tulc.optimization.options;

public enum LearningRate {
    ADAPTIVE("ADAPTIVE"),
    FIXED("FIXED");
    
    private String learningRate = "";
    LearningRate(String lr) {
        learningRate = lr;
    }
    
    public String getLearningRateType() {
        return learningRate;
    }
}
