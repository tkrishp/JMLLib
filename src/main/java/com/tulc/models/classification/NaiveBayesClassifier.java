package com.tulc.models.classification;

import com.tulc.data.Dataset;
import com.tulc.math.RVector;

public class NaiveBayesClassifier {
    protected Dataset X;
    protected RVector y;
    protected RVector theeta;
    protected Dataset train_X;
    protected Dataset test_X;
    protected RVector train_y;
    protected RVector test_y;
    
    public NaiveBayesClassifier(Dataset x, RVector y) {
        this.X = x;
        this.y = y;
        this.train_X = x;
        this.train_y = y;
        this.test_X = x;
        this.test_y = y;
    }
}
