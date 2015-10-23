package com.tulc.optimization;

public enum OptAlgorithm {
    GRADIENT_DESCENT("GD"),
    STOCHASTIC_GRADIENT_DESCENT("SGD"),
    MINI_BATCH_GRADIENT_DESCENT("MBGD"),
    CONJUGATE_GRADIENT_DESCENT("CGD"),
    BFGS("BFGS");
    
    private String algName = "";
    OptAlgorithm(String algName) {
        this.algName = algName;
    }
    
    public String getOptAlgorithmName() {
        return algName;
    }
}