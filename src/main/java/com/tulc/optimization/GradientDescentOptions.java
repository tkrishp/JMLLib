package com.tulc.optimization;

import java.io.IOException;

import com.tulc.optimization.options.LearningRate;
import com.tulc.optimization.options.Regularization;

public class GradientDescentOptions {
    private final double UNDEFINED = -1d;
    private final int DEF_NUM_ITER = 1;
    private final double DEF_MSE_GAIN = 0.01;
    private final double DEF_LEARN_RATE = 0.01;
    private final double DEF_THEETA = 0.01;
    private final boolean DEF_INTERCEPT = false;
    private final Regularization DEF_PENALTY = Regularization.NONE;
    private final double DEF_LAMBDA = UNDEFINED; // regularization parameter
    private final LearningRate DEF_LEARNING_RATE = LearningRate.FIXED;
    
    private int numOfIter;
    private double mseGain;
    private double iniTheeta;
    private boolean intercept;
    private Regularization penalty;
    private double lambda;
    private LearningRate learningRateType;
    private double alpha;
    
    
    public GradientDescentOptions() {
        numOfIter = DEF_NUM_ITER;
        mseGain = DEF_MSE_GAIN;
        iniTheeta = DEF_THEETA;
        intercept = DEF_INTERCEPT;
        penalty = DEF_PENALTY;
        lambda = DEF_LAMBDA;
        learningRateType = DEF_LEARNING_RATE;
        alpha = DEF_LEARN_RATE;
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
    
    public void setRegularizationParam(double d) throws IOException {
        if (penalty == Regularization.NONE) {
            throw new IOException("Regularization penalty must be defined before regularization parameter");
        }
        lambda = d;
    }
    
    public double getRegularizationParam() throws IOException {
        if (lambda == UNDEFINED) {
            throw new IOException("Regularization parameter is undefined");
        }
        return lambda;
    }
    
    public void setLearningRateType(LearningRate lr) {
        learningRateType = lr;
    }
    
    public LearningRate getLearningRateType() {
        return learningRateType;
    }
}