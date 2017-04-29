package br.edu.ifpr.londrina.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.swing.JFileChooser;

import br.edu.ifpr.londrina.controller.Controller;

public class OutputReport {

	private JFileChooser localSave = new JFileChooser();
	
	//Iterator
	private LinkedList<String> reports = Controller.getTestReport();
	
	//Test report on txt file
	public void reportTxt() throws IOException{
		
		localSave.showSaveDialog(null);
		
		FileWriter txt = new FileWriter(localSave.getSelectedFile().getCanonicalPath());
		PrintWriter report = new PrintWriter(txt);
		
		for (String txtReports : reports ) {
			report.printf("-"+ txtReports);
		}
		txt.close();
		System.out.println("\n\nGenerated File Report.");
	}
}
