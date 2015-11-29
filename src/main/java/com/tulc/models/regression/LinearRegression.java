package com.tulc.models.regression;

import com.tulc.models.base.BaseModel;
import com.tulc.optimization.options.OptAlgorithm;
import com.tulc.data.Dataset;
import com.tulc.math.RVector;

public class LinearRegression extends BaseModel {
    public LinearRegression(Dataset X, RVector y) {
        super(X, y, OptAlgorithm.GRADIENT_DESCENT);
    }
}