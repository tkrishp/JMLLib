package com.tulc.models.classification;

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
    protected ArrayList<Vector<Integer>> X;
    protected ArrayList<Integer> y;
    protected RVector theeta;
    protected ArrayList<Vector<Integer>> train_X;
    protected ArrayList<Vector<Integer>> test_X;
    protected ArrayList<Integer> train_y;
    protected ArrayList<Integer> test_y;
    private HashMap<Integer, Double> classProb;
    
    public NaiveBayesClassifier(ArrayList<Vector<Integer>> x, ArrayList<Integer> y) {
        this.X = x;
        this.y = y;
        this.train_X = x;
        this.train_y = y;
        this.test_X = x;
        this.test_y = y;
        this.classProb = new HashMap<Integer, Double>();
    }
}
