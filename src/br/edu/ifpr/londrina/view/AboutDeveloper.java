package br.edu.ifpr.londrina.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
//View About the Software Developer
//There are all components of view AboutDeveloper

public class AboutDeveloper extends JFrame{

	/**
	 * ABOUT DEVELOPER VIEW
	 */
	private static final long serialVersionUID = 1L;

	public AboutDeveloper(){
		JOptionPane.showMessageDialog(null, "\nDeveloper @ Welliton Fernandes Leal"
											+"\n\nMastermind @ Andréia Carniello\n"	
											+ "Mastermind @ Gilson Doi Junior\n"
											+ "\n                   IFPR\n"
											+ "Federal Institute of Education\n"
											+ "Science and Technology @ Brazil");
	}
}
