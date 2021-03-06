package com.tulc.optimization.loss;

import java.io.IOException;

import com.tulc.data.Dataset;
import com.tulc.math.RVector;

/* 
 * An abstract class for functions that can have a derivative
 * A function's derivative is its gradient that is used in computing
 * loss. Any class that extends function must implement its first
 * derivative
 */
public abstract class LossFunction {
    protected double gradient = 0d;
    protected RVector loss;
    protected double mse = 0d;
    abstract public RVector gradient(Dataset X, RVector y, RVector theeta) throws IOException;
    abstract public void update(Dataset X, RVector y, RVector theeta) throws IOException;
}