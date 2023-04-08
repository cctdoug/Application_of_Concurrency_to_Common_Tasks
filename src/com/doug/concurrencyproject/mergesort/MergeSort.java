package com.doug.concurrencyproject.mergesort;

import java.io.IOException;
import java.util.List;

import com.doug.concurrencyproject.util.ReadFromCSVFile;

public class MergeSort {

	private int[][] data;
	private int[][] sortedRows;
	private int[] mergedData;

	public MergeSort(String filePath) throws IOException, InterruptedException {

		try {
			ReadFromCSVFile csvFile = new ReadFromCSVFile(filePath);
			List<List<Integer>> allRows = csvFile.ReadFile(); // get the list of lists

			// Create an array for each row
			data = new int[allRows.size()][];
			for (int i = 0; i < allRows.size(); i++) {
				List<Integer> row = allRows.get(i);
				int[] array = new int[row.size()];
				for (int j = 0; j < row.size(); j++) {
					array[j] = row.get(j);
				}
				data[i] = array;
			}

			// Starts the threads
			for (int i = 0; i < data.length; i++) {
				MergeSortThread thread = new MergeSortThread(data[i]);
				thread.start();
			}

			// Adds sorted rows in their original order
			sortedRows = new int[data.length][data[0].length];
			int index = 0;
			for (int k = 0; k < data.length; k++) {
				sortedRows[index++] = data[k];
			}

			// Adds all rows into one single array to be totally sorted
			mergedData = new int[data.length * data[0].length];
			index = 0;
			for (int i = 0; i < data.length; i++) {
				MergeSortThread thread = new MergeSortThread(data[i]);
				thread.join();
				for (int j = 0; j < data[i].length; j++) {
					mergedData[index++] = data[i][j];
				}
			}
		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
			throw e;
		} catch (InterruptedException e) {
			System.out.println("Error with thread: " + e.getMessage());
			throw e;
		}
	}

	// Method to print the individually sorted arrays (rows)
	public void sortDataSetByRows() {
		printArray(sortedRows);
	}

	// Sorts all elements in the data set and prints them back
	public void sortAllDataSet() throws InterruptedException {
		MergeSortThread thread = new MergeSortThread(mergedData);
		thread.start();
		thread.join();

		printArray(mergedData);

	}

	// Method to print a 2D array (the individually sorted arrays)
	private void printArray(int[][] data) {
		for (int i = 0; i < data.length; i++) {
			printArray(data[i]);
		}
	}

	// Method to print a single array (the array with all the data totally sorted)
	private void printArray(int[] data) {
		for (int i = 0; i < data.length; i++) {
			// For better visualization, it will print a line every 10 columns
			if (i % 10 == 0) {
				System.out.println();
			}
			System.out.print(data[i] + "\t");
		}
	}

}
