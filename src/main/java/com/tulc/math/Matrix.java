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
  protected Vector<Vector<E>> matrix;
  
  @SuppressWarnings("unchecked")
  public Matrix(int rows, int columns) {
    this.rows = rows;
    this.columns = columns;
    matrix = new Vector<Vector<E>>(rows);

    for (int i = 0; i < rows; i++) {
    	matrix.add(new Vector<E>(columns));
    }
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
  public Vector<E> getRow(int i) {
    return (Vector<E>) matrix.get(i);
  }
  
  /**
   * Return the column vector
   * @param j
   * @return
   */
  public Vector<E> getColumn(int j) {
    Vector<E> column = new Vector<E>(rows);
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
  public void insert(E e, int i, int j) {
	  matrix.get(i).add(j, e);
  }
  
  /**
   * Return the value at index [i, j]
   * @param i row index
   * @param j column index
 * @return 
   * @return
   */
  public E get(int i, int j) {
    return matrix.get(i).get(j);
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
	  print();
	  in.print();
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
   * Returns a subset matrix
   * @param rowStart Starting row position (1,2,3...,total_rows)
   * @param rowEnd Ending row position
   * @param colStart Starting column position (1,2,3...,total_columns)
   * @param colEnd Ending column position
   * @return
   * @throws IOException
   */
  public Matrix<E> subset(int rowStart, int rowEnd, int colStart, int colEnd) throws IOException {
	  if (rowEnd > this.rows || colEnd > this.columns)
		  throw new IOException("Row or column index out of Matrix range");
	  Matrix<E> subsetMatrix = new Matrix<E>((rowEnd - rowStart) + 1, (colEnd - colStart) + 1);
	  for(int i = rowStart-1; i < rowEnd; i++) {
		  for(int j = colStart-1; j < colEnd; j++) {
			  subsetMatrix.insert(this.get(i, j), i, j);
		  }
	  }
	  return subsetMatrix;
  }
  
  public Matrix<E> subsetRows(int rowStart, int rowEnd) throws IOException {
	  if (rowEnd > this.rows)
		  throw new IOException("Row index out of Matrix range");
	  Matrix<E> subsetMatrix = subset(rowStart, rowEnd, 1, columns);
	  return subsetMatrix;
  }
  
  public Matrix<E> subsetColumns(int colStart, int colEnd) throws IOException {
	  if (colEnd > this.rows)
		  throw new IOException("Column index out of Matrix range");
	  Matrix<E> subsetMatrix = subset(1, rows, colStart, colEnd);
	  return subsetMatrix;
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
  public boolean equals(Matrix<?> in) {
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
