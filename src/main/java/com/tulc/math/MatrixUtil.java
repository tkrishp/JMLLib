package com.tulc.math;

import java.io.IOException;
import java.util.Vector;

public class MatrixUtil {
    public static Double dotProduct(Vector<Double> a, Vector<Double> b) throws IOException {
        Double ret = new Double(0.0);
        if (a.size() != b.size())
            throw new IOException("Cannot perform scalar multiplication on vectors of different sizes");
        for (int i = 0; i < a.size(); i++) {
            ret += a.get(i) * b.get(i);
        }
        return ret;
    }
    
    public static Vector<Double> eleWiseMultiply(Vector<Double> a, Vector<Double> b) throws IOException {
        if (a.size() != b.size()) {
            throw new IOException("Cannot perform element-wise multiplication on vectors of different sizes");
        }
        Vector<Double> ret = new Vector<Double>(a.size());
        for(int i = 0; i < a.size(); i++) {
            ret.insertElementAt(a.get(i) * b.get(i), i);
        }
        return ret;
    }
    
    public static Vector<Double> subtract(Vector<Double> a, Vector<Double> b) throws IOException {
        if (a.size() != b.size()) {
            throw new IOException("Cannot perform element-wise subtraction on vectors of different sizes");
        }
        Vector<Double> ret = new Vector<Double>(a.capacity());
        for (int i = 0; i < ret.capacity(); i++) {
            ret.insertElementAt((a.get(i) - b.get(i)), i);
        }
        return ret;
    }
}