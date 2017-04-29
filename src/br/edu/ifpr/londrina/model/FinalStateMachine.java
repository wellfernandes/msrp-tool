package br.edu.ifpr.londrina.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class FinalStateMachine{


	//Collections of States
	private LinkedList<StateModel> statesList = new LinkedList<StateModel>();
	private ArrayList<String> arrayEvents;
	
	//Last State added
	private StateModel lastState = new StateModel();
	//StringTokenizer 
	private StringTokenizer events;
	
	//UNIQUE INSTANCE FSM - SINGLETON
	private static FinalStateMachine fsm = null;
	
	int sizeArray = 0;
	
	//PRIVATE CONSTRUCTOR - SINGLETON
	private FinalStateMachine (){
		
	}
	
	//ADD STATES IN THE LIST OF STATES
	public void addStateInList(StateModel state) {
		
		if(statesList.size() == 0){
			System.out.println("initial state: "+state.getState_name());
			statesList.addFirst(state);
		}else{
			System.out.println("intermediate state: "+state.getState_name());
			statesList.addLast(state);
		}
			lastState = state;

		//Print list of added states
		for(int i=0; i < statesList.size(); i++){
			System.out.println("\nEstados salvos: "+ statesList.get(i).getState_name());
		}
		//Print last added state
		System.out.println("\n\nLast state added: "+ lastState.getState_name());
	}
	
	
	//SPLIT TRACE FILE EVENTS
	public void traceSplit(List<String> traceEvent){
		arrayEvents = new ArrayList<String>();
		System.out.println("\n\n--- SPLIT TRACE---");
		
		for (String traceEvents : traceEvent) {
			events = new StringTokenizer(traceEvents, " ");
			int count = 0;
			
			//System.out.println(">> "+ events.countTokens() + " <<");
			
			while(events.hasMoreElements()){
				String tTemp = events.nextToken();
				//System.out.print(tTemp +" + ");
				if(count == 4)
					arrayEvents.add(tTemp);
				count+=1;
			}
		}
	}
	
	//DELETE STATE OF LINKED LIST
	public boolean deleteState(String nameState){
		if(statesList.getFirst().getState_name().equals(nameState)){
			statesList.removeFirst();
		}else if(statesList.getLast().getState_name().equals(nameState)){
			statesList.removeLast();
		}else{
			for(int i=0; i < statesList.size(); i++){
				if(statesList.get(i).getState_name().equals(nameState)){
					statesList.remove(i);
				}
			}
		}
		if(statesList.isEmpty()){
			return true;
		}
			//Print saved states yet
			for(int j=0; j < statesList.size(); j++){
				System.out.println("Estados ainda salvos: "+ statesList.get(j).getState_name());
			}
		return false;
	}

	//ADD TRANSITIONS EVENTS IN ARRAY
	public void addTransitionEvent(String source, String target, String event){
		
		for (StateModel stateModel : statesList) {
			if(stateModel.getState_name().equals(source)){
				
				StateTransition transition = new StateTransition();
				
				transition.setTransitionEvent(event);
				transition.setTarget(target);
				
				stateModel.getTransactionsList().add(transition);
				
				for (StateTransition item : stateModel.getTransactionsList()) {
						System.out.println("Transições do estado: ["+stateModel.getState_name()+"] Event: "+
								item.getTransitionEvent()+" Target: "+item.getTarget());
				}
			}
		}
	}

	//METHOD OF DESIGN PATTERN SINGLETON
	//Return the unique instance of FinalStateMachine
	public static FinalStateMachine getInstance(){
		
		if (fsm != null){
			return fsm;
		}else{
			fsm = new FinalStateMachine();
			return fsm;
		}
	}
	
	/**--------------------------------------------------------------
	 *----------------------GETTERS AND SETTERS----------------------
	 *--------------------------------------------------------------- 
	 **/
	

	//Get StatesList
	public LinkedList<StateModel> getStatesList() {
		return statesList;
	}

	//Set StatesList
	public void setStatesList(LinkedList<StateModel> statesList) {
		this.statesList = statesList;
	}

	//Get LastState
	public StateModel getLastState() {
		return lastState;
	}

	//Set LastState
	public void setLastState(StateModel lastState) {
		this.lastState = lastState;
	}

	//Get ArrayEvents
	public ArrayList<String> getArrayEvents() {
		return arrayEvents;
	}

	//Set ArrayEvents
	public void setArrayEvents(ArrayList<String> arrayEvents) {
		this.arrayEvents = arrayEvents;
	}

	//Get Events
	public StringTokenizer getEvents() {
		return events;
	}

	//Set Events
	public void setEvents(StringTokenizer events) {
		this.events = events;
	}
}
