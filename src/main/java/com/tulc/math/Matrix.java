package com.tulc.math;

import java.io.IOException;
import java.util.Vector;

/**
 * @author Tulasi Paradarami
 * @version 0.1
 * @param <E>
 */
public class Matrix<E extends Number> {
  protected int rows;
  protected int columns;
  protected Vector<Vector<E>> x;
  
  public Matrix(int rows, int columns) {
    this.rows = rows;
    this.columns = columns;
    
    for (int i = 0; i < rows; i++) {
      x.add(new Vector<E>(columns));
    }
  }
  
  /**
   * Return number of rows in the matrix
   * @return
   */
  public int numOfRows() {
    return x.size();
  }
  
  /**
   * Return number of columns in the matrix
   * @return
   */
  public int numOfCols() {
    return x.get(0).size();
  }
  
  /**
   * Return the row vector
 * @param <E>
   * @param i
   * @return
   */
  public Vector<E> getRow(int i) {
    return (Vector<E>) x.get(i);
  }
  
  /**
   * Return the column vector
   * @param j
   * @return
   */
  public Vector<E> getColumn(int j) {
    Vector<E> column = new Vector<E>(rows);
    for(int i = 0; i < rows; i++) {
      column.add(x.get(i).get(j));
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
  public void insert(E e, int i, int j) {
    x.get(i).set(j, e);
  }
  
  /**
   * Return the value at index [i, j]
   * @param i row index
   * @param j column index
 * @return 
   * @return
   */
  public E get(int i, int j) {
    return x.get(i).get(j);
  }
  
  /**
   * add two matrices
   * @param in
   * @return
   * @throws IOException
   */
  public Matrix<E> add(Matrix<E> in) throws IOException {
    if ((in.numOfRows() != numOfRows()) ||
        in.numOfCols() != numOfCols()) {
      String err = "Error! Matrix dimensions donot match";
      System.out.println(err);
      throw new IOException(err);
    }
   
    Matrix<E> out = new Matrix<E>(rows, columns);
    
    for(int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
          out.insert(add(get(i,j), in.get(i,j)), i, j); 
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
  public Matrix<E> subtract(Matrix<E> in) throws IOException {
    if ((in.numOfRows() != numOfRows()) ||
        in.numOfCols() != numOfCols()) {
      String err = "Error! Matrix dimensions donot match";
      System.out.println(err);
      throw new IOException(err);
    }
   
    Matrix<E> out = new Matrix<E>(rows, columns);
    
    for(int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
          out.insert(subtract(get(i,j), in.get(i,j)), i, j); 
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
  public Matrix<E> multiply(Matrix<E> in) throws IOException {
    if (columns != in.numOfRows()) {
      String err = "Error! cannot multiply. columns of LHS != rows of RHS";
      throw new IOException(err);
    }
    
    int outRows = rows;
    int outCols = in.numOfCols();
    Vector<E> row = new Vector<E>(columns);
    Vector<E> column = new Vector<E>(in.numOfRows());
    Matrix<E> out = new Matrix<E>(outRows, outCols);

    for(int i=0; i<outRows; i++) {
      row = getRow(i);
      for (int j=0; j<outCols; j++) {
        column = getColumn(j);
        for(int k=0; k<row.size(); k++) {
          out.insert(add(multiply(row.get(k), column.get(k)), out.get(i, j)), i, j);
        }
      }
    }
    
    return out;
  }
  
  /**
   * Return the matrix transpose
   * @return
   */
  public Matrix<E> transpose() {
    Matrix<E> out = new Matrix<E>(columns, rows);
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
  
  @SuppressWarnings({ "unchecked", "hiding" })
  <E> E add(E one, E two) {
    if (one.getClass() == Integer.class) {
      return (E) (Integer) ((Integer) one + (Integer) two);
    } 
    else if (one.getClass() == Long.class) {
      return (E) Long.valueOf(((Long) one).longValue() + ((Long) two).longValue());
    } 
    else if (one.getClass() == Double.class) {
      return (E) Double.valueOf(((Double) one).doubleValue() + ((Double) two).doubleValue());
    }
    return one;
  }
  
  @SuppressWarnings({ "unchecked", "hiding" })
  <E> E subtract(E one, E two) {
    if (one.getClass() == Integer.class) {
      return (E) (Integer) ((Integer) one - (Integer) two);
    } 
    else if (one.getClass() == Long.class) {
      return (E) Long.valueOf(((Long) one).longValue() - ((Long) two).longValue());
    } 
    else if (one.getClass() == Double.class) {
      return (E) Double.valueOf(((Double) one).doubleValue() - ((Double) two).doubleValue());
    }
    return one;
  }
  
  @SuppressWarnings({ "unchecked", "hiding" })
  <E> E multiply(E one, E two) {
    if (one.getClass() == Integer.class) {
      return (E) (Integer) ((Integer) one * (Integer) two);
    } 
    else if (one.getClass() == Long.class) {
      return (E) Long.valueOf(((Long) one).longValue() * ((Long) two).longValue());
    } 
    else if (one.getClass() == Double.class) {
      return (E) Double.valueOf(((Double) one).doubleValue() * ((Double) two).doubleValue());
    }
    return one;
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