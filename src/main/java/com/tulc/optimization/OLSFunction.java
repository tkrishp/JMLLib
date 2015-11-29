package com.tulc.optimization;

import java.io.IOException;

import com.tulc.math.Dataset;
import com.tulc.math.MatrixUtil;
import com.tulc.math.RVector;

/*
 * An implementation of the Ordinary Least Squares Function
 * This class has the implementation of the first derivative
 * of OLS
 */
public class OLSFunction extends Function {
    @Override
    public RVector gradient(Dataset X, RVector y, RVector theeta) throws IOException {
        RVector loss = MatrixUtil.subtract(X.multiply(theeta), y);
        return X.transpose().multiply(loss);
    }
}