package com.tulc.optimization;

import java.io.IOException;
import java.util.Vector;

import com.tulc.math.Matrix;

public class MiniBatchSGD extends GradientDescent {
    public MiniBatchSGD(Double iniTheeta, Matrix<Double> X, Vector<Double> y, GradientDescentOptions gdo) throws IOException {
        super(iniTheeta, X, y, gdo);
    }
}