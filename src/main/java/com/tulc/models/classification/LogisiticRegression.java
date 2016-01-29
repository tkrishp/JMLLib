package com.tulc.models.classification;

import com.tulc.models.base.BaseModel;
import com.tulc.optimization.options.OptAlgorithm;

import java.io.IOException;
import java.util.Vector;

import com.tulc.data.Dataset;
import com.tulc.math.MatrixUtil;
import com.tulc.math.RVector;

public class LogisiticRegression extends BaseModel {

    public LogisiticRegression(Dataset X, RVector y) {
        super(X, y, OptAlgorithm.GRADIENT_DESCENT);
    }
    
    public RVector predictClass(Dataset X, float cutOff) throws IOException {
        RVector pred_Y =  new RVector(X.numOfRows());
        RVector pred_class = new RVector(X.numOfRows());
        pred_Y = X.multiply(theeta);
        for (int i = 0; i < pred_Y.capacity(); i++) {
            if (pred_Y.get(i) > cutOff) {
                pred_class.insertElementAt(1.0, i);                
            }
            else {
                pred_class.insertElementAt(0.0, i);
            }
        }
        return pred_class;
    }
}