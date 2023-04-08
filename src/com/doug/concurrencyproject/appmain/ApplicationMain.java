package com.doug.concurrencyproject.appmain;

import java.io.IOException;

import com.doug.concurrencyproject.deviationcalculator.DeviationCalculator;
import com.doug.concurrencyproject.matrixmultiplication.MatrixMultiplication;
import com.doug.concurrencyproject.mergesort.MergeSort;

/**
 * 
 * @author Douglas Santos - 2020338
 *
 */

public class ApplicationMain {

	public static void main(String[] args) throws IOException, InterruptedException {

		String filePath = "src/data.csv";

		//Title
		System.out.println("CA1: Application of Concurrency to Common Tasks\n");

		// Task 1 - Find the standard deviation of the data.
		DeviationCalculator standardDeviation = new DeviationCalculator(filePath);
		double result = standardDeviation.calculate();
		System.out.format("\n1) The Standard Deviation of the data is: %.4f", result);

		System.out.println("\n\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

		// Task 2 - Assume the data is in the form of two square matrices, one after another. Multiply the two matrices together.
		MatrixMultiplication matrixMultiplication = new MatrixMultiplication(filePath);
		System.out.println("\n2) Matrix Multiplication Result:\n");		
		matrixMultiplication.calculate();
		
		// Option to check the values of the matrices that are being multiplied.
		// (Commented on purpose - uncomment if needed).
		// matrixMultiplication.printInitialMatrices();

		System.out.println("\n\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

		// Task 3 - Use merge sort to sort the data from largest to smallest.
		MergeSort dataFromFile = new MergeSort(filePath);
		System.out.println("\n3) Sorted data from largest to smallest:\n");
		System.out.print("a) ROWS sorted from left to right ---> "
				+ "\n_________________________________________________________________________\n");
		dataFromFile.sortDataSetByRows();
		System.out.println("\n_________________________________________________________________________");
		System.out.print("\n\nb) ALL DATA sorted from left to right --->"
				+ "\n_________________________________________________________________________\n ");
		dataFromFile.sortAllDataSet();
		System.out.println("\n_________________________________________________________________________");

	}

}
