package com.tulc.models.regression;

import java.util.Vector;

import com.tulc.models.base.BaseModel;
import com.tulc.math.Matrix;

public class LinearRegression<E extends Number> extends BaseModel<E> {

    @SuppressWarnings("rawtypes")
    public LinearRegression(Matrix X, Vector y) {
        super(X, y);
    }
}