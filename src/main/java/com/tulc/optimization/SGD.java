package com.tulc.optimization;

import java.util.Vector;

import com.tulc.math.Matrix;

public class SGD extends GradientDescent {
    public SGD(Double iniTheeta, Matrix<Double> X, Vector<Double> y, Double mseGain) {
        super(iniTheeta, X, y, 1, mseGain);
    }
}