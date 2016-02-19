package com.tulc.models.classification;

import java.util.HashMap;

import com.tulc.data.Dataset;
import com.tulc.math.RVector;

/**
 * @author tulasi
 * 
 * Model for Naive Bayes Classifier that estimates the posterior probability of a class using
 * Naive Bayes methodology by considering all features to be mutually independent
 * P(y = class(k) | X1, X2, ... , Xp) = [P(y=1)/P(X1, X2, ... , Xp)] * [P(X1 | y = class(k)) * P(X2 | y=1) * ... * P(Xp | y=1)]
 *
 * Dataset is a set of features with categorical variables. As part of model training, probability
 * of each feature-value combination w.r.t to y-class is computed. These trained probabilities are
 * used in predictions.
 * 
 */
public class NaiveBayesClassifier {
    protected Dataset X;
    protected RVector y;
    protected RVector theeta;
    protected Dataset train_X;
    protected Dataset test_X;
    protected RVector train_y;
    protected RVector test_y;
    private HashMap<Integer, Double> classProb;
    
    public NaiveBayesClassifier(Dataset x, RVector y) {
        this.X = x;
        this.y = y;
        this.train_X = x;
        this.train_y = y;
        this.test_X = x;
        this.test_y = y;
        this.classProb = new HashMap<Integer, Double>();
    }
    
    public void train() {
    	
    	return;
    }
     
}
