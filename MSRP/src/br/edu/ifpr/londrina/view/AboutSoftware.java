package br.edu.ifpr.londrina.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AboutSoftware extends JFrame{
	
	/**
	 * ABOUT SOFTWARE DEVELOPER VIEW
	 */
	private static final long serialVersionUID = 1L;

	//Constructor Method
	public AboutSoftware(){
		
		JOptionPane.showMessageDialog(null, "Version 1.0.0 \n"
				+"OpenSource Project\n"
				+ "2015 @ Paraná, Brazil");
		
	}
}
