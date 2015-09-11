package com.tulc.optimization;

import java.io.IOException;
import java.util.Random;
import java.util.Vector;

public class GDWrapper {
    public static void main(String[] args) {
        Vector<Vector<Double>> x = new Vector<Vector<Double>>();
        Vector<Double> y = new Vector<Double>();
        Vector<Double> theeta = new Vector<Double>();
        Integer numOfRows = 10000;
        Integer numOfFeatures = 50;
        Double iniTheeta = 1.0;
        Integer numOfIter = 100000;
        Double mseGain = 0.001;
        
        System.out.println("Generating test data...");
        for (int n=0; n<numOfRows; n++) {
            Vector<Double> row = new Vector<Double>();
            for (int m=0; m<numOfFeatures; m++) {
                if (m == 0) {
                    row.add(m, (double) 1);
                }
                else {
                    Random r = new Random();
                    row.add(m, 0.0 + (100.0 - 0.0) * r.nextDouble());
                }
            }
            y.add(n, 100.0 + (1000.0 - 100.0) * (new Random()).nextDouble());
            x.add(n, row);
        }
        
        System.out.println("Running gradient descent");
        GradientDescent gd = new GradientDescent(
                                iniTheeta, 
                                x, 
                                y, 
                                numOfIter,
                                mseGain
                               );
        
        try {
            gd.run();
            theeta = gd.getTheeta();
            System.out.println(gd.getTheeta());
            System.out.println("done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
