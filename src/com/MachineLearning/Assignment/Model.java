package com.MachineLearning.Assignment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Model
{
	//Counters 
	
	private int lineCount = 0;
	private int cols;
	private double valueYesCounter = 0; //Used for self evaluation feature
	private double valueNoCounter  = 0;
	private int probArrayCounter   = 0;

	//File related attributes
	private File patientData;
	private File extern;
	private File default_data;
	
	private Scanner fileScanner;
	private FileWriter fr;
	
	//Arrays 
	private String[] lineValues;    // Stores the split line values 
	private String[][] fileValues;  // Stores the file values
	private String[][]trainData;	// Holds 70% of fileValues

	private double[] yesProbabilities;
	private double[]noProbabilities;

	// Yes | No counters
	private double tonYes = 0;
	private double tonNo  = 0;

	// Final probability decimal values
	private double pTons;
	private double pNoTons;
	
//**************************************** THE CONSTRUCTOR **************************************************
	public Model() 
	{
		patientData = new File("Patient_Files\\TrainData.csv");
		
		try 
		{
			fileScanner = new Scanner(patientData);

//-----------------------SCAN FILE FOR LINE AND COLUMN COUNT-------------------------------------------------------------------------------------------		

			while (fileScanner.hasNextLine()) 
			{
				// Splits the line into separate values and stores in the array lineValues
				String line = fileScanner.nextLine();
				lineValues = line.split(",");																 /* "line.split()", source: geekforgeeks.org */
				lineCount++;
			}
			
			cols = lineValues.length;

			// Reopen the scanner from the beginning
			fileScanner = new Scanner(patientData);

			// Initialises 2D array size here to make them dynamic
			
			fileValues = new String[lineCount][cols];
			trainData = new String[ (int)(lineCount* 0.7)][cols];// train data will be 70% of the overall data set
			yesProbabilities = new double[cols];
			noProbabilities = new double[cols];
			lineCount = 0;
			
//------------------------COPY FILE INTO 2D ARRAY------------------------------------------------------------------------------------------		
			
			while (fileScanner.hasNextLine())
			{
				String line = fileScanner.nextLine();

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
			
			fileScanner.close();

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
	
public boolean appendFile()
	{
	int patientID = lineCount;
	JFileChooser chooser = new JFileChooser();
	
	chooser.setCurrentDirectory(new File("C:\\Users\\lemon\\Documents\\GitHub\\OOP-Java-Assignment\\Patient_Files"));

	FileNameExtensionFilter filter = new FileNameExtensionFilter(
		    "Only csv", "csv");
		chooser.setFileFilter(filter);
		
		int returnVal = chooser.showOpenDialog(null);
		
	
		if(returnVal == JFileChooser.APPROVE_OPTION)
		{
		   System.out.println("You chose to open this file: " +
		        chooser.getSelectedFile().getPath());
		}

	extern = new File(chooser.getSelectedFile().getAbsolutePath());
	
	try
	{
		fileScanner = new Scanner(extern);
		fr = new FileWriter(patientData,true);
		
		while(fileScanner.hasNext())
		{
			patientID++;
			fr.write(System.lineSeparator()+patientID+","+fileScanner.nextLine()); 					/* "System.lineSeparator()", source: geekforgeeks.org */
		}
		
		fr.close();
		
		fileScanner = new Scanner(patientData);
		while(fileScanner.hasNext())
		{
			System.out.println(fileScanner.nextLine());
		}
		fileScanner.close();
	}
	catch (IOException e) 
	{
		System.out.println("\nCannot find file");
		return false ;

	} // End try catch
	return true;
	
	
	
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

//************************************ RESET PATIENT FILE  *****************************************************

public void reset() 
{
		// Copy of the starting data set, will be used to reset the main patient file
		default_data = new File("Patient_Files\\TrainDataDefault.csv");
		
		try
		{
			fileScanner = new Scanner(default_data);
			fr = new FileWriter(patientData);
			
			//Clears the patient file and copies the contents of the default file to it
			
			while(fileScanner.hasNext())
			{
				
				fr.write(fileScanner.nextLine());
				
				// Will stop making new lines after it reaches the end of the default file
				// This avoids printing a blank line at the end of the file
				
				if(!fileScanner.hasNext())
				{
					
				}
				else
				{
					fr.write(System.lineSeparator());
				}
				
			
			}
			
			
			
			fr.close();
			
		}
		catch (IOException e) 
		{
			System.out.println("\nCannot find file");
	
		} // End try catch	
}

//************************************ GETTERS / SETTERS  *****************************************************

public int getLineCount() {
	return lineCount;
}

public void setLineCount(int lineCount) {
	this.lineCount = lineCount;
}

public int getCols() {
	return cols;
}

public void setCols(int cols) {
	this.cols = cols;
}

public double getValueYesCounter() {
	return valueYesCounter;
}

public void setValueYesCounter(double valueYesCounter) {
	this.valueYesCounter = valueYesCounter;
}

public double getValueNoCounter() {
	return valueNoCounter;
}

public void setValueNoCounter(double valueNoCounter) {
	this.valueNoCounter = valueNoCounter;
}

public int getProbArrayCounter() {
	return probArrayCounter;
}

public void setProbArrayCounter(int probArrayCounter) {
	this.probArrayCounter = probArrayCounter;
}

public File getPatientData() {
	return patientData;
}

public void setPatientData(File patientData) {
	this.patientData = patientData;
}

public File getExtern() {
	return extern;
}

public void setExtern(File extern) {
	this.extern = extern;
}

public File getDefault_data() {
	return default_data;
}

public void setDefault_data(File default_data) {
	this.default_data = default_data;
}

public Scanner getFileScanner() {
	return fileScanner;
}

public void setFileScanner(Scanner fileScanner) {
	this.fileScanner = fileScanner;
}

public FileWriter getFr() {
	return fr;
}

public void setFr(FileWriter fr) {
	this.fr = fr;
}

public String[] getLineValues() {
	return lineValues;
}

public void setLineValues(String[] lineValues) {
	this.lineValues = lineValues;
}

public String[][] getFileValues() {
	return fileValues;
}

public void setFileValues(String[][] fileValues) {
	this.fileValues = fileValues;
}

public String[][] getTrainData() {
	return trainData;
}

public void setTrainData(String[][] trainData) {
	this.trainData = trainData;
}

public double[] getYesProbabilities() {
	return yesProbabilities;
}

public void setYesProbabilities(double[] yesProbabilities) {
	this.yesProbabilities = yesProbabilities;
}

public double[] getNoProbabilities() {
	return noProbabilities;
}

public void setNoProbabilities(double[] noProbabilities) {
	this.noProbabilities = noProbabilities;
}

public double getTonYes() {
	return tonYes;
}

public void setTonYes(double tonYes) {
	this.tonYes = tonYes;
}

public double getTonNo() {
	return tonNo;
}

public void setTonNo(double tonNo) {
	this.tonNo = tonNo;
}

public double getpTons() {
	return pTons;
}

public void setpTons(double pTons) {
	this.pTons = pTons;
}

public double getpNoTons() {
	return pNoTons;
}

public void setpNoTons(double pNoTons) {
	this.pNoTons = pNoTons;
}



//************************************ END OF CLASS MODEL *****************************************************
}
