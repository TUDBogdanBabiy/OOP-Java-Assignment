package com.MachineLearning.Assignment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainMenu extends JFrame implements ActionListener 
{

	private JButton patient;
	private JButton doctor;
	private JButton developer;

	JPanel PanelN = new JPanel();
	JPanel PanelC = new JPanel();
	JPanel PanelS = new JPanel();
	
//************************************ CONSTRUCTOR *****************************************************
	
	public MainMenu(String title)
	{
		super(title);

		// sets the screen layout - in this case, border layout
		setLayout(new BorderLayout());

	
//------------------------INSTANTIATE VARIABLES------------------------------------------------------------------------------------------		

		patient = new JButton("I'm a patient");
		patient.setPreferredSize(new Dimension(1000, 80));
		patient.addActionListener(this);

		doctor = new JButton("I'm a doctor");
		doctor.setPreferredSize(new Dimension(1000, 80));
		doctor.addActionListener(this);

		developer = new JButton("I'm a developer");
		developer.setPreferredSize(new Dimension(1000, 80));
		developer.addActionListener(this);
		
//------------------------ SET SCREEN SIZE + ADD ITEMS TO PANELS ------------------------------------------------------------------------------------------		
		
		
		// set the location of the screen
		setLocation(500, 100);

		// Define the size of the frame
		setSize(280, 350);
		this.setMinimumSize(new Dimension(280, 350));

		
		// add the panel to the screen ,set background colour and panel dimensions

		add(PanelC, BorderLayout.CENTER);
		PanelC.setBackground(Color.gray);

		PanelC.add(patient);
		PanelC.add(doctor);
		PanelC.add(developer);

		setVisible(true);
	}
	
//************************************ EVENT HANDLING *****************************************************


	public void actionPerformed(ActionEvent e)
	{

		// This will allow to switch between menus
		
		if (e.getSource() == patient) 
		{

			PatientMenu pm = new PatientMenu();
			this.dispose();
		} else if (e.getSource() == doctor)
		{

			DoctorMenu dm = new DoctorMenu();
			this.dispose();
			
		} else if (e.getSource() == developer) 
		{

			DeveloperMenu devm = new DeveloperMenu();
			this.dispose();
		}

	}

}
