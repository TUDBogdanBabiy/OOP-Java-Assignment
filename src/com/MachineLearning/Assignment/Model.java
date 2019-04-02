package com.MachineLearning.Assignment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Model {

	File trainData;
	Scanner myReader;
	String[] values;
	String[][] values2;
	int lineCount = 0;
	int cols;
	int rows = 0;

	int tonYes = 0;
	int tonNo = 0;

	double tempCtons;
	double tempNtons;
	double tempHtons;
	double achesYtons;
	double achesNtons;
	double throatYtons;
	double throatNtons;
	double pTons;

	double tempCnotons;
	double tempNnotons;
	double tempHnotons;
	double achesYnotons;
	double achesNnotons;
	double throatYnotons;
	double throatNnotons;
	double pNoTons;

	public Model() {
		trainData = new File("Patient_Files\\TrainData.csv");
		try {
			myReader = new Scanner(trainData);

			// Scan the File to find amount of rows and columns
			while (myReader.hasNextLine()) {

				String line = myReader.nextLine();
				values = line.split(",");

				rows++;
			}
			cols = values.length;
			myReader.close();

			// Reopen the scanner from the beginning
			myReader = new Scanner(trainData);

			values2 = new String[rows][cols];
			while (myReader.hasNextLine()) {

				String line = myReader.nextLine();

				// Split the line and save into array
				values = line.split(",");

				// Fill the 2D array row by row
				for (int i = 0; i < cols; i++) {
					values2[lineCount][i] = values[i];
				}

				lineCount++;
			}

		} catch (IOException e) {

			System.out.println("\nCannot find file");

		} // End try catch

		// Prints 2D array
		for (int i = 0; i < lineCount; i++) {
			System.out.print("\n-------------------------------------\n");

			for (int j = 0; j < values.length; j++) {
				System.out.print(values2[i][j]);

			}

		}

	}

	public double calcProbability(String temp,String aches,String throat) {
		
		countinCol(4, "yes");
		expandCols("yes");
		expandCols("no");
		
		

		return 0;
	}

	public void countinCol(int col, String value) {
		for (int i = 0; i < rows; i++) {
			if (values2[col][i].equals(value)) {
				tonYes++;

			}
		}

		tonNo = tonYes - rows;
	}
	


	public void expandCols( String value) {

		
		// Temperature
		
			for (int i = 0, col=1; i < rows; i++) {

				if (values2[col][i].equals("hot") && values2[4][i].equals(value)) {
					if (value.equals("yes")) {
						tempHtons++;
					} else {
						tempHnotons++;
					}
				}

				if (values2[col][i].equals("cool") && values2[4][i].equals(value)) {
					if (value.equals("yes")) {
						tempCtons++;
					} else {
						tempCnotons++;
					}
				}

				if (values2[col][i].equals("normal") && values2[4][i].equals(value)) {
					if (value.equals("yes")) {
						tempNtons++;
					} else {
						tempNnotons++;
					}
				}
			}
			
		// Aches
		
			for (int i = 0,col =2; i < rows; i++) {

				if (values2[col][i].equals("yes") && values2[4][i].equals(value)) {
					if (value.equals("yes")) {
						achesYtons++;
					} else {
						achesYnotons++;
					}
				}
				
				if (values2[col][i].equals("no") && values2[4][i].equals(value)) {
					if (value.equals("yes")) {
						achesNtons++;
					} else {
						achesNnotons++;
					}
				}
				

			}

			
		// Sore Throat
		
			for (int i = 0,col=3; i < rows; i++) {

				if (values2[col][i].equals("yes") && values2[4][i].equals(value)) {
					if (value.equals("yes")) {
						throatYtons++;
					} else {
						throatYnotons++;
					}
				}
				
				if (values2[col][i].equals("no") && values2[4][i].equals(value)) {
					if (value.equals("yes")) {
						throatNtons++;
					} else {
						throatNnotons++;
					}
				}

			}
			

		}

	

	public void appendFile(String filename) {

	}

}
