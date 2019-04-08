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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PatientMenu extends JFrame implements ActionListener {

	// Variables
	private String[] tempOptions = { "hot", "normal", "cool" };
	private String[] achesOptions = { "yes", "no" };
	private String[] throatOptions = { "yes", "no" };

	// Labels
	private JLabel nameLabel;
	private JLabel tempLabel;
	private JLabel achesLabel;
	private JLabel throatLabel;

	// Buttons
	private JButton submit;
	private JButton goHome;

	// Text Fields
	private JTextField nameInput;

	// Combo Boxes
	private JComboBox temp ;
	private JComboBox aches;
	private JComboBox sorethroat;
	
	// create a section of screen (panel) that will hold some GUI components
	JPanel PanelN = new JPanel();
	JPanel PanelS = new JPanel();
	JPanel PanelC = new JPanel();

	public PatientMenu() {

		super("Patient Menu");

		// sets the screen layout - in this case, border layout
		setLayout(new BorderLayout());

		// create a label
		nameLabel = new JLabel("Enter your name");
		tempLabel = new JLabel("Choose your temperature");
		achesLabel = new JLabel("Do you have aches?");
		throatLabel = new JLabel("Is your throat sore?");

		// Instantiate new buttons

		submit = new JButton("Submit");
		submit.setPreferredSize(new Dimension(125, 40));
		submit.addActionListener(this);

		goHome = new JButton("Home");
		goHome.setPreferredSize(new Dimension(125, 40));
		goHome.addActionListener(this);

		// Instantiate text fields
		nameInput = new JTextField();
		nameInput.setPreferredSize(new Dimension(120, 30));
		nameInput.addActionListener(this);
		nameInput.setToolTipText("Enter your name");

		// Instantiate combo boxes
		temp = new JComboBox(tempOptions);
		aches = new JComboBox(achesOptions);
		sorethroat = new JComboBox(throatOptions);

		// set the location of the screen
		setLocation(500, 100);

		// Define the size of the frame
		setSize(280, 350);
		this.setMinimumSize(new Dimension(280, 350));

		// add everything to the panels
		// Remember that once you add to the panel you cant resize
		PanelN.add(nameLabel);
		PanelN.add(nameInput);
		PanelC.add(tempLabel);
		PanelC.add(temp);
		PanelC.add(achesLabel);
		PanelC.add(aches);
		PanelC.add(throatLabel);
		PanelC.add(sorethroat);
		PanelS.add(submit);
		PanelS.add(goHome);

		// add the panel to the screen ,set background colour and panel dimensions
		add(PanelN, BorderLayout.NORTH);
		PanelN.setBackground(Color.lightGray);
		PanelS.setPreferredSize(new Dimension(300, 50));
		add(PanelS, BorderLayout.SOUTH);
		PanelS.setBackground(Color.gray);
		add(PanelC, BorderLayout.CENTER);
		PanelC.setBackground(Color.lightGray);

		// make the screen appear - without this, it doesn't!

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == goHome) {

			MainMenu m1 = new MainMenu("Main Menu");
			this.dispose();
		} else if (e.getSource() == submit) {
			
			Patient newPat = new Patient(nameInput.getText(), (String) temp.getSelectedItem(),
					 (String) aches.getSelectedItem(), (String) sorethroat.getSelectedItem());
			//JOptionPane.showMessageDialog(this, "You are now saved as: " + newPat);
			
			//Rounds the result to the nearest whole number and displays to the user
			JOptionPane.showMessageDialog(this,newPat.getpName()+ "\nYou have a : " + Math.round(newPat.checkPatient() * 100) + "%  Chance to have Tonsilitis");
			
			
		}

	}

}
