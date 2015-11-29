package com.tulc.metrics.util;

import java.io.IOException;
import java.util.Vector;

import com.tulc.math.RVector;

public class Metric {
    public static double MSE(RVector yActual, RVector yPred) throws IOException {
        double mse = 0.0;
        RVector ya = yActual;
        RVector yp = yPred;
        
        if (yActual.size() != yPred.size())
            throw new IOException("Vectors are unequal size");
        
        for (int i = 0; i < ya.size(); i++) {
            mse += (ya.get(i) - yp.get(i)) * (ya.get(i) - yp.get(i));
        }
        return (mse/ya.size());
    }
    
    public static double RMSE(RVector yActual, RVector yPred) throws IOException {
        return Math.sqrt(MSE(yActual, yPred));
    }
}
