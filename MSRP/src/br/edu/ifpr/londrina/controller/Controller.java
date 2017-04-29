/*
 * Open source project
 * 
 * By: Welliton Fernandes Leal
 * Github: https://github.com/wellfernandes
 * 
 * Network Protocol Verification System
 * by means of model-based testing
 * 
 */

package br.edu.ifpr.londrina.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.ifpr.londrina.model.FinalStateMachine;
import br.edu.ifpr.londrina.model.InputTrace;
import br.edu.ifpr.londrina.model.InputXML;
import br.edu.ifpr.londrina.model.StateModel;
import br.edu.ifpr.londrina.model.StateTransition;
import br.edu.ifpr.londrina.model.XMLRead;
import br.edu.ifpr.londrina.view.AboutDeveloper;
import br.edu.ifpr.londrina.view.AboutSoftware;
import br.edu.ifpr.londrina.view.PrincipalView;

public class Controller{
	
	private PrincipalView principalView;
	private FinalStateMachine fsm;
	private AboutSoftware aboutSoft;
	private AboutDeveloper aboutDev;
	private String varChoice = null;
	private String currentEvent;
	private String targetState;
	
	private String lastState;
	private String lastEvent;
	private String waitingEvent;

	//List about test report - txt File
	private static LinkedList<String> testReport = new LinkedList<String>();
	
	String tempEvent;
	
	private InputXML inputXML; 
	
	XMLRead xmlRead;
	
	//Controller Initialize view
	public void initializeView(){
		principalView = new PrincipalView();
	}
	
	//Controller view - Close/Cancel/AboutSoftware/AboutDeveloper
	public void viewOperation(String choice){
		
		if(choice.equals("close")){
			//Close the Software
			System.out.println("Closing - Do you would like to save this project?");
			int dell = JOptionPane.showConfirmDialog(null, "Do you would like to save this project?");
			if(dell == JOptionPane.CANCEL_OPTION){
				System.out.println("Canceled - Would like not close the project");
			}else if(dell == JOptionPane.OK_OPTION){
				System.out.println("Yes - Would like to save the project");
				JOptionPane.showMessageDialog(null, "Not implemented yet");
			}
			else if(dell == JOptionPane.NO_OPTION){
				System.out.println("Save project: No.");
			}
			System.exit(1);
		}
		else if(choice.equals("aboutSoftware")){
			//About Soft. View
			aboutSoft = new AboutSoftware();
		}
		else if(choice.equals("aboutDeveloper")){
			//About Develop. View
			aboutDev = new AboutDeveloper();
		}
		
		else{
			//If the frame to cause a error
			int dell = JOptionPane.showConfirmDialog(null, "Sorry about that, there is a error on your view.");
			if(dell == JOptionPane.CANCEL_OPTION){
				System.out.println("Canceled");
			}else if(dell == JOptionPane.OK_OPTION){
				System.out.println("Ok");
			}
			else{
				System.out.println("default");
			}
		}
	}
	
	//Controller Import Trace file
	public boolean importTraceFile(){
		
		System.out.println("Importing File...");
		InputTrace inputTraceFile = new InputTrace();
		
		if(inputTraceFile.loadFile() == true){
			fsm = FinalStateMachine.getInstance();
			fsm.traceSplit(InputTrace.getTraceList());
			return true;
		}else{
			return false;
		}
	}

	//Controller Open XML file
	public boolean openProject(){

		System.out.println("Open XML File...");
		inputXML = new InputXML();
		
		if(inputXML.loadXML() == true){
			XMLRead statesXML = new XMLRead();
			statesXML.parsing(inputXML.getFileChooserXML().getSelectedFile().getPath());
			return true;
		}else{
			return false;
		}
	}
	
	//Controller Save Project XML
	public void saveProject(){
		System.out.println("Saving the project...");
		JOptionPane.showMessageDialog(null, "Not implemented...");
	}
	
	//Controller Save As 
	public void saveAsProject(){
		System.out.println("Saving as...");
		JOptionPane.showMessageDialog(null, "Not implemented...");
	}
	
	//Controller Verify Modeling VS Trace
		public boolean verifyModeling(String currentState){
			
			int lines = 0;
			
			fsm = FinalStateMachine.getInstance();
			ArrayList<String> event = fsm.getArrayEvents();

			int indexEvent=0;
			
			for (String e : event) {

				currentEvent = event.get(indexEvent);
				List<StateModel> stateList = fsm.getStatesList();
				for (StateModel states : stateList) {
					
					List<StateTransition> transitionTemp = states.getTransactionsList();
					
					if(states.getState_name().equalsIgnoreCase((currentState)) && !states.getTransactionsList().isEmpty()){
						
						//Last state 1
						lastState = currentState;
						System.out.println("\n\nCURRENT STATE: "+currentState);
						System.out.println("Transitions: "+states.getTransactionsList().size());
						lastState = currentState;
						
						//Check transitions of state
						int countT =1;
							for (StateTransition transition : transitionTemp) {
								if(transition.getTransitionEvent().equalsIgnoreCase((currentEvent))){
									System.out.println("CURRENT EVENT: "+ currentEvent);
									
									waitingEvent = currentEvent;
									
									//Target - Next State
									targetState = transition.getTarget();
									System.out.println("TARGET STATE: "+targetState+ "\n" );
									
									//Add to Test Report - FOR TXT FILE
									testReport.add("\nCurrent State: "+currentState+ "\n"+"Transitions: "+ states.getTransactionsList().size()+"\n"
											+"Current event: "+currentEvent+"\n"+"Target State: "+ targetState+"\nTrace line: "+lines+"\n\n");
									
									currentState = targetState;
								
									System.out.println("TRACE LINE: "+lines);
									lines++;
									break;
									}
								else if(transition.getTransitionEvent().isEmpty() || transition.getTarget().equals(null)){
									return false;
								}
								if(!transition.getTransitionEvent().equalsIgnoreCase((currentEvent)) && countT == transitionTemp.size()){
									System.out.println("CURRENT EVENT: "+ currentEvent);
									waitingEvent = currentEvent;
									return false;
								}
								countT++;
							}
						//LastState 2	
						lastState = currentState;
						lastEvent = currentEvent;
					}
					currentState = targetState;
					
					//END RUN FINAL STATE MACHINE
					if(lines == fsm.getArrayEvents().size()){
						return true;									
					}
					if(e.length() == -1){
						return true;
					}
				}
				indexEvent++;
			}
			return false;
		}
		
	//Controller clear trace file imported
	public boolean clearFileImported(){
		
		//Cleaning the trace list imported 
		if(InputTrace.getTraceList().size() != 0){
			InputTrace.getTraceList().clear();
			return true;
		}
		else if(InputTrace.getTraceList().size() == 0){
			return false;
		}
		else{
			JOptionPane.showMessageDialog(null, "Sorry! Cant to clear the file.\nInternal error!");
			return false;
		}
	}
	
	/**--------------------------------------------------------------
	 *----------------------GETTERS AND SETTERS----------------------
	 *--------------------------------------------------------------- 
	 **/
	//Get VarChoice
	public String getVarChoice() {
		return varChoice;
	}

	//Set VarChoice
	public void setVarChoice(String varChoice) {
		this.varChoice = varChoice;
	}

	//Get PrincipalView
	public PrincipalView getPrincipalView() {
		return principalView;
	}

	//Set PrincipalView
	public void setPrincipalView(PrincipalView principalView) {
		this.principalView = principalView;
	}

	//get AboutSoftware
	public AboutSoftware getAboutSoft() {
		return aboutSoft;
	}
	
	//Set AboutSoftware
	public void setAboutSoft(AboutSoftware aboutSoft) {
		this.aboutSoft = aboutSoft;
	}

	//Get AboutDeloper
	public AboutDeveloper getAboutDev() {
		return aboutDev;
	}

	//Set AboutDev
	public void setAboutDev(AboutDeveloper aboutDev) {
		this.aboutDev = aboutDev;
	}

	//Get XML Readl
	public XMLRead getXmlRead() {
		return xmlRead;
	}

	//set XML ReadXML
	public void setXmlRead(XMLRead xmlRead) {
		this.xmlRead = xmlRead;
	}

	//Get LastState
	public String getLastState() {
		return lastState;
	}

	//Set LastState
	public void setLastState(String lastState) {
		this.lastState = lastState;
	}

	//Get LastEvent
	public String getLastEvent() {
		return lastEvent;
	}

	//Set LastEvent
	public void setLastEvent(String lastEvent) {
		this.lastEvent = lastEvent;
	}

	//Get WaintingEvent
	public String getWaitingEvent() {
		return waitingEvent;
	}

	//Set WaintingEvent
	public void setWaitingEvent(String waitingEvent) {
		this.waitingEvent = waitingEvent;
	}

	//Get CurrentEvent
	public String getCurrentEvent() {
		return currentEvent;
	}

	//Set CurrentEvent
	public void setCurrentEvent(String currentEvent) {
		this.currentEvent = currentEvent;
	}

	//Get TargetState
	public String getTargetState() {
		return targetState;
	}

	//Set TargetState
	public void setTargetState(String targetState) {
		this.targetState = targetState;
	}

	//Get LinkedList TesteRport
	public static LinkedList<String> getTestReport() {
		return testReport;
	}

	//Set LinkedList TesteRport
	public static void setTestReport(LinkedList<String> testReport) {
		Controller.testReport = testReport;
	}

	//Get TempEvent
	public String getTempEvent() {
		return tempEvent;
	}

	//Set TempEvent
	public void setTempEvent(String tempEvent) {
		this.tempEvent = tempEvent;
	}

	//Get InputXML
	public InputXML getInputXML() {
		return inputXML;
	}

	//Set InputXML
	public void setInputXML(InputXML inputXML) {
		this.inputXML = inputXML;
	}
	
}
