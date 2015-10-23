package com.tulc.math;

import java.io.IOException;
import java.util.Vector;

public class MatrixUtils<E extends Number> {
    public Matrix<E> multiply(Matrix<E> a, Matrix<E> b) throws IOException {
        return a.multiply(b);
    }
    
    public E multiply(Vector<E> a, Vector<E> b) throws IOException {
        Matrix<E> m1 = new Matrix<E>(1, a.size());
        Matrix<E> m2 = new Matrix<E>(b.size(), 1);
        return m1.multiply(m2).get(0, 0);
    }
    
    public Vector<E> multiply(Matrix<E> a, Vector<E> b) throws IOException {
        return a.multiply(new Matrix<E>(b.size(), 1)).getColumn(0);
    }
}
