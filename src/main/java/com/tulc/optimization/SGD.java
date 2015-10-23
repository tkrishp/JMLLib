package com.tulc.optimization;

import java.io.IOException;
import java.util.Vector;

import com.tulc.math.Matrix;

public class SGD extends GradientDescent {
    public SGD(Double iniTheeta, Matrix<Double> X, Vector<Double> y, Double mseGain) throws IOException {
        super(iniTheeta, X, y, 1);
    }
}