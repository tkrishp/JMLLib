package com.tulc.models.classification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

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
    protected Vector<Vector<Integer>> X;
    protected Vector<Integer> y;
    protected RVector theeta;
    protected Vector<Vector<Integer>> train_X;
    protected Vector<Vector<Integer>> test_X;
    protected Vector<Integer> train_y;
    protected Vector<Integer> test_y;
    private HashMap<Integer, Double> classCount;
    
    public class FeatureResponseProbability {
    	Integer xIndex;
    	Integer xValue;
    	Integer yValue;
    	
    	public FeatureResponseProbability(Integer y, Integer idx, Integer x) {
    		this.xIndex = idx;
    		this.xValue = x;
    		this.yValue = y;
    	}
    	
    	public boolean equals(FeatureResponseProbability a) {
    		return this.yValue == a.yValue && this.xIndex == a.xIndex && this.xValue == a.xValue;
    	}
    	
    	public int hashCode() {
    		int hash = 1;
            hash = hash * 31 + this.xIndex;
            hash = hash * 31 + this.xValue;
            hash = hash * 31 + this.yValue;
            return hash;
    	}
    	
    	public String toString() {
    		return xIndex + "," + xValue + "," + yValue;
    	}
    }
    private HashMap<FeatureResponseProbability, Integer> featureRespCounts;
    private HashMap<FeatureResponseProbability, Double> featureRespProb;
    
    public NaiveBayesClassifier(Vector<Vector<Integer>> x, Vector<Integer> y) {
        this.X = x;
        this.y = y;
        this.train_X = x;
        this.train_y = y;
        this.test_X = x;
        this.test_y = y;
        this.classProb = new HashMap<Integer, Double>();
    }
    
    public void computeProb(Vector<Integer> feature, Integer featureIndex, Vector<Integer> y) throws IOException {
    	if (feature.size() != y.size())
    		throw new IOException("Feature vector at index [" + featureIndex + "] and y vectors are not of same size");
    	
    	for(int i = 0; i < feature.size(); i++) {
    		
    	}
    	
    	return;
    }
}
