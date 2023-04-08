package com.doug.concurrencyproject.deviationcalculator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.doug.concurrencyproject.util.ReadFromCSVFile;

public class DeviationCalculator {

	private String filePath;

	public DeviationCalculator(String filePath) {
		this.filePath = filePath;
	}

	public double calculate() throws IOException, InterruptedException {

		List<DeviationCalculatorThread> data = new ArrayList<>();
		List<Thread> threads = new ArrayList<>();

		// Reads the CSV file and divide it into rows
		ReadFromCSVFile csvFile = new ReadFromCSVFile(filePath);
		List<List<Integer>> rowsOfTheFile = csvFile.ReadFile();

		// Creates a thread for each row of the file
		for (int i = 0; i < rowsOfTheFile.size(); i++) {
			DeviationCalculatorThread dataRow = new DeviationCalculatorThread(rowsOfTheFile.get(i));
			data.add(dataRow);
			Thread thread = new Thread(dataRow);
			threads.add(thread);
		}

		// Starts all threads
		for (int i = 0; i < threads.size(); i++) {
			Thread thread = threads.get(i);
			thread.start();
		}

		// Waits for all threads to finish
		for (Thread thread : threads) {
			thread.join();
		}

		// Combines the results to compute the overall sum of elements and sum of squares
		double sumOfElements = 0;
		double sumOfSquares = 0;
		double countOfElements = 0;
		// Iterates through the data ArrayList to get the results of each row
		for (DeviationCalculatorThread row : data) {
			sumOfElements += row.getSumOfElements();
			sumOfSquares += row.getSumOfSquares();
			countOfElements += row.getCountOfElements();
		}

		// Uses the results to do the last bit of the calculation using the results of
		// each row combined
		double mean = sumOfElements / countOfElements;
		double variation = sumOfSquares / countOfElements - mean * mean;

		return Math.sqrt(variation);
	}

}