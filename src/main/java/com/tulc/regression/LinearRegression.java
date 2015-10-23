package com.tulc.regression;

import java.util.Vector;

import com.tulc.base.BaseModel;
import com.tulc.math.Matrix;

public class LinearRegression extends BaseModel {

    @SuppressWarnings("rawtypes")
    public LinearRegression(Matrix X, Vector y) {
        super(X, y);
    }
}