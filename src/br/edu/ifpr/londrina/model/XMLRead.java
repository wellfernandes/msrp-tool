package br.edu.ifpr.londrina.model;

import java.io.IOException;
import java.util.LinkedList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class XMLRead extends DefaultHandler{
	
	private LinkedList<StateTransition> transitions = new LinkedList<StateTransition>();
	
	//Final State Machine
	private FinalStateMachine fsm;				//NAO APARECE A LISTA DE ESTADOS EM fsm (Deve ser porque esta como STATIC)
	private StateModel stateTemp;
	private StateTransition transition;
	
	private final String FSM = "fsm";
	private final String STATE = "state";
	private final String TRANSITION = "transition";
	private final String SOURCE = "source";
	private final String TARGET = "target";
	private final String EVENT = "event";
	
	private int startTransition = 0;
	private int endTransition = 0;
	private String nameStateTemp;
	private String event;
	private String target;
	private String source;
	private String text;
	private String currentTag;
	
	private String tag;
	public XMLRead(){
		super();
		fsm = FinalStateMachine.getInstance();
	}
	
	/** Method to execute the automatic parsing in the XML document**/
	public void parsing(String parsingFile){
		//Crate SAXParser instance with the factory 
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser;
		
		try{
			saxParser = factory.newSAXParser();
			//initialize the parsing
			saxParser.parse(parsingFile, this);
		//exceptions
		}catch(ParserConfigurationException | SAXException | IOException error){
			
			StringBuffer msg = new StringBuffer();

			msg.append("Erro:\n");
			msg.append(error.getMessage() + "\n");
			msg.append(error.toString());
			System.out.println(msg);
		}
	}
	
	//Method startDocument 
	@Override
	public void startDocument(){
		System.out.println("\n----------------------------------");
		System.out.println("::::Starting parsing document::::");
		System.out.println("----------------------------------\n\n");
	}
	//Method endDocument 
	@Override
	public void endDocument(){
		System.out.println("\n----------------------------------");
		System.out.println("::::End parsing document::::");
		System.out.println("----------------------------------\n\n");
	}
	//Method StarElement
	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts){
		currentTag = qName;
		if(qName.equalsIgnoreCase(STATE)){
			stateTemp = new StateModel();
			nameStateTemp = atts.getValue(0);
			stateTemp.setState_name(nameStateTemp);
		}
		if(qName.equalsIgnoreCase(TRANSITION)){
			transition = new StateTransition();
		}
		if(qName.equalsIgnoreCase(SOURCE)){
			this.transition.setSource(source);
		}
		if(qName.equalsIgnoreCase(TARGET)){
			this.transition.setTarget(target);
		}
		if(qName.equalsIgnoreCase(EVENT)){
			this.transition.setTransitionEvent(event);
		}
	}
	//Method EndElement
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException{
		
		if(qName.equalsIgnoreCase(STATE)){
			stateTemp.setState_name(text);
			fsm.addStateInList(stateTemp);
			
		}else if(qName.equalsIgnoreCase(TRANSITION)){
			if(startTransition == 0){
				startTransition = 1;
			}
			if(startTransition == 1){
				fsm.addTransitionEvent(source, target, event);
				endTransition = 1;
			}
			startTransition = 0;
			endTransition = 0;
		}else if(qName.equalsIgnoreCase(SOURCE)){
			source = text;
		}else if(qName.equalsIgnoreCase(TARGET)){
			target = text;
		}else if(qName.equalsIgnoreCase(EVENT)){
			event = text;
		}
	}
	
	//Method characters - Recover data
	@Override
	public void characters(char ch [], int start, int length) throws SAXException{
		text = new String(ch, start, length);
	}
	
	/**--------------------------------------------------------------
	 *----------------------GETTERS AND SETTERS----------------------
	 *--------------------------------------------------------------- 
	 **/
	//Get FSM
	public String getFSM() {
		return FSM;
	}

	//Get STATE
	public String getSTATE() {
		return STATE;
	}

	//Get TRANSITION
	public String getTRANSITION() {
		return TRANSITION;
	}

	//Set SOURCE
	public String getSOURCE() {
		return SOURCE;
	}

	//Get TARGET
	public String getTARGET() {
		return TARGET;
	}

	//Get EVENT
	public String getEVENT() {
		return EVENT;
	}

	//Get CurrentTag
	public String getCurrentTag() {
		return currentTag;
	}

	//Set CurrentTag
	public void setCurrentTag(String currentTag) {
		this.currentTag = currentTag;
	}

	//Get Tag
	public String getTag() {
		return tag;
	}

	//Set Tag
	public void setTag(String tag) {
		this.tag = tag;
	}

	//Get Transitions List
	public LinkedList<StateTransition> getTransitions() {
		return transitions;
	}

	//Set Transitions List
	public void setTransitions(LinkedList<StateTransition> transitions) {
		this.transitions = transitions;
	}

	//Get NameStateTemp
	public String getNameStateTemp() {
		return nameStateTemp;
	}

	//Set NameStateTemp
	public void setNameStateTemp(String nameStateTemp) {
		this.nameStateTemp = nameStateTemp;
	}

	//Get StateTemp
	public StateModel getStateTemp() {
		return stateTemp;
	}

	//Set StateTemp
	public void setStateTemp(StateModel stateTemp) {
		this.stateTemp = stateTemp;
	}
	
	//Get Transition
	public StateTransition getTransition() {
		return transition;
	}

	//Set Transition
	public void setTransition(StateTransition transition) {
		this.transition = transition;
	}

	//Get Source
	public String getSource() {
		return source;
	}

	//Set Source
	public void setSource(String source) {
		this.source = source;
	}

	//Get Text
	public String getText() {
		return text;
	}

	//Set Text
	public void setText(String text) {
		this.text = text;
	}

	//Get Event
	public String getEvent() {
		return event;
	}

	//Set Event
	public void setEvent(String event) {
		this.event = event;
	}

	//Get Target
	public String getTarget() {
		return target;
	}

	//Set Target
	public void setTarget(String target) {
		this.target = target;
	}

	//Get StartTransition
	public int getStartTransition() {
		return startTransition;
	}

	//Set StartTransition
	public void setStartTransition(int startTransition) {
		this.startTransition = startTransition;
	}

	//Get EndTransition
	public int getEndTransition() {
		return endTransition;
	}

	//Set EndTransition
	public void setEndTransition(int endTransition) {
		this.endTransition = endTransition;
	}
}

