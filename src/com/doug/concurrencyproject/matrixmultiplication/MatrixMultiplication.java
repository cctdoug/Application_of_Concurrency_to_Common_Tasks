package com.doug.concurrencyproject.matrixmultiplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.doug.concurrencyproject.util.ReadFromCSVFile;

public class MatrixMultiplication {

	private int[][] matrix1;
	private int[][] matrix2;
	private String filePath;

	public MatrixMultiplication(String filePath) {
		this.filePath = filePath;
	}

	public void calculate() throws InterruptedException, IOException {

		int rowIndex = 0;
		int numCols = 0;

		ReadFromCSVFile csvFile = new ReadFromCSVFile(filePath);
		List<List<Integer>> rowsOfTheFile = csvFile.ReadFile();

		while (rowIndex < rowsOfTheFile.size()) {

			// If this is the first row, initialize the matrices
			if (rowIndex == 0) {

				// Set the number of columns in the first row
				numCols = rowsOfTheFile.get(0).size();

				// Initialize the matrices with the correct size
				matrix1 = new int[numCols][];
				matrix2 = new int[numCols][];

				for (int i = 0; i < numCols; i++) {
					matrix1[i] = new int[numCols];
					matrix2[i] = new int[numCols];
				}
			}

			// Read the values into the matrices
			for (int i = 0; i < numCols; i++) {
				int value = rowsOfTheFile.get(rowIndex).get(i);

				// If we're still reading the first matrix, store the values there
				if (rowIndex < numCols) {
					matrix1[rowIndex][i] = value;
				}

				// Otherwise, store the values in the second matrix
				else {
					matrix2[rowIndex - numCols][i] = value;
				}
			}
			// Counter for the rows
			rowIndex++;
		}

		// Creates the result array to hold the output of each thread
		int[][] result = new int[matrix1.length][matrix2[0].length];

		// Creates the threads and start them
		List<Thread> threads = new ArrayList<>();
		for (int i = 0; i < matrix1.length; i++) {
			// Create a new thread to compute each row of the matrix result separately
			MatrixMultiplicationThread thread = new MatrixMultiplicationThread(matrix1[i], i, matrix2, result);
			threads.add(thread);
			thread.start();
		}

		// Waits for all threads to finish
		for (Thread thread : threads) {
			thread.join();
		}

		// Print the result
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[0].length; j++) {
				System.out.print(result[i][j] + "\t");
			}
			System.out.println();
		}

	}

	public void printInitialMatrices() {

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
