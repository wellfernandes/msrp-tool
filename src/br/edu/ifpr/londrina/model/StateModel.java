package br.edu.ifpr.londrina.model;

import java.util.ArrayList;

import com.mxgraph.view.mxGraph;

/** Class State */

public class StateModel {

	private ArrayList<StateTransition> transactionsList = new ArrayList<StateTransition>();
	private mxGraph graphState;
	private String state_name;
	
	/**--------------------------------------------------------------
	 *----------------------GETTERS AND SETTERS----------------------
	 *--------------------------------------------------------------- 
	 **/
	
	//Get Graph State
	public mxGraph getGraphState() {
		return graphState;
	}

	//Set Graph State
	public void setGraphState(mxGraph graphState) {
		this.graphState = graphState;
	}

	//Get State Name
	public String getState_name() {
		return state_name;
	}
	
	//Set State Name
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	//Get Transactions of State
	public ArrayList<StateTransition> getTransactionsList() {
		return transactionsList;
	}

	//Set Transactions of State
	public void setTransactionsList(ArrayList<StateTransition> transactionsList) {
		this.transactionsList = transactionsList;
	}
}
