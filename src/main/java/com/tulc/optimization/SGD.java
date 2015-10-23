package com.tulc.optimization;

import java.io.IOException;
import java.util.Vector;

import com.tulc.math.Matrix;

public class SGD extends GradientDescent {
    public SGD(Double iniTheeta, Matrix<Double> X, Vector<Double> y) throws IOException {
        super();
        GradientDescentOptions gdo = new GradientDescentOptions();
        gdo.setNumOfIter(1);
    }
}