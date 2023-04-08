package com.doug.concurrencyproject.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class ReadFromCSVFile {

	// Variable to stores the path to the CSV file
	private String filePath;
	// Creates a list of lists to store the CSV data
	private List<List<Integer>> allRows = new ArrayList<>();

	// Constructor that sets the file path
	public ReadFromCSVFile(String filePath) {
		this.filePath = filePath;
	}

	public List<List<Integer>> getAllRows() {
		return allRows;
	}

	// Method that reads the CSV file and returns a list of lists of integers
	public List<List<Integer>> ReadFile() throws IOException {

		// Creates a list to store a single row of data
		List<Integer> singleRow;

		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));

			// Reads each line of the CSV file
			String eachLine;
			while ((eachLine = br.readLine()) != null) {

				// Splits the line using a comma as the delimiter
				String csvSplitBy = ",";
				String[] values = eachLine.split(csvSplitBy);
				singleRow = new ArrayList<>();

				// Parses the value as an integer and adds it to the current row list
				for (String value : values) {
					singleRow.add(Integer.parseInt(value.trim()));
				}
				// Adds the new row list to the main list
				allRows.add(singleRow);
			}
			br.close();

			// Handling possible exceptions
		} catch (IOException e) {
			throw new IOException("Error while reading the file.", e);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Error while parsing the value to integer.");
		}

		// Returns the list of all the rows in the file
		return allRows;
	}

	// Prints the data set
	public void printDataSet() throws IOException {
		ReadFile();
		int rowCount = getAllRows().size();
		int colCount = getAllRows().get(0).size();

		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				System.out.print(getAllRows().get(i).get(j) + "\t");
			}
			System.out.println();
		}
	}

}
