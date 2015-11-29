package com.tulc.data;

import java.io.IOException;
import java.util.Vector;

import com.tulc.math.RVector;

/**
 * @author Tulasi Paradarami
 * @version 0.1
 * @param <E>
 */
public class Dataset {
    protected int rows;
    protected int columns;
    protected Vector<RVector> matrix;

    public Dataset(int r, int c) {
        rows = r;
        columns = c;
        matrix = new Vector<RVector>(rows);
        for (int i = 0; i < rows; i++) {
            matrix.add(initializeRowVector());
        }
    }
    
    public Dataset(Dataset m) {
        rows = m.numOfRows();
        columns = m.numOfCols();
        matrix = new Vector<RVector>(rows);
        for (int i = 0; i < rows; i++) {
            matrix.add(initializeRowVector());
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                insert(m.get(i, j), i, j);
            }
        }
    }

    private RVector initializeRowVector() {
        RVector rowVector = new RVector(columns);
        for (int i = 0; i < columns; i++) {
            rowVector.add(i, 0.0);
        }
        return rowVector;
    }
    
    public String getMatrixDim() {
        return numOfRows() + " x " + numOfCols();
    }

    /**
     * Return number of rows in the matrix
     * 
     * @return
     */
    public int numOfRows() {
        return matrix.size();
    }

    /**
     * Return number of columns in the matrix
     * 
     * @return
     */
    public int numOfCols() {
        return matrix.get(0).size();
    }

    /**
     * Return the row vector
     * 
     * @param <E>
     * @param i
     * @return
     */
    public RVector getRow(int i) {
        return matrix.get(i);
    }
    
    public void setRow(int i, RVector v) {
        matrix.set(i, v);
    }

    /**
     * Return the column vector
     * 
     * @param j
     * @return
     */
    public RVector getFeatureVector(int j) {
        RVector column = new RVector(rows);
        for (int i = 0; i < rows; i++) {
            column.add(matrix.get(i).get(j));
        }
        return column;
    }
    
    /**
     * Updates the feature vector in the matrix with input v
     * at index j
     * @param j
     * @param v
     */
    public void setFeatureVector(int j, RVector v) {
        for (int i = 0; i < rows; i++) {
            insert(v.get(i), i, j);
        }
    }

    /**
     * Method to insert an element, e, in the matrix at location [i, j]
     * 
     * @param e
     *            value of the element to be added
     * @param i
     *            row index
     * @param j
     *            column index
     */
    public void insert(Double e, int i, int j) {
        matrix.get(i).set(j, e);
    }

    /**
     * Return the value at index [i, j]
     * 
     * @param i
     *            row index
     * @param j
     *            column index
     * @return
     * @return
     */
    public Double get(int i, int j) {
        return matrix.get(i).get(j);
    }

    /**
     * add two matrices
     * 
     * @param in
     * @return
     * @throws IOException
     */
    public Dataset add(Dataset in) throws IOException {
        if ((in.numOfRows() != numOfRows()) || in.numOfCols() != numOfCols()) {
            String err = "Error! Matrix dimensions donot match";
            System.out.println(err);
            throw new IOException(err);
        }

        Dataset out = new Dataset(rows, columns);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                out.insert((get(i, j) + in.get(i, j)), i, j);
            }
        }

        return out;
    }

    /**
     * subtract two matrices
     * 
     * @param in
     * @return
     * @throws IOException
     */
    public Dataset subtract(Dataset in) throws IOException {
        if ((in.numOfRows() != numOfRows()) || in.numOfCols() != numOfCols()) {
            String err = "Error! Matrix dimensions donot match";
            System.out.println(err);
            throw new IOException(err);
        }

        Dataset out = new Dataset(rows, columns);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                out.insert(get(i, j) - in.get(i, j), i, j);
            }
        }

        return out;
    }

    /**
     * Return the result of multiplying two matrices
     * 
     * @param in
     * @return
     * @throws IOException
     */
    public Dataset multiply(Dataset in) throws IOException {
        if (columns != in.numOfRows()) {
            String err = "Error! cannot multiply. columns of LHS != rows of RHS";
            throw new IOException(err);
        }

        int outRows = rows;
        int outCols = in.numOfCols();
        RVector row = new RVector(columns);
        RVector column = new RVector(in.numOfRows());
        Dataset out = new Dataset(outRows, outCols);
        for (int i = 0; i < outRows; i++) {
            row = getRow(i);
            for (int j = 0; j < outCols; j++) {
                column = getFeatureVector(j);
                out.insert(MatrixUtil.dotProduct(row, column), i, j);
            }
        }
        return out;
    }

    public RVector multiply(RVector in) throws IOException {
        Dataset m = new Dataset(in.size(), 1);
        for (int i = 0; i < m.rows; i++) {
            m.insert(in.get(i), i, 0);
        }
        return multiply(m).getFeatureVector(0);
    }

    /**
     * Returns a subset matrix
     * 
     * @param rowStart
     *            Starting row position (1,2,3...,total_rows)
     * @param rowEnd
     *            Ending row position
     * @param colStart
     *            Starting column position (1,2,3...,total_columns)
     * @param colEnd
     *            Ending column position
     * @return
     * @throws IOException
     */
    public Dataset subset(int rowStart, int rowEnd, int colStart, int colEnd) throws IOException {
        if (rowEnd > this.rows || colEnd > this.columns)
            throw new IOException("Row or column index out of Matrix range");
        Dataset subsetMatrix = new Dataset((rowEnd - rowStart) + 1, (colEnd - colStart) + 1);
        for (int i = rowStart - 1; i < rowEnd; i++) {
            for (int j = colStart - 1; j < colEnd; j++) {
                subsetMatrix.insert(this.get(i, j), i, j);
            }
        }
        return subsetMatrix;
    }

    public Dataset subsetRows(int rowStart, int rowEnd) throws IOException {
        if (rowEnd > this.rows)
            throw new IOException("Row index out of Matrix range");
        Dataset subsetMatrix = subset(rowStart, rowEnd, 1, columns);
        return subsetMatrix;
    }

    public Dataset subsetColumns(int colStart, int colEnd) throws IOException {
        if (colEnd > this.rows)
            throw new IOException("Column index out of Matrix range");
        Dataset subsetMatrix = subset(1, rows, colStart, colEnd);
        return subsetMatrix;
    }

    /**
     * Return the matrix transpose
     * 
     * @return
     */
    public Dataset transpose() {
        Dataset out = new Dataset(columns, rows);
        for (int i = 0; i < out.numOfRows(); i++) {
            for (int j = 0; j < out.numOfCols(); j++) {
                out.insert(get(j, i), i, j);
            }
        }
        return out;
    }
    
    public void copy(Dataset m) throws IOException {
        if (rows > m.numOfRows() || columns < m.numOfCols()) {
            throw new IOException("Source matrix dimension [" + m.getMatrixDim() + "] "
                    + "does not fit into target matrix [" + getMatrixDim() + "]");
        }
        
        for (int i = 0; i < m.numOfRows(); i++) {
            for (int j = 0; j < m.numOfCols(); j++) {
                insert(m.get(i, j), i, j);
            }
        }
    }
    
    /**
     * Method to insert a new feature vector at the specified location
     * All feature vectors following the index are pushed by one location
     * @param fv Feature vector to be inserted at index i
     * @param i index of the location where feature vector will be inserted
     */
    public void insertFeatureVector(RVector fv, int ind) {
        int cols = columns + 1;
        Dataset newMatrix = new Dataset(rows, cols);
        for (int j = 0; j < cols; j++) {
            if (j == ind) {
                newMatrix.setFeatureVector(j, fv);
            }
            else {
                newMatrix.setFeatureVector(j, getFeatureVector(j));
            }
        }
        matrix = newMatrix.matrix;
    }

    /**
     * true if matrices are equal
     * 
     * @param in
     * @return
     */
    public boolean equals(Dataset in) {
        if (rows != in.numOfRows())
            return false;
        if (columns != in.numOfCols())
            return false;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (get(i, j) != in.get(i, j))
                    return false;
            }
        }
        return true;
    }

    /**
     * Print a readable matrix format
     */
    public void print() {
        for (int i = 0; i < numOfRows(); i++) {
            System.out.print("[    ");
            for (int j = 0; j < numOfCols(); j++) {
                System.out.print(get(i, j) + "    ");
            }
            System.out.println("]");
        }

    }
}
