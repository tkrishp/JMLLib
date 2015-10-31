package com.tulc.optimization;

import java.io.IOException;
import java.util.Vector;

import com.tulc.math.GenericTypeOp;
import com.tulc.math.Matrix;
import com.tulc.math.MatrixUtils;

/* 
 * Class that implements GradientDescent
 * test more
 */
public class GradientDescent<E extends Number, F extends Number> {
    protected Vector<Double> theeta;
    protected Vector<F> y;
    protected Matrix<E> X;
    protected Vector<Double> loss;
    protected int numOfIter = 0;
    protected Double mseGain = 0.0;
    protected Integer numOfRows;
    protected Integer numOfFeatures;
    protected Double mse;
    protected MatrixUtils<E> matUtil;
    protected boolean checkNumOfIter;
    protected boolean checkMseGain;
    
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
     * @throws IOException 
     */
    @SuppressWarnings("unchecked")
    public GradientDescent(Double iniTheeta, Matrix<E> X, Vector<F> y, GradientDescentOptions gdo) 
            throws IOException {
        matUtil = new MatrixUtils<E>();
        theeta = new Vector<Double>();
        this.theeta.setSize(X.numOfCols());
        for (int i=0; i<this.theeta.size(); i++) {
            this.theeta.set(i, iniTheeta);
        }
        this.X = X;
        this.y = y;
        this.numOfIter = gdo.getNumOfIter();
        this.mseGain = gdo.getMseGain();
        this.numOfRows = X.numOfRows();
        this.numOfFeatures = X.numOfCols();
        this.mse = (double) 0;
        
        this.checkNumOfIter = (numOfIter == -1) ? false : true;
        this.checkMseGain = (mseGain == -1) ? false : true;
        
        optimize();
     }

    /**
     * Run the gradient descent algorithm till threshold conditions are satisfied
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public Vector<Double> optimize() throws IOException {
        if (! (checkNumOfIter || checkMseGain))
            throw new IOException("Gradient descent must have a defined exit condition. " +
                    "Provide number of iterations or mse gain");
        Double gradient = new Double(0);
        Double alpha = 0.001;
        Double prevMse = (double) 0;
        Vector<Double> newTheeta = new Vector<Double>(numOfFeatures);
        Double step = (double) 0;
        
        int i = 0;
        do {
            computeLossAndMse();
            for (int m=0; m<numOfFeatures; m++) {
                gradient = (Double) sumOfElements(matUtil.multiply(X.transpose(), (Vector<E>)loss));
                step = (Double) GenericTypeOp.multiply(gradient, alpha)/numOfRows;
                newTheeta.set(m, GenericTypeOp.subtract(theeta.get(m), step));
                gradient = 0.0;
            }

            theeta = newTheeta;
            if (i > 0) {
                if ((mse - prevMse) < mseGain) {
                    return getTheeta();
                }
            }
            prevMse = mse;
            
        } while (
                (checkNumOfIter ? (i++ < numOfIter) : true) && 
                (checkMseGain ? ((mse - prevMse) > mseGain) : true)
            );
        
        return getTheeta();
    }
    
    /**
     * Compute loss and mean square error
     * loss is a vector of differences between actual and predicted values for each record in training set
     * @throws IOException
     */
    private void computeLossAndMse() throws IOException {
        Double yhat = new Double(0);
        loss = new Vector<Double>(numOfRows);
        Vector<E> row = new Vector<E>();
        Double currY = new Double(0);
        
        if (numOfFeatures != theeta.size())
            throw new IOException("Invalid dimensions for the vector theeta");
        
        for (int n=0; n<numOfRows; n++) {
            row = X.getRow(n);
            yhat = (Double) matUtil.multiply(row, theeta);
            currY = (Double) y.get(n);
            loss.set(n, (yhat - currY));
            mse += (yhat - currY) * (yhat - currY);
        }
        mse = mse/numOfRows;
    }
    
    /**
     * Returns the vector of theeta
     * @return
     */
    public Vector<Double> getTheeta() {
        return (Vector<Double>) theeta;
    }
    
    private Double sumOfElements(Vector<E> a) {
        Double ret = new Double(0.0);
        for (int i = 0; i < a.size(); i++) {
            ret += (Double) a.get(i);
        }
        return ret;
    }
}
