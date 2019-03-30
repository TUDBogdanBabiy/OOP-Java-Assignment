package com.MachineLearning.Assignment;

public class Main {

	public static void main(String[] args) {
		
		MainMenu m1 = new MainMenu("Main Menu");
		
		m1.dispose();
		
		PatientMenu pm = new PatientMenu("Patient Menu");
		
		pm.dispose();
		
		DoctorMenu dm  = new DoctorMenu();
	}

}
