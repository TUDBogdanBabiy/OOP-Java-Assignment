# OOP-Java-Assignment: Machine Learning

Author: Bogdan Babiy

ID: C17355926

Github: https://github.com/TUDBogdanBabiy/OOP-Java-Assignment  (Will go public after upload)

Youtube: https://youtu.be/2bwXBnUmpFM

------------------------------------- INTRO ---------------------------------------------------------------

	This README file will go through all my classes and explain why I used  specific code.



------------------------------------- MAIN.JAVA ---------------------------------------------------------------

	This class is my control class where I call my main menu class for the first time.
	I instantiate a model class to reset the train data file in case it was modified and the program
	was closed.
	
	
	
------------------------------------- MAIN-MENU.JAVA ---------------------------------------------------------------

	This is the main menu class where the user is given a login frame. This login system
	is used to get into the doctor menu and developer menu. The user can click the button 
	"Im a Patient"	to move to the patient menu. 
	
	Doctor Menu - Username:doctor Password:doc
	
	Developer Menu - Username:developer Password:dev
	
	
	
------------------------------------- PATIENT-MENU.JAVA ---------------------------------------------------------------

	This class will allow the user to select their symptoms using JComboBox.
	This is used to avoid unnecessary error checking. The name JTextField is entirely optional
	so the user can leave it blank or input irrelevant characters.
	
	When the user clicks submit, a new patient is instantiated with the values taken from the combo box.
	After that we call the checkPatient() function to calculate the probability. That answer is multiplied by 100
	to show as percentage. I used the round() function to round the value to the nearest whole number.
	
	
	
------------------------------------- PATIENT.JAVA ---------------------------------------------------------------

	This is a short class that hold the patient symptoms as strings. This class uses one method that 
	instantiates a new Model class and calls the calcProbability method passing the symptoms.
	
	
	
------------------------------------- MODEL.JAVA ---------------------------------------------------------------

	This class acts as both a model class and a file processor class.
	
	Instantiating this class reads from a csv file where all the data is stored.
	The path is hard coded because the user wont be allowed to modify or read that file.
	
	First the model scans the file to count its rows and columns. This is used to
	later instantiate a 2D array, since arrays are not dynamic, this is needed.
	
	We then open the scanner from the begining to copy the file contents into the 2D array.
	I used line.split to split up each line and then save that line as a row in the 2D array.
	
	You will see two calcProbabilities methods, I used method overloading to use one for
	the patient and the other for testing the model later on.
	
	The appendFile method will open a file chooser for the user to pick what file they want to append.
	The user is blocked from appending the files other than the newpatients.csv


	
------------------------------------- DOCTOR-MENU.JAVA ---------------------------------------------------------------

	Using this class a doctor user can view all current patients and add a new data set
	to the current one.
	
	The show all button will load all the patient data into a single String and then sets the 
	jtext area to that string.
	
	
------------------------------------- DEVELOPER-MENU.JAVA ---------------------------------------------------------------

	This class gives the developer user two buttons, one for testing the model and another to manually 
	reset the patient data file.

	
	