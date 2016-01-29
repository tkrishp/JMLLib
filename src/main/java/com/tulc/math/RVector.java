package com.tulc.math;

import java.util.Vector;

public class RVector extends Vector<Double> {
    /**
     * 
     *
    **/
    private static final long serialVersionUID = -4506477794064248264L;
    
    public RVector() {
        super();
    }
    
    public RVector(int r) {
        super(r);
    }
    
    /**
     * Returns the p-norm of the vector. 
     * if p=1, Manahatten distance is returned (L1/Lasso)
     * if p=2, Euclidean distance is returned (L2/Ridge)
     * @param p p-norm of the vector
     * @return norm of the vector
     */
    public Double pNorm(int p) {
        Double d = 0d;
        for (int i = 0; i < this.capacity(); i++) {
            d += Math.pow(this.get(i), p);
        }
        return Math.pow(d, (1/p));
    }   
}
