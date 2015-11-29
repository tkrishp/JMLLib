package com.tulc.optimization;

import java.util.Vector;

import com.tulc.math.Dataset;
import com.tulc.math.RVector;

public class SGD extends GradientDescent {
    public SGD(Double iniTheeta, Dataset inX, Vector<Double> iny, GradientDescentOptions inGDOpt) throws Exception {
        super();
        X = new Dataset(1, X.numOfCols());
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