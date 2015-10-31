import java.util.Vector;

import com.tulc.math.Matrix;
import com.tulc.models.regression.LinearRegression;

public class TestLinRegression {
    Matrix<Integer> X = new Matrix<Integer>(5, 2);
    Vector<Double> y = new Vector<Double>(5);
    
    LinearRegression<Integer, Double> lr = new LinearRegression<Integer, Double>(X, y);
}
