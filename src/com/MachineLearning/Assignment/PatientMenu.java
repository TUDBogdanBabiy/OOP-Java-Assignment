package com.MachineLearning.Assignment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PatientMenu extends JFrame implements ActionListener{
	
	//Attributes
	private JLabel nameLabel;
	private JButton submit;
	private JTextField nameInput;
	
	private String[] tempOptions = {"hot","normal","cool"};
	private String[] achesOptions  = {"yes","no"};
	private String[] throatOptions = {"yes","no"};
	
	private JComboBox temp = new JComboBox(tempOptions);
	private JComboBox aches = new JComboBox(achesOptions);
	private JComboBox sorethroat = new JComboBox(throatOptions);
	
	// create a section of screen (panel) that will hold some GUI components 
	   JPanel PanelN = new JPanel();
	   JPanel PanelS = new JPanel();
	   JPanel PanelC = new JPanel();
	   
	   
	public PatientMenu(String title) {
		
		super(title);
		
		// sets the screen layout  - in this case, border layout
		   setLayout(new BorderLayout());
		   
		   // create a label
		   nameLabel  = new JLabel("Enter your name");
		   
		   // add the labels 
		   // Remember that once you add to the panel you cant resize
		   PanelN.add(nameLabel); 
		   
		   
		// add the panel to the screen ,set background colour and panel dimensions
		   add(PanelN, BorderLayout.NORTH);
		   PanelN.setBackground(Color.lightGray);
		   PanelS.setPreferredSize(new Dimension(300,50));
		   add(PanelS, BorderLayout.SOUTH);
		   PanelS.setBackground(Color.gray);
		   add(PanelC, BorderLayout.CENTER);
		   PanelC.setBackground(Color.lightGray);

			// set the location of the screen  
		   setLocation(500,100);

		   // Define the size of the frame  
		   setSize(250,300);
		   
		 //Instantiate new buttons
		   
		   submit = new JButton("Submit Symptoms");
		   submit.setPreferredSize(new Dimension(100, 40));
		   submit.addActionListener(this);
		   
		 //Add Buttons to the panels and add listener  
		   PanelS.add(submit);
		   
		   nameInput = new JTextField();
		   nameInput.setPreferredSize(new Dimension(120, 30));
		   PanelN.add(nameInput,BorderLayout.CENTER);
		   nameInput.addActionListener(this);
		   nameInput.setToolTipText("Enter your name");
		   
		// make the screen appear - without this, it doesn't!  
		   
		   setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
