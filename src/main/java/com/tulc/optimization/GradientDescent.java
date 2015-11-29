package com.tulc.optimization;

import java.io.IOException;
import java.util.Vector;

import com.tulc.math.Matrix;
import com.tulc.math.MatrixUtil;
import com.tulc.math.RVector;
import com.tulc.metrics.util.Metric;
import com.tulc.optimization.options.Regularization;

/* 
 * Class that implements GradientDescent
 * test more
 */
public class GradientDescent {
    protected RVector theeta;
    protected RVector y;
    protected RVector yhat;
    protected Matrix X;
    protected Integer numOfRows;
    protected Integer numOfFeatures;
    protected Double mse;
    protected boolean checkNumOfIter;
    protected boolean checkMseGain;
    protected GradientDescentOptions gdOptions;
    protected Integer interceptIndex;
    protected Function costFunction;
    
    /*
     * For use by derived classes
     */
    public GradientDescent() {
    }
    
    /**
     * Initializes gradient descent algorithm with input parameters
     * 
     * @param iniTheeta initial value of theeta
     * @param x is the matrix that is to be optimized
     * @param y is the response variable vector
     * @param numOfIter is the number of iterations gradient descent is run
     * @param mseGain stop condition if the difference between mean squared error (mse) of current and previous 
     * iterations is less than this value
     * @throws Exception 
     */
    public GradientDescent(Double iniTheeta, Matrix dataSet, RVector respVec, GradientDescentOptions gdo) 
            throws Exception {
        costFunction = new OLSFunction();
        if (gdo.isInterceptSet()) {
            interceptIndex = dataSet.numOfCols();
            X = new Matrix(dataSet.numOfRows(), dataSet.numOfCols() + 1);
            X.copy(dataSet);
            X.setFeatureVector(interceptIndex, MatrixUtil.getUnitVector(dataSet.numOfRows()));
        }
        else {
            interceptIndex = -1;
            X = new Matrix(dataSet);
        }
        theeta = new RVector(X.numOfCols());
        for (int i = 0; i < theeta.capacity(); i++) {
            theeta.add(iniTheeta);
        }
        y = respVec;
        gdOptions = gdo;
        numOfRows = X.numOfRows();
        numOfFeatures = X.numOfCols();
        mse = (double) 0;
        yhat = new RVector(numOfRows);
        checkNumOfIter = (gdOptions.getNumOfIter() == -1) ? false : true;
        checkMseGain = (gdOptions.getMseGain() == -1) ? false : true;
        
        optimize();
     }

    /**
     * Run the gradient descent algorithm till threshold conditions are satisfied
     * @throws Exception 
     */
    public RVector optimize() throws Exception {
        if (! (checkNumOfIter || checkMseGain))
            throw new IOException("Gradient descent must have a defined exit condition. " +
                                  "Provide number of iterations or mse gain");
        Double prevMse = (double) 0;
        RVector step = new RVector(numOfFeatures);
        int i = 0;
        do {
            yhat = X.multiply(theeta);
            mse = Metric.MSE(y, yhat);
            if (i > 0) {
                if ((mse - prevMse) < gdOptions.getMseGain()) {
                    return getTheeta();
                }
            }
            // take one step in the direction of the gradient
            step = MatrixUtil.scaleVector(
                    gdOptions.getLearningRate()/numOfRows, 
                    costFunction.gradient(X, y, theeta));
            theeta = MatrixUtil.subtract(
                        (gdOptions.getPenalty() == Regularization.L1) ? 
                            MatrixUtil.scaleVector((1 - (gdOptions.getLearningRate()*gdOptions.getRegularizationParam()/X.numOfRows())), theeta) : 
                            theeta, 
                        step
                    );
            prevMse = mse;
        } while (
                (checkNumOfIter ? (i++ < gdOptions.getNumOfIter()) : true) && 
                (checkMseGain ? ((mse - prevMse) > gdOptions.getMseGain()) : true)
            );
        return theeta;
    }

    /**
     * Returns the vector of theeta
     * @return
     */
    public RVector getTheeta() {
        return theeta;
    }
}
