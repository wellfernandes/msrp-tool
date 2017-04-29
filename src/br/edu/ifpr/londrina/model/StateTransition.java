package br.edu.ifpr.londrina.model;


/** Class transition */

public class StateTransition {

	private String transitionEvent;
	private String source;
	private String target;
	
	/**--------------------------------------------------------------
	 *----------------------GETTERS AND SETTERS----------------------
	 *--------------------------------------------------------------- 
	 **/
	
	//Get Name transition
	public String getTransitionEvent() {
		return transitionEvent;
	}
	
	//Set Name transition
	public void setTransitionEvent(String transitionEvent) {
		this.transitionEvent = transitionEvent;
	}
	
	//Get State Target
	public String getTarget() {
		return target;
	}
	
	//Set State Destiny
	public void setTarget(String target) {
		this.target = target;
	}
	
	//Get State Source
	public String getSource() {
		return source;
	}
	
	//Set State Source
	public void setSource(String source) {
		this.source = source;
	}
}
