package com.MachineLearning.Assignment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DeveloperMenu extends JFrame implements ActionListener{

	//Labels
		private JLabel accuracy;
			

	//Buttons
		private JButton showAccuracy;
		
	// create a section of screen (panel) that will hold some GUI components 
	   JPanel PanelN = new JPanel();
	   JPanel PanelS = new JPanel();
	   JPanel PanelC = new JPanel();	
	public DeveloperMenu() {
		

		super("Developer Menu");
		
		// sets the screen layout  - in this case, border layout
		   setLayout(new BorderLayout());
		   
		// create a label
		   accuracy   = new JLabel("Click Button To Test Model Accuracy");
		   accuracy.setPreferredSize(new Dimension(210, 40));
		   
	    //Instantiate new buttons
		   
		   showAccuracy = new JButton("Test Model Accuracy");
		   showAccuracy.setPreferredSize(new Dimension(250, 40));
		   showAccuracy.addActionListener(this);
		   
		// set the location of the screen  
		   setLocation(500,100);

		 // Define the size of the frame  
		   setSize(280,350);
		   this.setMinimumSize(new Dimension(280,350));
		   
		// add everything to the panels
		// Remember that once you add to the panel you cant resize
		   PanelN.add(accuracy); 
		   PanelS.add(showAccuracy);	
		   
		// add the panel to the screen ,set background colour and panel dimensions
		   add(PanelN, BorderLayout.NORTH);
		   PanelN.setBackground(Color.lightGray);
		   PanelS.setPreferredSize(new Dimension(300,50));
		   add(PanelS, BorderLayout.SOUTH);
		   PanelS.setBackground(Color.gray);
		   add(PanelC, BorderLayout.CENTER);
		   PanelC.setBackground(Color.lightGray);	
		   add(PanelC, BorderLayout.CENTER);
		   PanelC.setBackground(Color.lightGray);	
		   
	    // make the screen appear - without this, it doesn't!  
		   
		   setVisible(true);		   		   
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
