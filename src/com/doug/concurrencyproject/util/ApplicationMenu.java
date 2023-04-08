package com.doug.concurrencyproject.util;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.doug.concurrencyproject.deviationcalculator.DeviationCalculator;
import com.doug.concurrencyproject.matrixmultiplication.MatrixMultiplication;
import com.doug.concurrencyproject.mergesort.MergeSort;

public class ApplicationMenu {

	Scanner scanner = new Scanner(System.in);
	String filePath = "src/data.csv";

	public void choose() throws IOException, InterruptedException {

		while (true) {
			// Displays menu options
			System.out.println("\n- - - - - - - - - - - - - - - - MENU - - - - - - - - - - - - - - - \n");
			System.out.println("1. Find the standard deviation of the data");
			System.out.println("2. Check the two matrices to multiply");
			System.out.println("3. Multiply two square matrices together");
			System.out.println("4. Use merge sort to sort the data from largest to smallest");
			System.out.println("5. Show data set");
			System.out.println("0. Exit");

			// Gets user input and validates it
			// (If not an integer, prompts the menu again)
			int choice;
			try {
				System.out.print("\nChoose an option: ");
				choice = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Invalid input! \nPlease choose a number from the MENU.");
				scanner.next();
				continue;
			}

			switch (choice) {
			case 1:
				// Task 1 - Finds the standard deviation of the data.
				DeviationCalculator standardDeviation = new DeviationCalculator(filePath);
				double result = standardDeviation.calculate();
				System.out.format("\nThe Standard Deviation of the data is: %.4f\n", result);
				System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
				break;

			case 2:
				// Task 2 - Shows the Matrices.
				MatrixMultiplication checkMatrices = new MatrixMultiplication(filePath);
				System.out.println("\nMatrices:");
				checkMatrices.printInitialMatrices();
				System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
				break;

			case 3:
				// Task 2 - Multiplies the matrices.
				MatrixMultiplication matrixMultiplication = new MatrixMultiplication(filePath);
				System.out.println("\nMatrix Multiplication Result:\n");
				matrixMultiplication.printMatrixResult();
				System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
				break;

			case 4:
				// Task 3 - Sorts the data using merge sort				
				while (true) {
					// Displays sub-menu
					System.out.println("\nSort the data using merge sort:");
					System.out.println("1. Sort data by rows");
					System.out.println("2. Sort all data");
					System.out.println("0. Back to main menu");

					// Gets user input and validates it
					int subChoice;
					try {
						System.out.print("Enter your choice: ");
						subChoice = scanner.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("Invalid input! \nPlease choose a number from the MENU.");
						scanner.next();
						continue;
					}

					switch (subChoice) {
					case 1:
						// Sorts data by rows
						MergeSort dataFromFile = new MergeSort(filePath);
						System.out.println("\nSorted data from largest to smallest (BY ROWS):\n");
						dataFromFile.sortDataSetByRows();
						System.out.println(
								"\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
						break;

					case 2:
						// Sorts all data
						MergeSort allDataFromFile = new MergeSort(filePath);
						System.out.println("\nSorted data from largest to smallest (ALL DATA):\n");
						allDataFromFile.sortAllDataSet();
						System.out.println(
								"\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
						break;

					case 0:
						// Back to main menu
						break;

					default:
						System.out.println("Invalid choice!\nPlease choose a number from the MENU.");
					}

					if (subChoice == 0) {
						break;
					}
				}
				break;

			case 5:
				// Prints the original data set.
				ReadFromCSVFile readDataSet = new ReadFromCSVFile(filePath);
				System.out.println("\nData Set (Read from data.csv):\n");
				readDataSet.printDataSet();
				System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
				break;

			case 0:
				// Exits the program.
				System.out.println("Exiting the program...\n");
				System.out.println("/\\");
				System.out.println("\\/'");
				System.out.println("(( '");
				System.out.println(" )) '");
				System.out.println(" (   '");
				System.out.println("      ' Thanks for using the system. See you next!");
				System.exit(0);

			default:
				System.out.println("Invalid choice!\nPlease choose a number from the MENU.");
			}
		}
	}

}
