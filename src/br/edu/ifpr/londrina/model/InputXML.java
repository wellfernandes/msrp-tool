package br.edu.ifpr.londrina.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class InputXML {
	
	private FileReader reader;
	private BufferedReader buffer;
	
	//Name of archive, url in the storage
	private String archiveName = new String("/Users/");
	//Create the archive
	private File archive = new File(archiveName);
	private JFileChooser fileChooserXML;
	//Count the number of lines read
	private int returnChoice = 0;

	//LOAD XML FILE
	public boolean loadXML(){
		fileChooserXML = new JFileChooser();
		
		returnChoice = fileChooserXML.showOpenDialog(null);
		
		//If there is a file loaded
		if(returnChoice == JFileChooser.APPROVE_OPTION){
			System.out.println("\nLoading XML File...\n");
			if(readXML() == true){
				return true;
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Operation canceled.");
			return false;
		}
		return false;
	}

	//READ XML FILE
	public boolean readXML(){
		if(archive.exists()){
			try{
				//FileReader to read txt file
				reader = new FileReader(fileChooserXML.getSelectedFile());
				//Buffer to save collections of file
				buffer = new BufferedReader(reader);
				
				//Check XML File
				if(fileChooserXML.getSelectedFile().isFile()){
					System.out.println("\nXML OK\n");
				}
				if(!fileChooserXML.getSelectedFile().isFile()){
					JOptionPane.showMessageDialog(null, "File is empty!");
				}
			
			//Close the file read
			buffer.close();
				
			}catch(Exception errorFile){
				//Error File
			}finally{
				JOptionPane.showMessageDialog(null, "Trace file has been uploaded sucessfully! ");
			}
		}
		return true;
	}
	
	
	/**--------------------------------------------------------------
	 *----------------------GETTERS AND SETTERS----------------------
	 *--------------------------------------------------------------- 
	 **/
	
	public JFileChooser getFileChooserXML() {
		return fileChooserXML;
	}

	public void setFileChooserXML(JFileChooser fileChooserXML) {
		this.fileChooserXML = fileChooserXML;
	}

	public FileReader getReader() {
		return reader;
	}

	public void setReader(FileReader reader) {
		this.reader = reader;
	}

	public BufferedReader getBuffer() {
		return buffer;
	}

	public void setBuffer(BufferedReader buffer) {
		this.buffer = buffer;
	}

	public String getArchiveName() {
		return archiveName;
	}

	public void setArchiveName(String archiveName) {
		this.archiveName = archiveName;
	}

	public int getReturnChoice() {
		return returnChoice;
	}

	public void setReturnChoice(int returnChoice) {
		this.returnChoice = returnChoice;
	}

	public File getArchive() {
		return archive;
	}

	public void setArchive(File archive) {
		this.archive = archive;
	}
	
}
