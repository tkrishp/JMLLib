package com.tulc.metrics.util;

import java.io.IOException;
import java.util.Vector;

public class Metric {
    public static double MSE(Vector<Double> yActual, Vector<Double> yPred) throws IOException {
        double mse = 0.0;
        Vector<Double> ya = yActual;
        Vector<Double> yp = yPred;
        
        if (yActual.size() != yPred.size())
            throw new IOException("Vectors are unequal size");
        
        for (int i = 0; i < ya.size(); i++) {
            mse += (ya.get(i) - yp.get(i)) * (ya.get(i) - yp.get(i));
        }
        return (mse/ya.size());
    }
    
    public static double RMSE(Vector<Double> yActual, Vector<Double> yPred) throws IOException {
        return Math.sqrt(MSE(yActual, yPred));
    }
}
