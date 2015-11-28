package com.tulc.optimization;

import java.io.IOException;

import com.tulc.math.MatrixUtil;
import com.tulc.math.RVector;

/*
 * An implementation of the Ordinary Least Squares Function
 * This class has the implementation of the first derivative
 * of OLS
 */
public class OLSFunction extends Function {
    @Override
    public double firstDerivative(RVector row, RVector theeta) throws IOException {
        gradient = MatrixUtil.dotProduct(row, theeta);
        return gradient;
    }
}