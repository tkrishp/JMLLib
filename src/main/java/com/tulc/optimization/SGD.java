package com.tulc.optimization;

import com.tulc.data.Dataset;
import com.tulc.math.RVector;

public class SGD extends GradientDescent {
    public SGD(Double iniTheeta, Dataset inX, RVector iny, GradientDescentOptions inGDOpt) throws Exception {
        super(iniTheeta, inX, iny, inGDOpt);
        gdOptions.setNumOfIter(1);
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