package com.doug.concurrencyproject.deviationcalculator;

import java.util.List;

public class DeviationCalculatorThread extends Thread {

	private List<Integer> data;
	private int sumOfElements;
	private int sumOfSquares;
	private int countOfElements;
	private double mean;
	private double variation;

	public DeviationCalculatorThread(List<Integer> data) {
		this.data = data;
	}

	public int getSumOfElements() {
		return sumOfElements;
	}

	public int getSumOfSquares() {
		return sumOfSquares;
	}

	public int getCountOfElements() {
		return countOfElements;
	}

	public double getMean() {
		return mean;
	}

	public double getVariation() {
		return variation;
	}

	@Override
	public void run() {

		// Compute the sum of the elements and sum of squares for each row
		for (int element : data) {
			sumOfElements += element;
			sumOfSquares += element * element;

		}

		// Compute the mean and variation using the sum of elements and sum of squares
		countOfElements += data.size();
		mean = sumOfElements / countOfElements;
		variation = sumOfSquares / countOfElements - mean * mean;

	}

}
