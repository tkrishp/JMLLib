package com.tulc.optimization;

import java.io.IOException;
import com.tulc.data.Dataset;
import com.tulc.math.MatrixUtil;
import com.tulc.math.RVector;
import com.tulc.metrics.util.Metric;
import com.tulc.optimization.options.LearningRate;
import com.tulc.optimization.options.Regularization;

/* 
 * Class that implements GradientDescent
 * test more
 */
public class GradientDescent {
    protected RVector theeta;
    protected RVector y;
    protected RVector yhat;
    protected Dataset X;
    protected Double mse;
    protected boolean checkNumOfIter;
    protected boolean checkMseGain;
    protected GradientDescentOptions gdOptions;
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
     * 
     * step = (alpla/m) * gradient
     * L1: theeta = theeta - step + (lambda/m)*||theeta||
     * L2: theeta = theeta - step + (lambda/m)*||theeta||(2)
     * elastic net: theeta = theeta - step + (lambda1/m)*||theeta|| + (lambda2/m)*||theeta||(2)
     */
    public GradientDescent(Double iniTheeta, Dataset dataSet, RVector respVec, GradientDescentOptions gdo) 
            throws Exception {
        costFunction = gdo.getCostFunction();
        if (gdo.isInterceptSet()) {
            X = new Dataset(dataSet);
            X.insertFeatureVector(MatrixUtil.getUnitVector(dataSet.numOfRows()), 0);
        }
        else {
            X = new Dataset(dataSet);
        }
        theeta = new RVector(X.numOfCols());
        for (int i = 0; i < theeta.capacity(); i++) {
            theeta.add(iniTheeta);
        }
        y = respVec;
        gdOptions = gdo;
        mse = (double) 0;
        yhat = new RVector(X.numOfRows());
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
        RVector step = new RVector(X.numOfCols());
        int i = 0;
        Double regularizationFac = 0d;
        Double alpha = 0d;
        RVector prevTheeta = new RVector(theeta.capacity());
        do {
            prevTheeta = theeta;
            prevMse = mse;
            // take one step in the direction of the gradient
            step = MatrixUtil.scaleVector(
                    alpha/X.numOfRows(), 
                    costFunction.gradient(X, y, theeta));
            
            if (gdOptions.getPenalty() == Regularization.L1) {
                regularizationFac = (gdOptions.getLambda1()/X.numOfRows()) * theeta.pNorm(1);
            }
            else if (gdOptions.getPenalty() == Regularization.L2) {
                regularizationFac = (gdOptions.getLambda2()/X.numOfRows()) * theeta.pNorm(2);
            }
            // update theeta
            theeta = MatrixUtil.incrElements(MatrixUtil.subtract(theeta, step), regularizationFac);
            // regularization is not applied to the intercept term
            if (gdOptions.isInterceptSet()) {
                theeta.set(0, MatrixUtil.subtract(theeta, step).get(0));
            }
            yhat = X.multiply(theeta);
            mse = Metric.MSE(y, yhat);
            if (i > 0) {
                if (gdOptions.getLearningRateType() == LearningRate.ADAPTIVE) {
                    if (mse < prevMse) {
                        alpha = 1.05 * alpha;
                    }
                    else {
                        theeta = prevTheeta;
                        alpha = 0.5 * alpha;
                    }
                }
                else {
                    alpha = gdOptions.getLearningRate();
                }
            }
        } while (
            (checkNumOfIter ? (i++ < gdOptions.getNumOfIter()) : true) && 
            (checkMseGain ? (Math.abs(prevMse - mse) < gdOptions.getMseGain()) : true)
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
