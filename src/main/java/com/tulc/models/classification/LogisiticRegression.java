package com.tulc.models.classification;

import java.util.Vector;

import com.tulc.models.base.BaseModel;
import com.tulc.optimization.OptAlgorithm;
import com.tulc.math.Matrix;

public class LogisiticRegression extends BaseModel {

    public LogisiticRegression(Matrix X, Vector y) {
        super(X, y, OptAlgorithm.GRADIENT_DESCENT);
    }

}
