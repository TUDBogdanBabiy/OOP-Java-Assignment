package com.MachineLearning.Assignment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Model
{
	
	File trainData;
	Scanner myReader;
	String[] values ;
	String[][] values2; 
	int lineCount= 0;
	int cols;
	int rows =0;
	private static ArrayList <ArrayList<String>> lines = new ArrayList ();//2D array list
	
	public Model() 
	{
		trainData = new File("Patient_Files\\TrainData.csv");
		try 
		{
			myReader = new Scanner(trainData);
			
			//Scan the File to find amount of rows and columns
			while(myReader.hasNextLine())
			{
				
				String line = myReader.nextLine();
				values = line.split(",");
				
				rows++;
			}
			cols = values.length;
			myReader.close();
			
			//Reopen the scanner from the beginning
			myReader = new Scanner(trainData);
			
			values2 = new String[rows][cols];
			while(myReader.hasNextLine())
			{
				
				String line = myReader.nextLine();
				
				//Split the line and save into array
				values = line.split(",");
				
				
				for(int i=0;i< cols;i++) {
					values2[lineCount][i] = values[i];
				}
				
				lineCount++;
			}
			
		}
		catch(IOException e) 
		{
			
			System.out.println("\nCannot find file");
			
		}//End try catch
		
		
        for(int i =0; i<lineCount ;i++)
        {
            System.out.print("\n-------------------------------------\n");
            
            for (int j=0;j<values.length;j++) 
            {
                System.out.print(values2[i][j] );
                
            }
           
        }
		
	}
	
	public double calcProbability()
	{
		
		return 0;
	}

}
