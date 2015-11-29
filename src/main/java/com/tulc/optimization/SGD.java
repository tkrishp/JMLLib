package com.tulc.optimization;

import java.io.IOException;
import java.util.Vector;

import com.tulc.math.Matrix;
import com.tulc.math.RVector;

public class SGD extends GradientDescent {
    public SGD(Double iniTheeta, Matrix inX, Vector<Double> iny, GradientDescentOptions inGDOpt) throws IOException {
        super();
        X = new Matrix(1, X.numOfCols());
        y = new RVector(1);
        for (int i = 0; i < inX.numOfRows(); i++) {
            y.insertElementAt(iny.get(i), 0);
            for (int j = 0; j < inX.numOfCols(); j++) {
                X.insert(inX.get(i, j), 1, j);
            }
            optimize();
        }
    }
}