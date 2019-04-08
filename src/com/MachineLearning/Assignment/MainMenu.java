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
import javax.swing.JTextField;

public class MainMenu extends JFrame implements ActionListener 
{
	
	private JLabel user;
	private JLabel pass;
	
	private JButton patient;
	private JButton login;
	
	private JTextField userName;
	private JTextField passWord;
	
	

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
		patient.setPreferredSize(new Dimension(125, 40));
		patient.addActionListener(this);
		
		login = new JButton("Login");
		login.setPreferredSize(new Dimension(125, 40));
		login.addActionListener(this);
		
		userName = new JTextField();
		userName.setPreferredSize(new Dimension(150, 30));
		userName.addActionListener(this);
		userName.setToolTipText("Enter Username");
		
		passWord = new JTextField();
		passWord.setPreferredSize(new Dimension(150, 30));
		passWord.addActionListener(this);
		passWord.setToolTipText("Enter Password");
		
		user = new JLabel("Username");
		pass = new JLabel("Password");

		
		
//------------------------ SET SCREEN SIZE + ADD ITEMS TO PANELS ------------------------------------------------------------------------------------------		
		
		
		// set the location of the screen
		setLocation(500, 100);

		// Define the size of the frame
		setSize(280, 250);
		this.setMinimumSize(new Dimension(270, 250));

		
		// add the panel to the screen ,set background colour and panel dimensions

		add(PanelC, BorderLayout.CENTER);
		PanelC.setBackground(Color.LIGHT_GRAY);
		
		add(PanelS, BorderLayout.SOUTH);
		PanelS.setBackground(Color.gray);
		

		PanelC.add(user);
		PanelC.add(userName);
		PanelC.add(pass);
		PanelC.add(passWord);
		PanelS.add(login);
		PanelS.add(patient);
		

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
			
		} else if (e.getSource() == login ||e.getSource() == userName ||e.getSource() == passWord)
		{
			
			if(userName.getText().equals("doctor")&&passWord.getText().equals("doc"))
			{
				DoctorMenu dm = new DoctorMenu();
				this.dispose();
			}
			else if(userName.getText().equals("developer")&&passWord.getText().equals("dev"))
			{
				DeveloperMenu devm = new DeveloperMenu();
				this.dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Incorrect Username or Password");
				userName.setText("");
				passWord.setText("");
			}
			
		}	


	}

}
