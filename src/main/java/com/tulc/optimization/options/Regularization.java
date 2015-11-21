package com.tulc.optimization.options;

public enum Regularization {
    L1("L1"),
    L2("L2");
    
    private String regularization = "";
    Regularization(String r) {
        regularization = r;
    }
    
    public String getRegularizationType() {
        return regularization;
    }
}