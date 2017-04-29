package br.edu.ifpr.londrina.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class InputTrace {

	//List imported trace file
	public static List<String> traceList = new ArrayList<String>();
	
	//private Scanner leitor;
	private FileReader reader;
	private BufferedReader buffer;
	private FinalStateMachine fsm;
	private int traceSize = 0;
	private boolean equalsState = false;
	private int traceLines = 0;
	
	//Each line of archive
	String traceEvent;
	//Count the number of lines read
	int totalLines = 0;
	//Verify if the user choice any file
	int returnChoice = 0;
	//Instance of selected trace file
	JFileChooser fileChooserTrace = new JFileChooser();
	
	//LOAD TRACE FILE
	public boolean loadFile(){
		
		returnChoice = fileChooserTrace.showOpenDialog(null);
			
		//If there is a file loaded
		if(returnChoice == JFileChooser.APPROVE_OPTION){
			System.out.println("Loading File...");
			if(readTrace() == true){
				return true;
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Operation canceled.");
			return false;
		}
		return false;
	}	
	
	//READ TRACE FILE
	public boolean readTrace(){
			try{
				//File reader for to read txt file
				reader = new FileReader(fileChooserTrace.getSelectedFile());
				//Buffer to save collections of file
				buffer = new BufferedReader(reader);
				
				while(true){
					traceEvent = buffer.readLine();
					if(!traceEvent.isEmpty()){
						traceList.add(traceSize, traceEvent);
					}
					
					traceSize++;
					traceLines++;
					
					if(traceEvent.isEmpty()){
						JOptionPane.showMessageDialog(null, "File is empty!");
						break;
					}
					else{
						//Print the trace file Test reading
						System.out.println("Line "+traceLines+":  "+ traceEvent);
					}
				}
				//Close the file read
				buffer.close();
				
			}catch(Exception errorFile){
				//Error File
			}finally{
				JOptionPane.showMessageDialog(null, "Trace file has been uploaded sucessfully! ");
			}
		return true;
	}
	
	/**--------------------------------------------------------------
	 *----------------------GETTERS AND SETTERS----------------------
	 *--------------------------------------------------------------- 
	 **/

		//Get TotalLines
		public int getTotalLines() {
			return totalLines;
		}
		//Set TotalLines
		public void setTotalLines(int totalLines) {
			this.totalLines = totalLines;
		}

		//Get ReturnChoice
		public int getReturnChoice() {
			return returnChoice;
		}
		//set ReturnChoice
		public void setReturnChoice(int returnChoice) {
			this.returnChoice = returnChoice;
		}

		//Get TraceList
		public static List<String> getTraceList() {
			return traceList;
		}

		//Set TraceList
		public static void setTraceList(List<String> traceList) {
			InputTrace.traceList = traceList;
		}

		//Get TraceSize
		public int getTraceSize() {
			return traceSize;
		}

		//Set TraceSize
		public void setTraceSize(int traceSize) {
			this.traceSize = traceSize;
		}

		//Is EqualsState
		public boolean isEqualsState() {
			return equalsState;
		}

		//Set SetEqualsState
		public void setEqualsState(boolean equalsState) {
			this.equalsState = equalsState;
		}

		//Get TraceLines
		public int getTraceLines() {
			return traceLines;
		}
		
		//Set TraceLines
		public void setTraceLines(int traceLines) {
			this.traceLines = traceLines;
		}

		//Get Trace FileChooser
		public JFileChooser getFileChooserTrace() {
			return fileChooserTrace;
		}

		//Set Trace FileChooser
		public void setFileChooserTrace(JFileChooser fileChooserTrace) {
			this.fileChooserTrace = fileChooserTrace;
		}

		//Get FileReader
		public FileReader getReader() {
			return reader;
		}

		//Set FileReader
		public void setReader(FileReader reader) {
			this.reader = reader;
		}

		//Get Buffer
		public BufferedReader getBuffer() {
			return buffer;
		}
		
		//Set Buffer
		public void setBuffer(BufferedReader buffer) {
			this.buffer = buffer;
		}

		//Get FSM
		public FinalStateMachine getFsm() {
			return fsm;
		}

		//Set FSM
		public void setFsm(FinalStateMachine fsm) {
			this.fsm = fsm;
		}
		
		//Get TraceEvent
		public String getTraceEvent() {
			return traceEvent;
		}
		
		//Set TraceEvent
		public void setTraceEvent(String traceEvent) {
			this.traceEvent = traceEvent;
		}
		
}
