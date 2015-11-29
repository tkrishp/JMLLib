package com.tulc.data;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tulc.data.Dataset;

public class DatasetTest {
    Dataset m = new Dataset(5, 3);

    @Test
    public void testMatrix() {
        assertEquals(m.rows, 5);
        assertEquals(m.columns, 3);
        
        // ensure when matrix is defined, it's initialized with 0 value elements
        assertEquals(m.get(0, 0), new Double(0.0));
        assertEquals(m.get(1, 0), new Double(0.0));
        assertEquals(m.get(2, 0), new Double(0.0));
        assertEquals(m.get(3, 0), new Double(0.0));
        assertEquals(m.get(4, 0), new Double(0.0));
        assertEquals(m.get(0, 1), new Double(0.0));
        assertEquals(m.get(1, 1), new Double(0.0));
        assertEquals(m.get(2, 1), new Double(0.0));
        assertEquals(m.get(3, 1), new Double(0.0));
        assertEquals(m.get(4, 1), new Double(0.0));
        assertEquals(m.get(0, 2), new Double(0.0));
        assertEquals(m.get(1, 2), new Double(0.0));
        assertEquals(m.get(2, 2), new Double(0.0));
        assertEquals(m.get(3, 2), new Double(0.0));
        assertEquals(m.get(4, 2), new Double(0.0));
    }

    @Test
    public void testGetMatrixDim() {
        assertEquals(m.getMatrixDim(), "5 x 3");
    }

    @Test
    public void testNumOfRows() {
        assertEquals(m.numOfRows(), 5);
    }

    @Test
    public void testNumOfCols() {
        assertEquals(m.numOfCols(), 3);
    }

    @Test
    public void testInsert() {
        m.insert(2.5, 1, 1);
        m.insert(3.5, 3, 1);
        assertEquals(m.get(1, 1), new Double(2.5));
        assertEquals(m.get(3, 1), new Double(3.5));
    }
    
    public void testGetRow() {
    }

    public void testGetColumn() {
    }

    public void testGet() {
    }

    public void testAdd() {
    }

    public void testSubtract() {
    }

    public void testMultiplyMatrix() {
    }

    public void testMultiplyVectorOfDouble() {
    }

    public void testSubset() {
    }

    public void testSubsetRows() {
    }

    public void testSubsetColumns() {
    }

    public void testTranspose() {
    }

    public void testEqualsMatrix() {
    }

    public void testPrint() {
    }
}
