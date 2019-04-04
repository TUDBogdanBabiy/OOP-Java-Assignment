package com.MachineLearning.Assignment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Model
{
	//Counters 
	
	int lineCount = 0;
	int cols;
	double valueYesCounter = 0;
	double valueNoCounter = 0;
	int probArrayCounter = 0;

	//File related attributes
	File trainData;
	Scanner myReader;
	static String[] lineValues;    // Stores the split line values 
	static String[][] fileVaues; // Stores the file values


	double[] yesProbabilities;
	double[]noProbabilities;

	// Yes | No counters
	double tonYes = 0;
	double tonNo  = 0;

	// Final probability decimal values
	double pTons;
	double pNoTons;
//**************************************** THE CONSTRUCTOR **************************************************
	public Model() 
	{
		trainData = new File("Patient_Files\\TrainData.csv");
		
		try 
		{
			myReader = new Scanner(trainData);

//-----------------------SCAN FILE FOR LINE AND COLUMN COUNT-------------------------------------------------------------------------------------------		

			while (myReader.hasNextLine()) 
			{
				// Splits the line into separate values and stores in the array lineValues
				String line = myReader.nextLine();
				lineValues = line.split(",");
				lineCount++;
			}
			
			cols = lineValues.length;

			// Reopen the scanner from the beginning
			myReader = new Scanner(trainData);

			// Initialises 2D array size here to make them dynamic
			
			fileVaues = new String[lineCount][cols];
			yesProbabilities = new double[cols];
			noProbabilities = new double[cols];
			lineCount = 0;
			
//------------------------COPY FILE INTO 2D ARRAY------------------------------------------------------------------------------------------		
			
			while (myReader.hasNextLine())
			{
				String line = myReader.nextLine();

				// Split the line and save into array
				lineValues = line.split(",");
				
				// Fill the 2D array row by row
				for (int i = 0; i < cols; i++)
				{
					fileVaues[lineCount][i] = lineValues[i];

					// Count yes
					if (i == 4 && lineValues[i].equals("yes")) 
					{
						tonYes++;
					}
				}

				lineCount++;
			}

			tonNo =  lineCount - tonYes;

		} 
		catch (IOException e) 
		{
			System.out.println("\nCannot find file");

		} // End try catch
		
//------------------------PRINTS 2D ARRAY ------------------------------------------------------------------------------------------		

		// Prints 2D array

		for (int i = 0; i < lineCount; i++) 
		{
			System.out.print("\n-------------------------------------\n");

			for (int j = 0; j < lineValues.length; j++)
			{
				System.out.print(fileVaues[i][j]+" | ");

			}

		}
		
	}
//********************************* CALCULATE PROBABILITY *******************************************************
	
	public double calcProbability(String temp, String aches, String throat)
	{
		// Counts the probability of each column
		countCol("temp",temp,aches,throat);
		countCol("aches",temp,aches,throat);
		countCol("throat",temp,aches,throat);

		// Multiplies the array of probabilities
		
		pTons   = multiply(yesProbabilities);
		pNoTons = multiply(noProbabilities);
	
		pTons = pTons/(pTons+pNoTons);
		
		System.out.println("\n\nChance of Tonsilitis: "+pTons*100+" %");
		
		return pTons;
	}
//********************************* MULTIPLY ARRAY OF PROBABILITIES ***************************************************
	
	public double multiply(double[] list) 
	{
		double total = list[0];
		
		for (int i = 0; i < 3; i++)
		{
			total = total * list[i+1] ;
		}
		
		return total;
	}
//*********************************** FIND COLUMN PROBABILITIES *****************************************************
	
	public void countCol(String colName, String temp, String aches, String throat) 
	{
//---------------------------EXPAND COLUMN TEMP---------------------------------------------------------------------------------------		
		
		if(colName.equals("temp"))
		{
			// Count probability of the value temp and yes
			for (int i = 0; i < lineCount; i++)
			{
				if (fileVaues[i][1].equals(temp) && fileVaues[i][4].equals("yes")) 
				{
					valueYesCounter++;
				}
			}
			
			// Count probability of the value temp and no
			for (int i = 0; i < lineCount; i++)
			{
				if (fileVaues[i][1].equals(temp) && fileVaues[i][4].equals("no"))
				{
					valueNoCounter++;
				}
			}
			
			//Add probabilities to array
			yesProbabilities[probArrayCounter] = ( (valueYesCounter / tonYes));
			noProbabilities[probArrayCounter]  = ( (valueNoCounter / tonNo));
			
			probArrayCounter++;
			valueYesCounter = 0;
			valueNoCounter  = 0;
			
		}
//-------------------------EXPAND COLUMN ACHES-----------------------------------------------------------------------------------------		
		
		else if(colName.equals("aches"))
		{
			// Count probability of the value aches and yes
			for (int i = 0; i < lineCount; i++)
			{
				if (fileVaues[i][2].equals(aches) && fileVaues[i][4].equals("yes")) 
				{
					valueYesCounter++;
					
				}
			}
			
			// Count probability of the value aches and no
			for (int i = 0; i < lineCount; i++) 
			{
				if (fileVaues[i][2].equals(aches) && fileVaues[i][4].equals("no")) 
				{
					valueNoCounter++;
				}
			}
			
			//Add probabilities to array
			yesProbabilities[probArrayCounter] =((valueYesCounter / tonYes));
			noProbabilities[probArrayCounter]  = ( (valueNoCounter / tonNo));
			
			probArrayCounter++;
			valueYesCounter = 0;
			valueNoCounter  = 0;
		}
//------------------------EXPAND COLUMN THROAT------------------------------------------------------------------------------------------		
		
		else if(colName.equals("throat"))
		{
			// Count probability of the value throat and yes
			for (int i = 0; i < lineCount; i++) 
			{
				if (fileVaues[i][3].equals(throat) && fileVaues[i][4].equals("yes"))
				{
					valueYesCounter++;
				}

			}
			
			// Count probability of the value throat and no
			for (int i = 0; i < lineCount; i++)
			{
				if (fileVaues[i][3].equals(throat) && fileVaues[i][4].equals("no"))
				{
					valueNoCounter++;
				}

			}
			
			//Add probabilities to array
			yesProbabilities[probArrayCounter] =( (valueYesCounter / tonYes));
			noProbabilities[probArrayCounter] = ( (valueNoCounter / tonNo));
			
			probArrayCounter++;
			valueYesCounter = 0;
			valueNoCounter = 0;
		}
		
		yesProbabilities[probArrayCounter] =( tonYes / lineCount);
		noProbabilities[probArrayCounter] =( tonNo / lineCount);
	}
//************************************ APPEND NEW FILE *****************************************************
	
public void appendFile(String filename)
	{

	}

}
