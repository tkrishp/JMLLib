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
    
    class FeatureResponseMetrics {
    	Integer xIndex;
    	Integer xValue;
    	Integer yValue;
    	
    	FeatureResponseMetrics() {
    	}
    	
    	FeatureResponseMetrics(Integer idx, Integer x, Integer y) {
    		this.xIndex = idx;
    		this.xValue = x;
    		this.yValue = y;
    	}
    	
    	public void initialize(Integer idx, Integer x, Integer y) {
    		this.xIndex = idx;
    		this.xValue = x;
    		this.yValue = y;
    	}
    	
    	public boolean equals(FeatureResponseMetrics a) {
    		return this.yValue == a.yValue && this.xIndex == a.xIndex && this.xValue == a.xValue;
    	}
    	
    	public int hashCode() {
    		int hash = 1;
            hash = hash * 31 + this.xIndex;
            hash = hash * 31 + this.xValue;
            hash = hash * 31 + this.yValue;
            return hash;
    	}
    	
    	public Integer getXIndex() {
    		return xIndex;
    	}
    	
    	public Integer getXValue() {
    		return xValue;
    	}
    	
    	public Integer getYValue() {
    		return yValue;
    	}
    	
    	public String toString() {
    		return xIndex + "," + xValue + "," + yValue;
    	}
    }
    private HashMap<FeatureResponseMetrics, Integer> featureRespCounts;
    private HashMap<FeatureResponseMetrics, Double> featureRespProb;
    private HashMap<Integer, Integer> responseVecCounts;
    
    public NaiveBayesClassifier(Vector<Vector<Integer>> x, Vector<Integer> y) {
        this.X = x;
        this.y = y;
        this.train_X = x;
        this.train_y = y;
        this.test_X = x;
        this.test_y = y;
        this.featureRespCounts = new HashMap<FeatureResponseMetrics, Integer>();
        this.featureRespProb= new HashMap<FeatureResponseMetrics, Double>();
        this.responseVecCounts = new HashMap<Integer, Integer>();
    }
    
    public void computeResponseVecCounts(Vector<Integer> y) {
    	int newCount = 0;
    	int key = 0;
    	for(int i = 0; i < y.size(); i++) {
    		key = y.get(i);
    		if (responseVecCounts.containsKey(key)) {
    			newCount = responseVecCounts.get(key) + 1;
    			responseVecCounts.put(key, newCount);
    		}
    	}
    }
    
    /*
     * Convert the input dataset into a hashmap of y-value, x-value and probability
     * This exercise is performed for each feature
     */
    public void computeProb(Vector<Integer> feature, Integer featureIndex, Vector<Integer> y) throws IOException {
    	if (feature.size() != y.size())
    		throw new IOException("Feature vector at index [" + featureIndex + "] and y vectors are not of same size");
    	FeatureResponseMetrics inter = new FeatureResponseMetrics();
    	int newCount = 0;
    	for(int i = 0; i < feature.size(); i++) {
    		inter.initialize(featureIndex, feature.get(i), y.get(i));
    		if (featureRespCounts.containsKey(inter)) {
    			newCount = featureRespCounts.get(inter) + 1;
    			featureRespCounts.put(inter, newCount);
    		}
    		else {
    			featureRespCounts.put(inter, 1);
    		}
    		newCount = 0;
    	}
    	
    	for (FeatureResponseMetrics key : featureRespCounts.keySet()) {
    		featureRespProb.put(key, featureRespCounts.get(key) * 1.0/responseVecCounts.get(key.getYValue()));
    	}
    	
    	return;
    }
}
