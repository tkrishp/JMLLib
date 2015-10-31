package com.tulc.optimization;

import java.io.IOException;
import java.util.Vector;

import com.tulc.math.Matrix;

public class MiniBatchSGD <E extends Number, F extends Number>extends GradientDescent<E, F> {
    public MiniBatchSGD(Double iniTheeta, Matrix<E> X, Vector<E> y, GradientDescentOptions gdo) throws IOException {
        super();
    }
}