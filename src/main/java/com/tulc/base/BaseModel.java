package com.tulc.base;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Vector;

import com.tulc.math.Matrix;
import com.tulc.optimization.GradientDescent;

public class BaseModel {
	@SuppressWarnings("rawtypes")
	private Matrix X;
	private Vector y;
	private Vector theeta;
	private Matrix train_X;
	private Matrix test_X;
	private Vector train_y;
	private Vector test_y;
	
	public BaseModel(Matrix X, Vector y) {
		this.X = X;
		this.y = y;
		this.train_X = X;
		this.train_y = y;
		this.test_X = X;
		this.test_y = y;
	}
	
	public void split(Double trainRatio) throws IOException {
		int splitPoint = (int) (X.numOfRows() * trainRatio);
		train_X = X.subsetRows(1, splitPoint);
		train_y = (Vector) y.subList(1, splitPoint);
		
		test_X = X.subsetRows(splitPoint + 1, X.numOfRows());
		test_y = (Vector) y.subList(splitPoint + 1, y.size());
		
	}
	
	public void train() throws IOException {
		GradientDescent gd = new GradientDescent(0.01, train_X, train_y, 1000000, 0.00001);
		theeta = gd.optimize();
	}
	
	public Vector getTheeta() {
		return theeta;
	}
	
	public Vector predict(Matrix X) {
		Vector pred_Y = new Vector(X.numOfRows());
		
		return pred_Y;
	}
}
