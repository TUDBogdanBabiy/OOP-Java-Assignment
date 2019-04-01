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
	String[] values = new String[6];
	String[][] values2 = new String[100][6];
	int lineCount= -1;
	private static ArrayList <ArrayList<String>> lines = new ArrayList ();//2D array list
	
	public Model() 
	{
		trainData = new File("Patient_Files\\TrainData.csv");
		try 
		{
			myReader = new Scanner(trainData);
			
			while(myReader.hasNextLine())
			{
				lineCount++;
				String line = myReader.nextLine();
				
				//Split the line and save into array
				values = line.split(",");
				
				
				for(int i=0;i< values.length;i++) {
					values2[lineCount][i] = values[i];
				}
				
				
			}
			
		}
		catch(IOException e) 
		{
			
			System.out.println("\nCannot find file");
			
		}//End try catch
		
		
        for(int i =0; i<lineCount ;i++)
        {
            System.out.print("\n-------------------------------\n");
            
            for (int j=0;j<values.length;j++) 
            {
                System.out.print(values2[i][j]+"|" );
                
            }
           
        }
		
	}
	
	public double calcProbability()
	{
		
		return 0;
	}

}
