package com.tulc.math;

import java.io.IOException;
import java.util.Vector;

public class MatrixUtil {
    public static Double dotProduct(Vector<Double> a, Vector<Double> b) throws IOException {
        Double ret = new Double(0.0);
        if (a.capacity() != b.capacity())
            throw new IOException("Cannot perform scalar multiplication on vectors of different sizes");
        for (int i = 0; i < a.capacity(); i++) {
            ret += a.get(i) * b.get(i);
        }
        return ret;
    }
    
    public static Vector<Double> eleWiseMultiply(Vector<Double> a, Vector<Double> b) throws IOException {
        if (a.capacity() != b.capacity()) {
            throw new IOException("Cannot perform element-wise multiplication on vectors of different sizes");
        }
        Vector<Double> ret = new Vector<Double>(a.capacity());
        for(int i = 0; i < a.capacity(); i++) {
            ret.insertElementAt(a.get(i) * b.get(i), i);
        }
        return ret;
    }
    
    public static Vector<Double> subtract(Vector<Double> a, Vector<Double> b) throws IOException {
        if (a.capacity() != b.capacity()) {
            throw new IOException("Cannot perform element-wise subtraction on vectors of different sizes");
        }
        Vector<Double> ret = new Vector<Double>(a.capacity());
        for (int i = 0; i < ret.capacity(); i++) {
            ret.insertElementAt((a.get(i) - b.get(i)), i);
        }
        return ret;
    }
    
    public static Vector<Double> getUnitVector(int m) {
        Vector<Double> uv = new Vector<Double>(m);
        for (int i = 0; i < m; i++) {
            uv.set(i, 1d);
        }
        return uv;
    }
    
    public static Vector<Double> scaleVector(double scale, Vector<Double> a) {
        Vector<Double> ret = new Vector<Double>(a.capacity());
        for (int i = 0; i < ret.capacity(); i++) {
            ret.insertElementAt(a.get(i) * scale, i);
        }
        return ret;
    }
}