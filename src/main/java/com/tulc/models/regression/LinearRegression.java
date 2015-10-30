package com.tulc.models.regression;

import java.util.Vector;

import com.tulc.models.base.BaseModel;
import com.tulc.math.Matrix;

public class LinearRegression extends BaseModel {

    @SuppressWarnings("rawtypes")
    public LinearRegression(Matrix X, Vector y) {
        super(X, y);
    }
}