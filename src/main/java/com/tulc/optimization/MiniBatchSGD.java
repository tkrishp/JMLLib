package com.tulc.optimization;

import com.tulc.data.Dataset;
import com.tulc.math.RVector;

public class MiniBatchSGD extends GradientDescent {
    public MiniBatchSGD(Double iniTheeta, Dataset inX, RVector iny, GradientDescentOptions inGDOpt, int batchSize) throws Exception {
        super(iniTheeta, inX, iny, inGDOpt);
        gdOptions.setNumOfIter(1);
        X = new Dataset(batchSize, X.numOfCols());
        y = new RVector(batchSize);
        int numOfBatches = inX.numOfRows() % batchSize;
        for (int i = 0; i < numOfBatches; i++) {
            for (int j = (i * batchSize); j < batchSize; j++) {
                y.insertElementAt(iny.get(j), 0);
                for (int k = 0; k < inX.numOfCols(); k++) {
                    X.insert(inX.get(j, k), 1, k);
                }
            }
            optimize();
        }
    }
}