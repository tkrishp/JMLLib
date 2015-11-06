package com.tulc.optimization;

import java.io.IOException;
import java.util.Vector;

import com.tulc.math.Matrix;

public class SGD extends GradientDescent {
    public SGD(Double iniTheeta, Matrix X, Vector<Double> y) throws IOException {
        super();
        GradientDescentOptions gdo = new GradientDescentOptions();
        gdo.setNumOfIter(1);
        super.X = new Matrix(1, X.numOfCols());
        super.y = new Vector<Double>(1);
        for (int i = 0; i < X.numOfRows(); i++) {
            super.y.insertElementAt(y.get(i), 0);
            for (int j = 0; j < X.numOfCols(); j++) {
                super.X.insert(X.get(i, j), 1, j);
            }
            optimize();
        }
    }
}