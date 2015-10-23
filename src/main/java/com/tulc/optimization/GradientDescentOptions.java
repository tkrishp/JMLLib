package com.tulc.optimization;

public class GradientDescentOptions {
    private Integer numOfIter;
    private double mseGain;
    private double alpha;
    
    public void setNumOfIter(Integer n) {
        numOfIter = n;
    }
    
    public Integer numOfIter() {
        return numOfIter;
    }
    
    public void setMseGain(double d) {
        mseGain = d;
    }
    
    public double getMseGain() {
        return mseGain;
    }
    
    public void setLearningRate(double d) {
        alpha = d;
    }
    
    public double getLearningRate() {
        return alpha;
    }
}
