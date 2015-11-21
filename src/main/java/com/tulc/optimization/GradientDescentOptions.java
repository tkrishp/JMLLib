package com.tulc.optimization;

import com.tulc.optimization.options.Regularization;

public class GradientDescentOptions {
    private final Integer DEF_NUM_ITER = 1;
    private final Double DEF_MSE_GAIN = 0.01;
    private final Double DEF_LEARN_RATE = 0.01;
    private final Double DEF_THEETA = 0.01;
    private final Regularization DEF_PENALTY = Regularization.L2;
    private final boolean DEF_INTERCEPT = false;
    
    private Integer numOfIter;
    private double mseGain;
    private double alpha;
    private double iniTheeta;
    private Regularization penalty;
    private boolean intercept;
    
    public GradientDescentOptions() {
        numOfIter = DEF_NUM_ITER;
        mseGain = DEF_MSE_GAIN;
        alpha = DEF_LEARN_RATE;
        iniTheeta = DEF_THEETA;
        penalty = DEF_PENALTY;
        intercept = DEF_INTERCEPT;
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
    
    public void setPenalty(Regularization p) {
        penalty = p;
    }
    
    public Regularization getPenalty() {
        return penalty;
    }
    
    public void setIntercept(boolean b) {
        intercept = b;
    }
    
    public boolean isInterceptSet() {
        return intercept;
    }
}