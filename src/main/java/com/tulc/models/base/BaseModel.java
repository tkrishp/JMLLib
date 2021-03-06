package com.tulc.models.base;

import java.io.IOException;
import java.util.Vector;

import com.tulc.data.Dataset;
import com.tulc.math.MatrixUtil;
import com.tulc.math.RVector;
import com.tulc.optimization.GradientDescent;
import com.tulc.optimization.GradientDescentOptions;
import com.tulc.optimization.SGD;
import com.tulc.optimization.options.OptAlgorithm;

@SuppressWarnings("rawtypes")
public class BaseModel {
    protected Dataset X;
    protected RVector y;
    protected RVector theeta;
    protected Dataset train_X;
    protected Dataset test_X;
    protected RVector train_y;
    protected RVector test_y;
    protected OptAlgorithm optAlg;
    protected GradientDescent gd = null;
    
    public BaseModel(Dataset x, RVector y, OptAlgorithm oa) {
        this.X = x;
        this.y = y;
        this.train_X = x;
        this.train_y = y;
        this.test_X = x;
        this.test_y = y;
        this.optAlg = oa;
    }
    /*
    @SuppressWarnings("unchecked")
    public void split(Double trainRatio) throws IOException {
        int splitPoint = (int) (X.numOfRows() * trainRatio);
        train_X = X.subsetRows(1, splitPoint);
        train_y = y.subList(1, splitPoint);
        
        test_X = X.subsetRows(splitPoint + 1, X.numOfRows());
        test_y = y.subList(splitPoint + 1, y.size());
    }
    */
    public RVector train() throws Exception {
        GradientDescentOptions gdo = new GradientDescentOptions();
        if (optAlg.getOptAlgorithmName().equals(OptAlgorithm.GRADIENT_DESCENT)) {
            gdo.setNumOfIter(1000000);
            gdo.setMseGain(0.00001);
            gd = new GradientDescent(0.01, train_X, train_y, gdo);
        }
        else if (optAlg.getOptAlgorithmName().equals(OptAlgorithm.STOCHASTIC_GRADIENT_DESCENT)) {
            gdo.setNumOfIter(1);
            gdo.setMseGain(0.00001);
            gd = new SGD(0.01, train_X, train_y, gdo);
        }
        
        theeta = gd.getTheeta();
        return theeta;
    }
    
    public RVector getTheeta() {
        return theeta;
    }
    
    public RVector predict(Dataset X) throws IOException {
        return X.multiply(theeta);
    }
}
