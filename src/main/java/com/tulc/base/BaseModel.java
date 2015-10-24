package com.tulc.base;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Vector;

import com.tulc.math.Matrix;
import com.tulc.math.MatrixUtils;
import com.tulc.optimization.GradientDescent;
import com.tulc.optimization.GradientDescentOptions;

@SuppressWarnings("rawtypes")
public class BaseModel<E extends Number> {
    private Matrix X;
    private Vector y;
    private Vector theeta;
    private Matrix<E> train_X;
    private Matrix<E> test_X;
    private Vector<E> train_y;
    private Vector<E> test_y;
    
    public BaseModel(Matrix X, Vector y) {
        this.X = X;
        this.y = y;
        this.train_X = X;
        this.train_y = y;
        this.test_X = X;
        this.test_y = y;
    }
    
    @SuppressWarnings("unchecked")
    public void split(Double trainRatio) throws IOException {
        int splitPoint = (int) (X.numOfRows() * trainRatio);
        train_X = X.subsetRows(1, splitPoint);
        train_y = (Vector) y.subList(1, splitPoint);
        
        test_X = X.subsetRows(splitPoint + 1, X.numOfRows());
        test_y = (Vector) y.subList(splitPoint + 1, y.size());
        
    }
    
    public void train() throws IOException {
        GradientDescentOptions gdo = new GradientDescentOptions();
        gdo.setNumOfIter(1000000);
        gdo.setMseGain(0.00001);
        GradientDescent gd = new GradientDescent(0.01, train_X, train_y, gdo);
        theeta = gd.getTheeta();
    }
    
    public Vector getTheeta() {
        return theeta;
    }
    
    public Vector predict(Matrix X) throws IOException {
        Vector<Double> pred_Y = new Vector<Double>(X.numOfRows());
        MatrixUtils<Double> mu = new MatrixUtils<Double>();
        for (int i = 0; i < X.numOfRows(); i++) {
            pred_Y.add(i, (Double) mu.multiply(pred_Y, theeta));
        }
        return pred_Y;
    }
}
