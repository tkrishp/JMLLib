package com.tulc.optimization.loss;

import java.io.IOException;

import com.tulc.data.Dataset;
import com.tulc.math.MatrixUtil;
import com.tulc.math.RVector;
import com.tulc.metrics.util.Metric;

/*
 * An implementation of the Ordinary Least Squares Function
 * This class has the implementation of the first derivative
 * of OLS
 */
public class LogLossFunction extends LossFunction {
    @Override
    public RVector gradient(Dataset X, RVector y, RVector theeta) throws IOException {
        return X.transpose().multiply(loss);
    }
    
    @Override
    public void update(Dataset X, RVector y, RVector theeta) throws IOException {
        loss = MatrixUtil.subtract(
                  MatrixUtil.divElements(1, 
                  MatrixUtil.incrElements(
                  MatrixUtil.powerE(
                  MatrixUtil.scaleVector(-1, X.multiply(theeta))), 1)),
                y);
        mse = Metric.MSE(y, X.multiply(theeta));
    }
}