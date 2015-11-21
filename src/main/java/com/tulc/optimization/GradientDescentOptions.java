package com.tulc.optimization;

public class GradientDescentOptions {
    private final Integer DEF_NUM_ITER = 1;
    private final Double DEF_MSE_GAIN = 0.01;
    private final Double DEF_LEARN_RATE = 0.01;
    private final Double DEF_THEETA = 0.01;
    private final String L1_PENALTY = "L1";
    private final String L2_PENALTY = "L2";
    private final String DEF_PENALTY = L2_PENALTY;
    
    private Integer numOfIter;
    private double mseGain;
    private double alpha;
    private double iniTheeta;
    private String penalty;
    
    public GradientDescentOptions() {
        numOfIter = DEF_NUM_ITER;
        mseGain = DEF_MSE_GAIN;
        alpha = DEF_LEARN_RATE;
        iniTheeta = DEF_THEETA;
        penalty = DEF_PENALTY;
    }
    
    public void setNumOfIter(Integer n) {
        numOfIter = n;
    }
    
    public Integer getNumOfIter() {
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
    
    public void setIniTheeta(double d) {
        iniTheeta = d;
    }
    
    public double getIniTheeta() {
        return iniTheeta;
    }
    
    public void setPenalty(String p) {
        penalty = p;
    }
    
    public String getPenalty() {
        return penalty;
    }
}

