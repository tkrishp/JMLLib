package com.tulc.models.regression;

import java.util.Vector;

import com.tulc.models.base.BaseModel;
import com.tulc.math.Matrix;

public class LinearRegression<E extends Number> extends BaseModel<E> {
    public LinearRegression(Matrix<E> X, Vector<E> y) {
        super(X, y);
    }
}