package com.doug.concurrencyproject.mergesort;

public class MergeSortThread extends Thread {

	private int[] arrayToSort;

	public MergeSortThread(int[] arrayToSort) {
		this.arrayToSort = arrayToSort;
	}

	public void mergeSort(int[] arrayToSort) {
		
		int inputLength = arrayToSort.length;

		// - - - Sorting
		// In case the array has fewer than 2 elements it will be already sorted
		if (inputLength < 2) {
			return;
		}

		// Divide the array into two halves and give them the sizes (half of the
		// original array to each)
		int midIndex = inputLength / 2;
		int[] leftHalf = new int[midIndex];
		int[] rightHalf = new int[inputLength - midIndex];

		// Initializing the arrays with the elements of the original array
		for (int i = 0; i < midIndex; i++) {
			leftHalf[i] = arrayToSort[i];
		}

		for (int i = midIndex; i < inputLength; i++) {
			rightHalf[i - midIndex] = arrayToSort[i];
		}

		// Sorting the array recursively until there's only one element left
		mergeSort(leftHalf);
		mergeSort(rightHalf);

		// Merging the sorted elements
		merge(arrayToSort, leftHalf, rightHalf);

	}

	public void merge(int[] arrayToSort, int[] leftHalf, int[] rightHalf) {

		// - - - Merging
		int leftSize = leftHalf.length;
		int rightSize = rightHalf.length;

		// To keep track of the position of each array
		int leftIndex = 0, rightIndex = 0, mainIndex = 0;

		// -- Conditions to re-populate the original array
		// -Compares the indexes to the sizes to check how many elements are left
		// -Compares the two values of both halves to see which is larger, and adds to the original array
		while (leftIndex < leftSize && rightIndex < rightSize) {
			if (leftHalf[leftIndex] >= rightHalf[rightIndex]) {
				arrayToSort[mainIndex] = leftHalf[leftIndex];
				leftIndex++;
			} else {
				arrayToSort[mainIndex] = rightHalf[rightIndex];
				rightIndex++;
			}
			mainIndex++;
		}
		// -- Conditions to add the remaining elements to both halves
		while (leftIndex < leftSize) {
			arrayToSort[mainIndex] = leftHalf[leftIndex];
			leftIndex++;
			mainIndex++;
		}
		while (rightIndex < rightSize) {
			arrayToSort[mainIndex] = rightHalf[rightIndex];
			rightIndex++;
			mainIndex++;
		}

	}

	@Override
	public void run() {

		mergeSort(arrayToSort);

	}

}
