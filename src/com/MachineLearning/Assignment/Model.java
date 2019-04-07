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
	File patientData;
	Scanner myReader;
	static String[] lineValues;    // Stores the split line values 
	static String[][] fileValues; // Stores the file values
	
	String[][]trainData;

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
		patientData = new File("Patient_Files\\TrainData.csv");
		
		try 
		{
			myReader = new Scanner(patientData);

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
			myReader = new Scanner(patientData);

			// Initialises 2D array size here to make them dynamic
			
			fileValues = new String[lineCount][cols];
			trainData = new String[ (int)(lineCount* 0.7)][cols];// train data will be 70% of the overall data set
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
					fileValues[lineCount][i] = lineValues[i];

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

			for (int j = 0; j < cols; j++)
			{
				System.out.print(fileValues[i][j]+" | ");

			}

		}
		
	}
//********************************* CALCULATE PROBABILITY *******************************************************
	
	public double calcProbability(String temp, String aches, String throat)
	{
		probArrayCounter =0;
		// Counts the probability of each column
		countCol("temp",temp,aches,throat,fileValues,lineCount,tonYes,tonNo);
		countCol("aches",temp,aches,throat,fileValues,lineCount,tonYes,tonNo);
		countCol("throat",temp,aches,throat,fileValues,lineCount,tonYes,tonNo);

		// Multiplies the array of probabilities
		
		pTons   = multiply(yesProbabilities);
		pNoTons = multiply(noProbabilities);
	
		pTons = pTons/(pTons+pNoTons);
		
		System.out.println("\n\nChance of Tonsilitis: "+pTons*100+" %");
		
		return pTons;
	}

//---------------------------CALCULATE PROBABILITY FOR TEST-DATA  ---------------------------------------------------------------------------------------		
// This method will  be used for the model self evaluation	
	
	public String calcProbability(String temp, String aches, String throat,String[][] trainData,int lines,double yesCount,double noCount)
	{
		probArrayCounter =0;
		// Counts the probability of each column
		countCol("temp",temp,aches,throat,trainData,lines,yesCount,noCount);
		countCol("aches",temp,aches,throat,trainData,lines,yesCount,noCount);
		countCol("throat",temp,aches,throat,trainData,lines,yesCount,noCount);

		// Multiplies the array of probabilities
		
		pTons   = multiply(yesProbabilities);
		pNoTons = multiply(noProbabilities);
	
		if(pTons > pNoTons)
		{
			return "yes";
		}
		else 
		{
			return "no";
		}
		
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
	
	public void countCol(String colName, String temp, String aches, String throat,String[][] trainData,int lineCount,double tonYes,double tonNo) 
	{
//---------------------------EXPAND COLUMN TEMP---------------------------------------------------------------------------------------		
		
		if(colName.equals("temp"))
		{
			// Count probability of the value temp and yes
			for (int i = 0; i < lineCount; i++)
			{
				if (trainData[i][1].equals(temp) && trainData[i][4].equals("yes")) 
				{
					valueYesCounter++;
				}
			}
			
			// Count probability of the value temp and no
			for (int i = 0; i < lineCount; i++)
			{
				if (trainData[i][1].equals(temp) && trainData[i][4].equals("no"))
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
				if (trainData[i][2].equals(aches) && trainData[i][4].equals("yes")) 
				{
					valueYesCounter++;
					
				}
			}
			
			// Count probability of the value aches and no
			for (int i = 0; i < lineCount; i++) 
			{
				if (trainData[i][2].equals(aches) && trainData[i][4].equals("no")) 
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
				if (trainData[i][3].equals(throat) && trainData[i][4].equals("yes"))
				{
					valueYesCounter++;
				}

			}
			
			// Count probability of the value throat and no
			for (int i = 0; i < lineCount; i++)
			{
				if (trainData[i][3].equals(throat) && trainData[i][4].equals("no"))
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

//************************************ TEST MODEL ACCURACY *****************************************************

public double testModel()
	{
		// Number of correct predictions and total amount of data being tested
		double success =0;
		double testAmount = 0;
		
		
		double yesCount = 0;
		double noCount = 0;
		
		// loads 70% of the file data into a new array
		loadTrainData();
		
		// Counts the number of yeses in the new array
		for (int i = 0; i < (int)(lineCount*0.7); i++) 
		{
			
			if(trainData[i][4].equals("yes")) 
			{
				yesCount++;
			}
			

		}
		noCount = ((int)(lineCount*0.7)) - yesCount;

//---------------------------TEST THE REST OF THE DATA SET USING THE TRAIN DATA ---------------------------------------------------------------------------------------		

		for(int i = (int)(lineCount*0.7);i<lineCount;i++)
		{
			
			if(fileValues[i][4].equals(calcProbability(fileValues[i][1],fileValues[i][2],fileValues[i][3],trainData,(int)(lineCount*0.7),yesCount,noCount)))
			{
				 success++;
			}
			testAmount++;
		}
		
		
		System.out.println("\nCorrect :"+ success+"\nTotal :"+ testAmount);
		
	return ((success/testAmount) * 100);		

	}

//************************************ LOAD 70% OF FILE DATA INTO TRAIN DATA  *****************************************************


private void loadTrainData()
{
	for (int i = 0; i < (int)(lineCount*0.7); i++) 
	{
		
		for (int j = 0; j < cols; j++)
		{
			trainData[i][j] = fileValues[i][j];

		}

	}
	
	
}
//************************************ END OF CLASS MODEL *****************************************************
}
