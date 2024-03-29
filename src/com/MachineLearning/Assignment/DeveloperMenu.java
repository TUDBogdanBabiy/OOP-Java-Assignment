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

public class DeveloperMenu extends JFrame implements ActionListener 
{

	// Labels
	private JLabel accuracy;

	// Buttons
	private JButton showAccuracy;
	private JButton goHome;
	private JButton reset;

	// create a section of screen/7 (panel) that will hold some GUI components
	JPanel PanelN = new JPanel();
	JPanel PanelS = new JPanel();
	JPanel PanelC = new JPanel();

//************************************ CONSTRUCTOR *****************************************************
	
	public DeveloperMenu() 
	{

		super("Developer Menu");

		// sets the screen layout - in this case, border layout
		setLayout(new BorderLayout());

//------------------------INSTANTIATE VARIABLES------------------------------------------------------------------------------------------		

		// create a label
		accuracy = new JLabel("Click Button To Test Model Accuracy");
		accuracy.setPreferredSize(new Dimension(210, 40));

		// Instantiate new buttons

		showAccuracy = new JButton("Test Model");
		showAccuracy.setPreferredSize(new Dimension(125, 40));
		showAccuracy.addActionListener(this);

		goHome = new JButton("Home");
		goHome.setPreferredSize(new Dimension(125, 40));
		goHome.addActionListener(this);
		
		reset = new JButton("Reset File");
		reset.setPreferredSize(new Dimension(125, 40));
		reset.addActionListener(this);

//------------------------ SET SCREEN SIZE + ADD ITEMS TO PANELS ------------------------------------------------------------------------------------------		

		// set the location of the screen
		setLocation(500, 100);

		// Define the size of the frame
		setSize(280, 350);
		this.setMinimumSize(new Dimension(280, 350));

		// add everything to the panels
		// Remember that once you add to the panel you cant resize
		PanelN.add(accuracy);
		PanelC.add(showAccuracy);
		PanelC.add(reset);
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
			
		}else if (e.getSource() == showAccuracy)
		{

			Model m1 = new Model();
			
			JOptionPane.showMessageDialog(this, "Model Accuracy is at: "+Math.round(m1.testModel())+"%");
			
		}else if (e.getSource() == reset) 
		{

			Model m1 = new Model();
			m1.reset();
			
			JOptionPane.showMessageDialog(this, "File Reset");
		}

	}

}
