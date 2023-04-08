package com.doug.concurrencyproject.matrixmultiplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.doug.concurrencyproject.util.ReadFromCSVFile;

public class MatrixMultiplication {

	private int[][] matrix1;
	private int[][] matrix2;
	private int[][] matrixResult;
	private String filePath;

	public MatrixMultiplication(String filePath) {
		this.filePath = filePath;
	}

	private void calculate() throws InterruptedException, IOException {

		int rowIndex = 0;
		int numCols = 0;

		ReadFromCSVFile csvFile = new ReadFromCSVFile(filePath);
		List<List<Integer>> rowsOfTheFile = csvFile.ReadFile();

		while (rowIndex < rowsOfTheFile.size()) {

			// If this is the first row, initializes the matrices
			if (rowIndex == 0) {

				// Sets the number of columns in the first row
				numCols = rowsOfTheFile.get(0).size();

				// Initializes the matrices with the correct size
				matrix1 = new int[numCols][];
				matrix2 = new int[numCols][];

				for (int i = 0; i < numCols; i++) {
					matrix1[i] = new int[numCols];
					matrix2[i] = new int[numCols];
				}
			}

			// Reads the values into the matrices
			for (int i = 0; i < numCols; i++) {
				int value = rowsOfTheFile.get(rowIndex).get(i);

				// If we're still reading the first matrix, stores the values there
				if (rowIndex < numCols) {
					matrix1[rowIndex][i] = value;
				}

				// Otherwise, stores the values in the second matrix
				else {
					matrix2[rowIndex - numCols][i] = value;
				}
			}
			// Counter for the rows
			rowIndex++;
		}

		// Creates the result array to hold the output of each thread
		matrixResult = new int[matrix1.length][matrix2[0].length];

		// Creates the threads and start them
		List<Thread> threads = new ArrayList<>();
		for (int i = 0; i < matrix1.length; i++) {
			// Create a new thread to compute each row of the matrix result separately
			MatrixMultiplicationThread thread = new MatrixMultiplicationThread(matrix1[i], i, matrix2, matrixResult);
			threads.add(thread);
			thread.start();
		}

		// Waits for all threads to finish
		for (Thread thread : threads) {
			thread.join();
		}

	}

	// Prints the Matrix Result
	public void printMatrixResult() throws InterruptedException, IOException {
		calculate();
		for (int i = 0; i < matrixResult.length; i++) {
			for (int j = 0; j < matrixResult[0].length; j++) {
				System.out.print(matrixResult[i][j] + "\t");
			}
			System.out.println();
		}
	}

	// Prints Matrix1 and Matrix2
	public void printInitialMatrices() throws InterruptedException, IOException {
		calculate();
		System.out.println("\nMatrix 1");

		for (int i = 0; i < matrix1.length; i++) {
			for (int j = 0; j < matrix1[0].length; j++) {
				System.out.print(matrix1[i][j] + "\t");
			}
			System.out.println();
		}

		System.out.println("\nMatrix 2");

		for (int i = 0; i < matrix2.length; i++) {
			for (int j = 0; j < matrix2[0].length; j++) {
				System.out.print(matrix2[i][j] + "\t");
			}
			System.out.println();
		}

	}

}
