package com.doug.concurrencyproject.matrixmultiplication;

public class MatrixMultiplicationThread extends Thread {

	private int[] matrix1row;
	private int matrix1index;
	private int[][] matrix2;
	private int[][] matrixResult;

	/**
	 * Method that will multiply the rows of the matrices
	 * 
	 * @param matrix1row   - row of matrix 1
	 * @param matrix1index - index of the row of matrix 1 (that will also be the
	 *                     index of the row of the matrix result)
	 * @param matrix2      - the whole matrix 2
	 * @param matrixResult - to receive the results of the two matrices
	 */
	public MatrixMultiplicationThread(int[] matrix1row, int matrix1index, int[][] matrix2, int[][] matrixResult) {
		this.matrix1row = matrix1row;
		this.matrix1index = matrix1index;
		this.matrix2 = matrix2;
		this.matrixResult = matrixResult;
	}

	@Override
	public void run() {

		// Store the number of rows and columns of the two matrices.
		int cols1 = this.matrix1row.length;
		int cols2 = this.matrix2[0].length;

		// Multiply each a row from matrix1 by all the columns of matrix2
		for (int i = 0; i <= 0; i++) { // -- Loop over a single row of matrix1
			for (int j = 0; j < cols2; j++) { // -- Loop over all columns of matrix2
				int sum = 0;
				for (int k = 0; k < cols1; k++) { // -- Multiply and sum the elements of the row
					sum += matrix1row[k] * matrix2[k][j];
				}
				matrixResult[matrix1index][j] = sum; // -- Add the element to the matrix result
			}
		}
	}

}
