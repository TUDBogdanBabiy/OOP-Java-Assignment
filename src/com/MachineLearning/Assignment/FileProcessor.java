package com.MachineLearning.Assignment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileProcessor 
{
	private File fileData;
	private Scanner fileReader;
	private FileWriter fileWriter;
		
	public FileProcessor(String fileName) 
	{
		fileData = new File(fileName);
		
		try
		{
			fileReader = new Scanner(fileData);
		}
		catch(IOException e)
		{
			System.out.println("\nCannot find file");

		}
	}
	
	public boolean appendFile(String newFile ,int lineCount)
	{
		int patientID = lineCount;
		File extern = new File("Patient_Files\\"+newFile+".csv");
		
		try
		{
			fileReader = new Scanner(extern);
			fileWriter = new FileWriter(fileData,true);
			
			while(fileReader.hasNext())
			{
				patientID++;
				fileWriter.write(System.lineSeparator()+patientID+","+fileReader.nextLine()); 					/* "System.lineSeparator()", source: geekforgeeks.org */
			}
			
			fileWriter.close();
			fileReader.close();
			
			//Prints the modified file
			
			fileReader = new Scanner(fileData);
			while(fileReader.hasNext())
			{
				System.out.println(fileReader.nextLine());
			}
			
		}
		catch (IOException e) 
		{
			System.out.println("\nCannot find file");
			return false ;
	
		} // End try catch
		
		return true;
	
	}
	
	public void resetFile() 
	{
			// Copy of the starting data set, will be used to reset the main patient file
			File default_data = new File("Patient_Files\\TrainDataDefault.csv");
			
			
			try
			{
				fileReader = new Scanner(default_data);
				fileWriter = new FileWriter(fileData);
				
				//Clears the patient file and copies the contents of the default file to it
				
				while(fileReader.hasNext())
				{
					
					fileWriter.write(fileReader.nextLine());
					
					// Will stop making new lines after it reaches the end of the default file
					// This avoids printing a blank line at the end of the file
					
					if(!fileReader.hasNext())
					{
						
					}
					else
					{
						fileWriter.write(System.lineSeparator());
					}
					
				
				}
				
				
				
				fileWriter.close();
				
			}
			catch (IOException e) 
			{
				System.out.println("\nCannot find file");
		
			} // End try catch	
	}
	
	
	public void closeFile()
	{
		fileReader.close();
	}


}
