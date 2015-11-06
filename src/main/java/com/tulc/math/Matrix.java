package com.tulc.math;

import java.io.IOException;
import java.util.Vector;

/**
 * @author Tulasi Paradarami
 * @version 0.1
 * @param <E>
 */
public class Matrix {
  protected int rows;
  protected int columns;
  private Vector<Double> columnVector;
  protected Vector<Vector<Double>> matrix;
  private MatrixUtils mu;
  
  public Matrix(int rows, int columns) {
      mu = new MatrixUtils();
      this.rows = rows;
      this.columns = columns;
      matrix = new Vector<Vector<Double>>(rows);
      for (int i = 0; i < rows; i++) {
          matrix.add(getColumnVector(columns));
      }
  }
  
  private Vector<Double> getColumnVector(int cols) {
      columnVector = new Vector<Double>(columns);
      for (int i = 0; i < cols; i++) {
          columnVector.add(i, 0.0);
      }
      return columnVector;
  }
  
  public String getMatrixDim() {
      return rows + " x " + columns;
  }
    
  /**
   * Return number of rows in the matrix
   * @return
   */
  public int numOfRows() {
    return matrix.size();
  }
  
  /**
   * Return number of columns in the matrix
   * @return
   */
  public int numOfCols() {
    return matrix.get(0).size();
  }
  
  /**
   * Return the row vector
 * @param <E>
   * @param i
   * @return
   */
  public Vector<Double> getRow(int i) {
    return (Vector<Double>) matrix.get(i);
  }
  
  /**
   * Return the column vector
   * @param j
   * @return
   */
  public Vector<Double> getColumn(int j) {
    Vector<Double> column = new Vector<Double>(rows);
    for(int i = 0; i < rows; i++) {
        column.add(matrix.get(i).get(j));
    }
    return column;
  }
  
  /**
   * Method to insert an element, e, in the matrix at
   * location [i, j]
   * 
   * @param e value of the element to be added
   * @param i row index
   * @param j column index
   */
  public void insert(Double e, int i, int j) {
      matrix.get(i).add(j, e);
  }
  
  /**
   * Return the value at index [i, j]
   * @param i row index
   * @param j column index
 * @return 
   * @return
   */
  public Double get(int i, int j) {
    return matrix.get(i).get(j);
  }
  
  /**
   * add two matrices
   * @param in
   * @return
   * @throws IOException
   */
  public Matrix add(Matrix in) throws IOException {
    if ((in.numOfRows() != numOfRows()) ||
        in.numOfCols() != numOfCols()) {
      String err = "Error! Matrix dimensions donot match";
      System.out.println(err);
      throw new IOException(err);
    }
   
    Matrix out = new Matrix(rows, columns);
    
    for(int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
          out.insert((get(i, j) + in.get(i, j)), i, j);
      }
    }
    
    return out;
  } 
  
  /**
   * subtract two matrices
   * @param in
   * @return
   * @throws IOException
   */
  public Matrix subtract(Matrix in) throws IOException {
    if ((in.numOfRows() != numOfRows()) ||
        in.numOfCols() != numOfCols()) {
      String err = "Error! Matrix dimensions donot match";
      System.out.println(err);
      throw new IOException(err);
    }
   
    Matrix out = new Matrix(rows, columns);
    
    for(int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
          out.insert(get(i, j) - in.get(i, j), i, j);
      }
    }
    
    return out;
  }
  
  /**
   * Return the result of multiplying two matrices
   * @param in
   * @return
   * @throws IOException
   */
  public Matrix multiply(Matrix in) throws IOException {
      if (columns != in.numOfRows()) {
          String err = "Error! cannot multiply. columns of LHS != rows of RHS";
          throw new IOException(err);
      }
    
      int outRows = rows;
      int outCols = in.numOfCols();
      Vector<Double> row = new Vector<Double>(columns);
      Vector<Double> column = new Vector<Double>(in.numOfRows());
      Matrix out = new Matrix(outRows, outCols);
      for(int i=0; i<outRows; i++) {
          row = getRow(i);
          for (int j=0; j<outCols; j++) {
              column = getColumn(j);
              out.insert(mu.dotProduct(row, column), i, j);
              mu.dotProduct(row, column);
          }
      }
    return out;
  }
  
  public Matrix multiply(Vector<Double> in) throws IOException {
      Matrix m = new Matrix(in.size(), 1);
      for (int i = 0; i < m.rows; i++) {
          m.insert(in.get(i), i, 1);
      }
      return multiply(m);
  }
  
  /**
   * Returns a subset matrix
   * @param rowStart Starting row position (1,2,3...,total_rows)
   * @param rowEnd Ending row position
   * @param colStart Starting column position (1,2,3...,total_columns)
   * @param colEnd Ending column position
   * @return
   * @throws IOException
   */
  public Matrix subset(int rowStart, int rowEnd, int colStart, int colEnd) throws IOException {
      if (rowEnd > this.rows || colEnd > this.columns)
          throw new IOException("Row or column index out of Matrix range");
      Matrix subsetMatrix = new Matrix((rowEnd - rowStart) + 1, (colEnd - colStart) + 1);
      for(int i = rowStart-1; i < rowEnd; i++) {
          for(int j = colStart-1; j < colEnd; j++) {
              subsetMatrix.insert(this.get(i, j), i, j);
          }
      }
      return subsetMatrix;
  }
  
  public Matrix subsetRows(int rowStart, int rowEnd) throws IOException {
      if (rowEnd > this.rows)
          throw new IOException("Row index out of Matrix range");
      Matrix subsetMatrix = subset(rowStart, rowEnd, 1, columns);
      return subsetMatrix;
  }
  
  public Matrix subsetColumns(int colStart, int colEnd) throws IOException {
      if (colEnd > this.rows)
          throw new IOException("Column index out of Matrix range");
      Matrix subsetMatrix = subset(1, rows, colStart, colEnd);
      return subsetMatrix;
  }
  
  /**
   * Return the matrix transpose
   * @return
   */
  public Matrix transpose() {
    Matrix out = new Matrix(columns, rows);
    for(int i = 0; i < out.numOfRows(); i++) {
      for(int j = 0; j < out.numOfCols(); j++) {
        out.insert(get(j, i), i, j);
      }
    }
    return out;
  }

  /**
   * true if matrices are equal
   * @param in
   * @return
   */
  public boolean equals(Matrix in) {
    if (rows != in.numOfRows()) return false;
    if (columns != in.numOfCols()) return false;
    
    for(int i=0; i<rows; i++) {
      for(int j=0; j<columns; j++) {
        if (get(i, j) != in.get(i, j)) return false;
      }
    }
    return true;
  }
  
  /**
   * Print a readable matrix format
   */
  public void print() {
    for(int i=0; i<rows; i++) {
      System.out.print("[    ");
      for(int j=0; j<columns; j++) {
        System.out.print(get(i,j) + "    ");
      }
      System.out.println("]");
    }
    
  }
}
