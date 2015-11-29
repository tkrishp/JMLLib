package com.tulc.models.classification;

import com.tulc.models.base.BaseModel;
import com.tulc.optimization.options.OptAlgorithm;
import com.tulc.data.Dataset;
import com.tulc.math.RVector;

public class LogisiticRegression extends BaseModel {

    public LogisiticRegression(Dataset X, RVector y) {
        super(X, y, OptAlgorithm.GRADIENT_DESCENT);
    }

}