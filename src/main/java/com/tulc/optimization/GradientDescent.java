package com.tulc.optimization;

import java.io.IOException;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tulc.math.Matrix;
import com.tulc.math.MatrixUtils;

/* 
 * Class that implements GradientDescent
 * test more
 */
public class GradientDescent {
    private Vector<Double> theeta;
    private Vector<Double> y;
    private Matrix<Double> x;
    private Vector<Double> loss;
    private int numOfIter;
    private Double mseGain;
    private Integer numOfRows;
    private Integer numOfFeatures;
    private Double mse;
    private MatrixUtils<Double> matUtil;
    private Logger logger;
    
    /**
     * Initializes the gradient descent
     * 
     * @param iniTheeta initial value of theeta
     * @param x is the matrix that is to be optimized
     * @param y is the response variable vector
     * @param numOfIter is the number of iterations gradient descent is run
     * @param mseGain stop condition if the difference between mean squared error (mse) of current and previous 
     * iterations is less than this value
     */
    public GradientDescent(Double iniTheeta, Matrix<Double> x, Vector<Double> y, int numOfIter, Double mseGain) {
    	logger = LoggerFactory.getLogger(GradientDescent.class);
    	matUtil = new MatrixUtils<Double>();
    	theeta = new Vector<Double>();
        this.theeta.setSize(x.numOfCols());
        logger.info("theeta size: " + this.theeta.size());
        for (int i=0; i<this.theeta.size(); i++) {
            this.theeta.set(i, iniTheeta);
        }
        this.x = x;
        this.y = y;
        this.numOfIter = numOfIter;
        this.mseGain = mseGain;
        this.numOfRows = x.numOfRows();
        this.numOfFeatures = x.numOfCols();
        this.mse = (double) 0;
        
        logger.info("Parameters: " +
                    "[# of rows: " + numOfRows + "], " + 
                    "[# of features: " + numOfFeatures + "], " +
                    "[initial theeta: " + iniTheeta + "], " 
            );
     }
    
    /**
     * Run the gradient descrent algorithm till threshold conditions are satisfied
     * @throws IOException
     */
    public Vector<Double> optimize() throws IOException {
        Double gradient = new Double(0);
        Double alpha = 0.001;
        Vector<Double> newTheeta = new Vector<Double>();
        Double prevMse = (double) 0;
        newTheeta = new Vector<Double>(numOfFeatures);
        Double step = (double) 0;
        
        for (int i=0; i<numOfIter; i++) {
            computeLossAndMse();
            for (int m=0; m<numOfFeatures; m++) {
            	gradient = sumOfElements(matUtil.multiply(x.transpose(), loss));
                step = gradient * alpha/numOfRows;
                newTheeta.set(m, (theeta.get(m) - step));
                logger.debug("feature[" + m + "]" + ", " + 
                            "gradient: " + gradient + ", " + 
                            "step: " + step + ", " +
                            "newtheeta: " + newTheeta.get(m) + ", " +
                            "oldtheeta: " + theeta.get(m));
                gradient = (double) 0;
            }

            theeta = newTheeta;
            if (i > 0) {
                if ((mse - prevMse) < mseGain) {
                    logger.debug("curr mse: " + mse + ", prev mse: " + prevMse + 
                    			 ", mse diff: " + (mse - prevMse) + ", cutoff: " + mseGain );
                    return getTheeta();
                }
            }
            logger.debug((i+1) + "," + mse + ", prev mse: " + prevMse + ", mse diff: " + (mse - prevMse));
            prevMse = mse;
            
        }
        
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

        Double currY = new Double(0);
        
        if (numOfFeatures != theeta.size()) {
            logger.debug("# of features: " + numOfFeatures);
            logger.debug("size of theeta: " + theeta.size());
            throw new IOException();
        }
        
        for (int n=0; n<numOfRows; n++) {
            Vector<Double> row = x.getRow(n);
            yhat = matUtil.multiply(row, theeta);
            currY = y.get(n);
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
        return theeta;
    }
    
    private Double sumOfElements(Vector<Double> a) {
    	Double ret = (double) 0.0;
    	for (int i = 0; i < a.size(); i++) {
    		ret += a.get(i);
    	}
    	return ret;
    }
}
