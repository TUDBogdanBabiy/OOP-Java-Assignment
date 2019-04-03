package com.MachineLearning.Assignment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Model {

	int lineCount = 0;
	int cols;
	int rows = 0;
	int valueCounter = 0;
	int probArrayCounter = 0;

	File trainData;
	Scanner myReader;
	static String[] values;
	static String[][] values2; // rows[] colums[]

	double[] yesProbabilities;
	double[]noProbabilities;

	int tonYes = 0;
	int tonNo = 0;

	double pTons;
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
			yesProbabilities = new double[cols];
			noProbabilities = new double[cols];

			while (myReader.hasNextLine()) {

				String line = myReader.nextLine();

				// Split the line and save into array
				values = line.split(",");
				
				

				// Fill the 2D array row by row
				for (int i = 0; i < cols; i++) {
					values2[lineCount][i] = values[i];

					// Count yes
					if (i == 4 && values[i].equals("yes")) {
						tonYes++;
					}
				}

				lineCount++;
			}

			tonNo = tonYes - rows;

		} catch (IOException e) {

			System.out.println("\nCannot find file");

		} // End try catch

		// Prints 2D array

		for (int i = 0; i < lineCount; i++) {
			System.out.print("\n-------------------------------------\n");

			for (int j = 0; j < values.length; j++) {
				System.out.print(values2[i][j]+" | ");

			}

		}
		
		//This should print the last element of the last array
		System.out.print("\n\nyes = "+values2[18][4]); //This will print the 18th row
		
		System.out.print("\n\nSize of values = "+values.length); //This will print the 18th row
		
		
	}

	public double calcProbability(String temp, String aches, String throat) {

		
		
		countCol("temp",temp,aches,throat);
		countCol("aches",temp,aches,throat);
		countCol("throat",temp,aches,throat);

		
		double pTons = multiply(yesProbabilities);
		double pNoTons = multiply(noProbabilities);

		
		pTons = pTons/(pTons+pNoTons);
		
		System.out.println("\n\nChance of Tonsilitis: "+pTons+" %");
		return pTons;

	}

	public double multiply(double[] list) {
		double total = list[0];
		
		
		System.out.println("\n:"+list.length+":");
		
				
		for (int i = 0; i < 3; i++) {
			
			total = total * list[i+1] ;
		}
		
		System.out.println("\n:"+total+":");
		return total;
	}

	public void countCol(String colName, String temp, String aches, String throat) {

		
		if(colName.equals("temp")) {
			
			for (int i = 0; i < lineCount; i++) {
				if (values2[i][1].equals(temp) && values2[i][4].equals("yes")) {
					valueCounter++;
				}
			}
			
			yesProbabilities[probArrayCounter] = ((double) (valueCounter / tonYes));
			valueCounter = tonYes - valueCounter;
			noProbabilities[probArrayCounter] = ((double) (valueCounter / tonYes));
			probArrayCounter++;
		}
		else if(colName.equals("aches")) {
			for (int i = 0; i < lineCount; i++) {
				if (values2[i][2].equals(aches) && values2[i][4].equals("yes")) {
					valueCounter++;
				}
			}
			
			yesProbabilities[probArrayCounter] =((double) (valueCounter / tonYes));
			valueCounter = tonYes - valueCounter;
			noProbabilities[probArrayCounter] =((double) (valueCounter / tonYes));
			probArrayCounter++;
		}
		else if(colName.equals("throat")) {
			for (int i = 0; i < lineCount; i++) {
				if (values2[i][3].equals(throat) && values2[i][4].equals("yes")) {
					valueCounter++;
				}

			}
			
			yesProbabilities[probArrayCounter] =((double) (valueCounter / tonYes));
			valueCounter = tonYes - valueCounter;
			noProbabilities[probArrayCounter] =((double) (valueCounter / tonYes));
			probArrayCounter++;
		}
		

		yesProbabilities[probArrayCounter] =((double) tonYes / lineCount);
		noProbabilities[probArrayCounter] =((double) tonNo / lineCount);
	}

	public void appendFile(String filename) {

	}

}
