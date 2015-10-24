package com.tulc.optimization;

import java.io.IOException;
import java.util.Vector;

import com.tulc.math.Matrix;

public class SGD <E extends Number> extends GradientDescent<E> {
    public SGD(Double iniTheeta, Matrix<E> X, Vector<E> y) throws IOException {
        super();
        GradientDescentOptions gdo = new GradientDescentOptions();
        gdo.setNumOfIter(1);
    }
}