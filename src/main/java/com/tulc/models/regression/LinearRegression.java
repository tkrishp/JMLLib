package com.tulc.models.regression;

import java.util.Vector;

import com.tulc.models.base.BaseModel;
import com.tulc.math.Matrix;

public class LinearRegression extends BaseModel {
    public LinearRegression(Matrix X, Vector<Double> y) {
        super(X, y);
    }
}