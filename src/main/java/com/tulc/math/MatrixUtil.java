package com.tulc.math;

import java.io.IOException;

public class MatrixUtil {
    public static Double dotProduct(RVector a, RVector b) throws IOException {
        Double ret = new Double(0.0);
        if (a.capacity() != b.capacity())
            throw new IOException("Cannot perform scalar multiplication on vectors of different sizes");
        for (int i = 0; i < a.capacity(); i++) {
            ret += a.get(i) * b.get(i);
        }
        return ret;
    }
    
    public static RVector eleWiseMultiply(RVector a, RVector b) throws IOException {
        if (a.capacity() != b.capacity()) {
            throw new IOException("Cannot perform element-wise multiplication on vectors of different sizes");
        }
        RVector ret = new RVector(a.capacity());
        for(int i = 0; i < a.capacity(); i++) {
            ret.insertElementAt(a.get(i) * b.get(i), i);
        }
        return ret;
    }
    
    public static RVector subtract(RVector a, RVector b) throws IOException {
        if (a.capacity() != b.capacity()) {
            throw new IOException("Cannot perform element-wise subtraction on vectors of different sizes");
        }
        RVector ret = new RVector(a.capacity());
        for (int i = 0; i < ret.capacity(); i++) {
            ret.insertElementAt((a.get(i) - b.get(i)), i);
        }
        return ret;
    }
    
    public static RVector add(RVector a, RVector b) throws IOException {
        if (a.capacity() != b.capacity()) {
            throw new IOException("Cannot perform element-wise subtraction on vectors of different sizes");
        }
        RVector ret = new RVector(a.capacity());
        for (int i = 0; i < ret.capacity(); i++) {
            ret.insertElementAt((a.get(i) + b.get(i)), i);
        }
        return ret;
    }
    
    public static RVector getUnitVector(int m) {
        RVector uv = new RVector(m);
        for (int i = 0; i < m; i++) {
            uv.set(i, 1d);
        }
        return uv;
    }
    
    public static RVector scaleVector(double scale, RVector a) {
        RVector ret = new RVector(a.capacity());
        for (int i = 0; i < ret.capacity(); i++) {
            ret.insertElementAt(a.get(i) * scale, i);
        }
        return ret;
    }
}