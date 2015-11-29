package com.tulc.optimization;

import com.tulc.math.Matrix;
import com.tulc.math.RVector;

/* 
 * An abstract class for functions that can have a derivative
 * A function's derivative is its gradient that is used in computing
 * loss. Any class that extends function must implement its first
 * derivative
 */
public abstract class Function {
    protected double gradient = 0d;
    abstract public RVector gradient(Matrix X, RVector y, RVector theeta) throws Exception;
}