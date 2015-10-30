package com.tulc.optimization;

import java.io.IOException;
import java.util.Vector;

import com.tulc.math.Matrix;

public class SGD <E extends Number> extends GradientDescent<E> {
    public SGD(Double iniTheeta, Matrix<E> X, Vector<E> y) throws IOException {
        super();
        GradientDescentOptions gdo = new GradientDescentOptions();
        gdo.setNumOfIter(1);
        Vector<E> row = new Vector<E>();
        for (int i = 0; i < X.numOfRows(); i++) {
            row = X.getRow(i);
            Matrix<E> rowMatrix = new Matrix<E>(1, X.numOfCols());
        }
    }
}