package com.tulc.optimization;

import java.io.IOException;
import java.util.Vector;

import com.tulc.math.Matrix;

public class MiniBatchSGD extends GradientDescent {
    public MiniBatchSGD(Double iniTheeta, Matrix<Double> X, Vector<Double> y, int numOfIter, Double mseGain) throws IOException {
        super(iniTheeta, X, y, numOfIter, mseGain);
    }

    public MiniBatchSGD(Double iniTheeta, Matrix<Double> X, Vector<Double> y, int numOfIter) throws IOException {
        super(iniTheeta, X, y, numOfIter);
    }

    public MiniBatchSGD(Double iniTheeta, Matrix<Double> X, Vector<Double> y, Double mseGain) throws IOException {
        super(iniTheeta, X, y, mseGain);
    }
}