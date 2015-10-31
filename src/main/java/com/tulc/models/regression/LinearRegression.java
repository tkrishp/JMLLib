package com.tulc.models.regression;

import java.util.Vector;

import com.tulc.models.base.BaseModel;
import com.tulc.math.Matrix;

public class LinearRegression<E extends Number, F extends Number> extends BaseModel<E, F> {
    public LinearRegression(Matrix<E> X, Vector<F> y) {
        super(X, y);
    }
}