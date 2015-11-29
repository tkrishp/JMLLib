package com.tulc.models.regression;

import com.tulc.models.base.BaseModel;
import com.tulc.optimization.options.OptAlgorithm;
import com.tulc.math.Matrix;
import com.tulc.math.RVector;

public class LinearRegression extends BaseModel {
    public LinearRegression(Matrix X, RVector y) {
        super(X, y, OptAlgorithm.GRADIENT_DESCENT);
    }
}