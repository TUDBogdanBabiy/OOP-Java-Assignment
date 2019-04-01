package com.MachineLearning.Assignment;

public class Patient {
	
	//Attributes
	private String pName;
	private int pID;
	private static int currentNum = 0;
	private String temp;
	private String aches;
	private String throat;
	
	public Patient(String pName,String temp,String aches,String throat) {
		
		currentNum++;
		this.pName = pName;
		this.temp = temp;
		this.aches = aches;
		this.throat = throat;
		this.pID = currentNum;
		
	}
	
	public double checkPatient() {
		double result = 0;
		return result;
	}

	@Override
	public String toString() {
		return "\nPatient: " + pID + "\n"
				+ "Name: " + pName + "\n"
				+ "temp: " + temp + "\n"
				+ "aches: " + aches + "\n"
				+ "throat: " + throat;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public int getpID() {
		return pID;
	}

	public void setpID(int pID) {
		this.pID = pID;
	}

	public static int getCurrentNum() {
		return currentNum;
	}

	public static void setCurrentNum(int currentNum) {
		Patient.currentNum = currentNum;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getAches() {
		return aches;
	}

	public void setAches(String aches) {
		this.aches = aches;
	}

	public String getThroat() {
		return throat;
	}

	public void setThroat(String throat) {
		this.throat = throat;
	}
	
	

}
