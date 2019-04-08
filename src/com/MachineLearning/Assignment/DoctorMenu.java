package com.MachineLearning.Assignment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DoctorMenu extends JFrame implements ActionListener 
{

	// Variables
	String patients;
	// Labels
	private JLabel fileLabel;

	// Buttons
	private JButton addPatient;
	private JButton showAll;
	private JButton deleteAll;
	private JButton goHome;

	// Text Fields
	private JTextField fileName;
	private JTextArea display;
	private JScrollPane scroll;           /*"JScrollPane", source: stackoverflow.com*/

	// create a section of screen (panel) that will hold some GUI components
	JPanel PanelN = new JPanel();
	JPanel PanelS = new JPanel();
	JPanel PanelC = new JPanel();

//************************************ CONSTRUCTOR *****************************************************
	
	public DoctorMenu()
	{
		super("Doctor Menu");

		// sets the screen layout - in this case, border layout
		setLayout(new BorderLayout());

//------------------------INSTANTIATE VARIABLES------------------------------------------------------------------------------------------		

		// create a label
		fileLabel = new JLabel("Enter file name");

		// Instantiate new buttons

		addPatient = new JButton("Add Patients");
		addPatient.setPreferredSize(new Dimension(125, 40));
		addPatient.addActionListener(this);

		showAll = new JButton("Show All Patients");
		showAll.setPreferredSize(new Dimension(250, 40));
		showAll.addActionListener(this);

		deleteAll = new JButton("Clear Screen");
		deleteAll.setPreferredSize(new Dimension(250, 40));
		deleteAll.addActionListener(this);

		goHome = new JButton("Home");
		goHome.setPreferredSize(new Dimension(125, 40));
		goHome.addActionListener(this);

		// Instantiate text fields
		fileName = new JTextField();
		fileName.setPreferredSize(new Dimension(120, 30));
		fileName.addActionListener(this);
		fileName.setToolTipText("Patient file name");

		display = new JTextArea();
		scroll = new JScrollPane(display);
		scroll.setPreferredSize(new Dimension(300, 320));
		
//------------------------ SET SCREEN SIZE + ADD ITEMS TO PANELS ------------------------------------------------------------------------------------------		

		// set the location of the screen
		setLocation(500, 100);

		// Define the size of the frame
		setSize(310, 550);
		this.setMinimumSize(new Dimension(320, 560));

		// add everything to the panels
		// Remember that once you add to the panel you cant resize
		PanelN.add(fileLabel);
		PanelN.add(fileName);
		PanelC.add(showAll);
		PanelC.add(deleteAll);
		PanelC.add(scroll);
		PanelS.add(addPatient);
		PanelS.add(goHome);

		// add the panel to the screen ,set background colour and panel dimensions
		add(PanelN, BorderLayout.NORTH);
		PanelN.setBackground(Color.lightGray);
		PanelS.setPreferredSize(new Dimension(300, 50));
		add(PanelS, BorderLayout.SOUTH);
		PanelS.setBackground(Color.gray);
		add(PanelC, BorderLayout.CENTER);
		PanelC.setBackground(Color.lightGray);
		add(PanelC, BorderLayout.CENTER);
		PanelC.setBackground(Color.lightGray);

		// make the screen appear - without this, it doesn't!

		setVisible(true);

	}

//************************************ EVENT HANDLING *****************************************************

	public void actionPerformed(ActionEvent e)
	{

		if (e.getSource() == goHome)
		{

			MainMenu m1 = new MainMenu("Main Menu");
			this.dispose();
			
		}else if (e.getSource() == addPatient) 
		{

			Model m1 = new Model();
			
			m1.appendFile(fileName.getText());
			JOptionPane.showMessageDialog(this, "Patients added!");
		}
		else if(e.getSource() == showAll)
		{
			Model m2 = new Model();
			
			
			patients = "";
			
			for(int i = 0;i< m2.getLineCount() ;i++)
			{
				patients = patients +"	Patient: ";
				
				for(int j = 0; j<m2.getCols(); j++)
				{
					patients = patients +m2.getFileValues()[i][j]+" | ";
				}
				
				patients = patients +"\n";
			}
			
			display.setText("The list of patients are:\n"+patients);
		}
		else if(e.getSource() == deleteAll) 
		{
			
			display.setText("");
		}

	}

}
