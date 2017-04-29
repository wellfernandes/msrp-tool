//Principal View - Menus
package br.edu.ifpr.londrina.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

import br.edu.ifpr.londrina.controller.Controller;
import br.edu.ifpr.londrina.model.FinalStateMachine;
import br.edu.ifpr.londrina.model.InputTrace;
import br.edu.ifpr.londrina.model.OutputReport;
import br.edu.ifpr.londrina.model.StateModel;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class PrincipalView extends JFrame{
	/**
	 * PRINCIPAL VIEW
	 */
	private static final long serialVersionUID = 1L;
	
	//Components about the graph
	//Statics
	private static mxGraph graph = new mxGraph();;
	private static HashMap m = new HashMap();
	private mxGraphComponent graphComponent;
	private Object stateGraph;
	private Controller controller;
	
	private FinalStateMachine fsm = FinalStateMachine.getInstance();
	private StateModel state;
	private String state_name;
	
	//Object graph
	private Object v1;
	//Object line for connection with graph
	private Object v2;
		
	private JFrame principalFrame;
	private JPanel principalPanel;
	private JPanel optionsPanel;
	private JMenuBar principalMenuBar;
	
	//Menus
	private JMenu menuFile;
	private JMenu menuOptions;
	private JMenu menuAbout;
	
	//Menus Itens
	//FILE
	private JMenuItem importFile;
	private JMenuItem save;
	private JMenuItem saveAs;
	private JMenuItem close;
	private JMenuItem openProject;
	
	private JMenuItem refresh;
	private JMenuItem aboutSoftware;
	private JMenuItem aboutDeveloper;
	
	private JToolBar toolBar;
	private JSplitPane splitPane;
	private JButton buttonNewState;
	private JButton buttonDeleteState;
	private JButton buttonVerify;
	private JButton buttonTransition;
	private JButton buttonClear;
	private JButton buttonReport;
	private JLabel labelNameDescription = new JLabel();
	private JLabel labelDescription = new JLabel();
	
	//Constructor Method Empty
	public PrincipalView(){
		//Disable exception about the move graph in the view
		com.mxgraph.swing.util.mxGraphTransferable.enableImageSupport = false;
		//Content Panel Draw
		principalFrame = new JFrame("Modeling System Routing Protocol");
		principalFrame.setName("Modeling System Routing Protocol");
		principalPanel = new JPanel();
		principalPanel.setBounds(200, 0, 600, 555);
		principalPanel.setBorder(BorderFactory.createTitledBorder("Draw modeling"));
		//Content Panel Options
		optionsPanel = new JPanel();
		optionsPanel.setSize(200, 555);
		optionsPanel.setBorder(BorderFactory.createTitledBorder("States modeling"));
		
		principalMenuBar = new JMenuBar();
		toolBar = new JToolBar();
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true);
		
		//Menu
		menuFile = new JMenu("File");
		menuOptions = new JMenu("Options");
		menuAbout = new JMenu("About");
		//Menus Item
		importFile = new JMenuItem("Import trace");
		importFile.setEnabled(false);
		openProject = new JMenuItem("Open XML");
		save = new JMenuItem("Save");
		save.setEnabled(false);
		saveAs = new JMenuItem("Save as");
		saveAs.setEnabled(false);
		close = new JMenuItem("Close");
		refresh = new JMenuItem("Refresh");
		aboutSoftware = new JMenuItem("Software");
		aboutDeveloper = new JMenuItem("Developer");
		
		//Components about the graph - View to create modeling
		graphComponent = new mxGraphComponent(graph);
		//Dont chance the values of Dimension
		graphComponent.setPreferredSize(new Dimension(585,518));
		principalPanel.add(getContentPane().add(graphComponent));
		
		//Add MenuItens on Menu
		menuFile.setMnemonic(KeyEvent.VK_F);
		menuFile.add(importFile);
		menuFile.add(openProject);
		menuFile.add(save);
		menuFile.add(saveAs);
		menuFile.add(close);
		
		menuOptions.setMnemonic(KeyEvent.VK_O);
		menuOptions.add(refresh);
		
		menuAbout.setMnemonic(KeyEvent.VK_A);
		menuAbout.add(aboutSoftware);
		menuAbout.add(aboutDeveloper);
		
		//Add Menu on Menubar
		principalMenuBar.add(menuFile);
		principalMenuBar.add(menuOptions);
		principalMenuBar.add(menuAbout);
		
		//Dimension of Buttons in the Menu Options
		Dimension dimension = new Dimension(190,40);
		
		/**----------------------------------------**/
		
		buttonNewState = new JButton("New state");
		buttonNewState.setPreferredSize(dimension);;
		buttonNewState.setFocusable(false);
		optionsPanel.add(buttonNewState);
		
		//JButton Delete State
		buttonDeleteState = new JButton("Delete state");
		buttonDeleteState.setPreferredSize(dimension);
		buttonDeleteState.setFocusable(false);
		buttonDeleteState.setEnabled(false);
		optionsPanel.add(buttonDeleteState);
		
		//Button Verify with States
		buttonVerify = new JButton("Check modeling");
		buttonVerify.setPreferredSize(dimension);
		buttonVerify.setFocusable(false);
		buttonVerify.setEnabled(false);
		optionsPanel.add(buttonVerify);
		
		//Button Insert Event Transition
		buttonTransition = new JButton("Insert transition");
		buttonTransition.setPreferredSize(dimension);
		buttonTransition.setEnabled(false);
		buttonTransition.setFocusable(false);
		optionsPanel.add(buttonTransition);
		
		//Button Clear list
		buttonClear = new JButton("Clear trace");
		buttonClear.setPreferredSize(dimension);
		buttonClear.setEnabled(false);
		buttonClear.setFocusable(false);
		optionsPanel.add(buttonClear);
		
		buttonReport = new JButton("Generate report");
		buttonReport.setPreferredSize(dimension);
		buttonReport.setEnabled(false);
		buttonReport.setFocusable(false);
		optionsPanel.add(buttonReport);
		
		//Label description buttons
		optionsPanel.add(labelNameDescription);
		optionsPanel.add(labelDescription);
		
		/**----------------------------------------**/
		
		/** Configure Frame **/
		principalFrame.setSize(800, 600);
		principalFrame.add(principalPanel);
		principalFrame.add(optionsPanel);
		principalFrame.add(splitPane);
		principalFrame.setJMenuBar(principalMenuBar);
		principalFrame.setResizable(false);
		principalFrame.setLayout(null);
		principalFrame.setLocationRelativeTo(null);
		principalFrame.setVisible(true);
		principalFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		System.out.println("Running Principal Frame.");
		
		/** EVENTS **/ 
		Actions action = new Actions();
		//Events Menu FILE
		importFile.addActionListener(action);
		openProject.addActionListener(action);
		save.addActionListener(action);
		saveAs.addActionListener(action);
		close.addActionListener(action);
		//Events Menu OPTIONS
		refresh.addActionListener(action);
		//Events Menu ABOUT
		aboutSoftware.addActionListener(action);
		aboutDeveloper.addActionListener(action);
		//Button create new state
		buttonNewState.addActionListener(action);
		//Button delete state
		buttonDeleteState.addActionListener(action);
		//Button Verify Modeling
		buttonVerify.addActionListener(action);
		//Button Insert Event Transition
		buttonTransition.addActionListener(action);
		//Button Clear List
		buttonClear.addActionListener(action);
		//Button File Test Report
		buttonReport.addActionListener(action);
		
		/** Mouser Listener **/
		ActionsOver actionListener = new ActionsOver();
		buttonNewState.addMouseListener(actionListener);
		buttonDeleteState.addMouseListener(actionListener);
		buttonVerify.addMouseListener(actionListener);
		buttonTransition.addMouseListener(actionListener);
		buttonClear.addMouseListener(actionListener);
		buttonReport.addMouseListener(actionListener);
	}
	
	/** PRIVATE CLASS for mouse over events **/
	private class ActionsOver implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent action) {
			
		}

		@Override
		public void mousePressed(MouseEvent action) {
			
		}

		@Override
		public void mouseReleased(MouseEvent action) {
			if(action.getSource().equals(null)){
				labelNameDescription.setText("");
				labelDescription.setText("");
			}
		}

		@Override
		public void mouseEntered(MouseEvent action) {
			//Implements the conditions
			if(action.getSource().equals(buttonNewState)){
				labelNameDescription.setText("•Create:");
				labelDescription.setText("Create a new state    ");
			}else if(action.getSource().equals(buttonDeleteState)){
				labelNameDescription.setText("•Delete:");
				labelDescription.setText("Delete selected state");
			}else if(action.getSource().equals(buttonVerify)){
				labelNameDescription.setText("•Verify:");
				labelDescription.setText("Verify implementation");
			}else if(action.getSource().equals(buttonTransition)){
				labelNameDescription.setText("•Transition:");
				labelDescription.setText("Insert a event transition");
			}else if(action.getSource().equals(buttonClear)){
				labelNameDescription.setText("•Clear:");
				labelDescription.setText("Clear imported trace  ");
			}else if(action.getSource().equals(buttonReport)){
				labelNameDescription.setText("•Report:");
				labelDescription.setText("Generate report file  ");
			}else if(action.getSource().equals(null)){
				labelNameDescription.setText("");
				labelDescription.setText("");
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			labelNameDescription.setText(" ");
			labelDescription.setText(" ");
		}
	}
	
	/** PRIVATE CLASS for mouse actions **/
	private class Actions implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent action) {
			if(action.getSource().equals(importFile)){
				controller = new Controller();
				if(controller.importTraceFile() == true){
					if(!fsm.getStatesList().isEmpty()){			
						buttonVerify.setEnabled(true);
						buttonClear.setEnabled(true);
					}
				}else{
					System.out.println("Canceled import trace");
				}
			}else if(action.getSource().equals(openProject)){
				controller = new Controller();
				if(controller.openProject() == true){
					importFile.setEnabled(true);
				}else{
					System.out.println("Canceled open XML");
				}
			}else if(action.getSource().equals(save)){
				controller = new Controller();
				controller.saveProject();
			}else if(action.getSource().equals(saveAs)){
				controller = new Controller();
				controller.saveAsProject();
			}else if(action.getSource().equals(close)){
				System.out.println("Close.");
				controller = new Controller();
				controller.viewOperation("close");
			}else if(action.getSource().equals(refresh)){
				System.out.println("Refresh Frame...");
				principalFrame.repaint();
				System.out.println("Frame updated ok");
			}else if(action.getSource().equals(aboutSoftware)){
				System.out.println("About software");
				controller = new Controller();
				controller.viewOperation("aboutSoftware");
			}else if(action.getSource().equals(aboutDeveloper)){
				System.out.println("About Developer");
				controller = new Controller();
				controller.viewOperation("aboutDeveloper");
			}else if(action.getSource().equals(buttonNewState)){
				state_name = new String(state_name = JOptionPane.showInputDialog("Name state:"));
				if(!state_name.equals("")){
					createGraph(state_name);
				}else{
					JOptionPane.showMessageDialog(null, "You need to insert a name event\nint the state!");
				}
			}else if(action.getSource().equals(buttonDeleteState)){
				//Update graphs
				getGraph().getModel().beginUpdate();
				//Select the graph
				stateGraph = graph.getSelectionCell();
				//Method delete selected graph
				deleteGraph(stateGraph);
			}
			else if(action.getSource().equals(buttonVerify)){
				controller = new Controller();
				if(controller.verifyModeling(fsm.getStatesList().getFirst().getState_name()) == true){
					JOptionPane.showMessageDialog(null, "Implementation OK!\nLast state: "+"["+controller.getLastState()+"]\n"
							+ "Last event: ["+controller.getLastEvent()+"]");
					buttonReport.setEnabled(true);
				}else{
					JOptionPane.showMessageDialog(null, "WARNING!\nLast state: ["+controller.getLastState()+"]"
							+"\n(Trace file waiting for an ["+controller.getWaitingEvent()+"] event).");
					buttonReport.setEnabled(true);
				}
			}
			else if(action.getSource().equals(buttonTransition)){
				String event = null;
				if(fsm.getStatesList().size() < 2){
					JOptionPane.showMessageDialog(null, "Sorry!\nIt must have at least two states!");
				}else{
					buttonTransition.setEnabled(true);
					event = JOptionPane.showInputDialog("Insert a event transition:");
					if(!event.equals("")){
						String a;
						String b;
						a = JOptionPane.showInputDialog("Of state:");
						b = JOptionPane.showInputDialog("to state:");
						Object v3 = getM().get(a);
						Object v4 = getM().get(b);
						
						transition(a, b, v3, v4, event);
						
					}else{
						JOptionPane.showMessageDialog(null, "Sorry!\nYou need to insert a event");
					}
				}
			}else if(action.getSource().equals(buttonClear)){
				//Cleaning the trace list imported 
				controller = new Controller();
				if(controller.clearFileImported() == true){
					buttonVerify.setEnabled(false);
					buttonClear.setEnabled(false);
					buttonReport.setEnabled(false);
					importFile.setEnabled(true);
					JOptionPane.showMessageDialog(null, "Ok!\nCleaned trace file.");
				}else{
					JOptionPane.showMessageDialog(null, "Sorry!\nYou need to import a file yet!");
				}
			}else if(action.getSource().equals(buttonReport)){
				OutputReport report = new OutputReport();
				try {
					report.reportTxt();
					JOptionPane.showMessageDialog(null, "Checked report file created!");
				} catch (IOException e) {

					System.out.println("\n\nFound error: "+ e.getMessage()+"\n\n");
					JOptionPane.showMessageDialog(null, "Checked report file not created!");
				}
				
			}else{
				JOptionPane.showConfirmDialog(null, "Sorry, happend a Internal Erro!");
				System.exit(EXIT_ON_CLOSE);
			}
		}
	}
	
	/**---------------------------------------------------------------------------
	/**----------------------METHODS CREATE AND DELETE GRAPH----------------------
	 * ---------------------------------------------------------------------------
	**/
	
	//Create Graph
	public void createGraph(String name_state){
		if(fsm.getStatesList().size() == 0){

			state = new StateModel();
			state.setState_name(name_state);
			
			PrincipalView.getGraph().getModel().beginUpdate();
			
			try{
				Object parent = PrincipalView.getGraph().getDefaultParent(); 
				v1 = PrincipalView.getGraph().insertVertex(parent, null, name_state, 330, 30, 100, 50, "ROUNDED;strokeColor=black;fillColor=silver;");

				PrincipalView.getM().put(name_state, v1);
		
				PrincipalView.getGraph().setCellsEditable(false);
				PrincipalView.getGraph().setCellsMovable(true);
				PrincipalView.getGraph().setCellsResizable(false);
				PrincipalView.getGraph().setCellsEditable(false);
				System.out.println("\n\nCreating initial state...\n");
				
			}finally{
				PrincipalView.getGraph().getModel().endUpdate();
				
				fsm = FinalStateMachine.getInstance();
				fsm.addStateInList(state);
				
				//Enable button Delete State
				buttonDeleteState.setEnabled(true);
			}
		}else{
			System.out.println("\n\nCreating intermediate state...\n");
			
			state = new StateModel();	
			state.setState_name(name_state);
			
			PrincipalView.getGraph().getModel().beginUpdate();
			
			try{
				Object parent = PrincipalView.getGraph().getDefaultParent(); 
				v1 = PrincipalView.getGraph().insertVertex(parent, null, name_state, 330, 30, 100, 50);

				PrincipalView.getM().put(name_state, v1);
		
				PrincipalView.getGraph().setCellsEditable(true);
				PrincipalView.getGraph().setCellsMovable(true);
				PrincipalView.getGraph().setCellsResizable(false);
				PrincipalView.getGraph().setCellsEditable(false);
				
			}finally{
				PrincipalView.getGraph().getModel().endUpdate();
				fsm = FinalStateMachine.getInstance();
				fsm.addStateInList(state);
			}
		}
		if(fsm.getStatesList().size() >= 2){
			buttonTransition.setEnabled(true);
			importFile.setEnabled(true);
		}
		if(!fsm.getStatesList().isEmpty() && !InputTrace.getTraceList().isEmpty()){
			buttonVerify.setEnabled(true);
		}
	}
	
	//Delete selected Graph
	public void deleteGraph(Object stateSelected){

		//Delete in the LinkedList States
		fsm = FinalStateMachine.getInstance();

		
		if(graph.isSelectionEmpty() == true){
			JOptionPane.showMessageDialog(null, "Select a state to delete.");
		}else{
			//Delete Graph View
			graph.getModel().remove(stateSelected);
			PrincipalView.graph.getModel().endUpdate();
			
			//Print deleted state
			System.out.println("\n\nState deleted: "+graph.getModel().getValue(stateSelected));
			String nameState = PrincipalView.graph.getModel().getValue(stateSelected).toString();
			
			
			//Enable and Disable the Buttons Delete State and Insert transition
			if(fsm.deleteState(nameState) == true){
				buttonDeleteState.setEnabled(false);
				buttonReport.setEnabled(false);
				JOptionPane.showMessageDialog(null, "The modeling is empty!");
			}
			if(!fsm.getStatesList().isEmpty()){
				buttonDeleteState.setEnabled(true);
				buttonVerify.setEnabled(true);
				importFile.setEnabled(false);
			}
			if(fsm.getStatesList().size() < 2){
				buttonTransition.setEnabled(false);
				buttonVerify.setEnabled(false);
			}
			
			//Delete Target
			for(int i=0; i < fsm.getStatesList().size(); i++){
				for (int j = 0; j < fsm.getStatesList().get(j).getTransactionsList().size(); j++) {
					if(fsm.getStatesList().get(j).getTransactionsList().get(j).getTarget().equalsIgnoreCase(nameState)){
						fsm.getStatesList().get(i).getTransactionsList().remove(j);
						System.out.println("Removeu Target");
					}
				}
			}
		}
	}
	
	//Add transitions
	public void transition(String a, String b, Object state1, Object state2, String event){
		
		Object parent = PrincipalView.getGraph().getDefaultParent();
		PrincipalView.getGraph().insertEdge(parent, null, event, state1, state2);
		System.out.println("State Name: "+state_name);
        
		//Method to add transition
	    fsm.addTransitionEvent(a, b, event);
	}
	
	/**--------------------------------------------------------------
	 *----------------------GETTERS AND SETTERS----------------------
	 *--------------------------------------------------------------- 
	 **/

	//Get Graph
	public static mxGraph getGraph() {
		return graph;
	}
	
	//Get GraphComponent
	public mxGraphComponent getGraphComponent() {
		return graphComponent;
	}

	//Set GraphCompenent
	public void setGraphComponent(mxGraphComponent graphComponent) {
		this.graphComponent = graphComponent;
	}

	//Get StateGraph
	public Object getStateGraph() {
		return stateGraph;
	}
	
	//Set StateGraph
	public void setStateGraph(Object stateGraph) {
		this.stateGraph = stateGraph;
	}
	
	//Get HashMap M
	public static HashMap getM() {
		return m;
	}
	//Set M
	public static void setM(HashMap m) {
		PrincipalView.m = m;
	}

	//Get ObjectV1
	public Object getV1() {
		return v1;
	}

	//Set ObjectV1 - Graph created 
	public void setV1(Object v1) {
		this.v1 = v1;
	}

	//Get ObjectV2 - Transition Line
	public Object getV2() {
		return v2;
	}

	//Set ObjectV2
	public void setV2(Object v2) {
		this.v2 = v2;
	}

	//Get PrincipalFrame
	public JFrame getPrincipalFrame() {
		return principalFrame;
	}

	//Set PrincipalFrame
	public void setPrincipalFrame(JFrame principalFrame) {
		this.principalFrame = principalFrame;
	}

	//Get PrincipalPanel
	public JPanel getPrincipalPanel() {
		return principalPanel;
	}

	//Set PrincipalPanel
	public void setPrincipalPanel(JPanel principalPanel) {
		this.principalPanel = principalPanel;
	}

	//Get OptionsPanel
	public JPanel getOptionsPanel() {
		return optionsPanel;
	}

	//Set OptionsPanel
	public void setOptionsPanel(JPanel optionsPanel) {
		this.optionsPanel = optionsPanel;
	}

	//Get Principal MenuBar
	public JMenuBar getPrincipalMenuBar() {
		return principalMenuBar;
	}

	//Set PrincipalMenuBar
	public void setPrincipalMenuBar(JMenuBar principalMenuBar) {
		this.principalMenuBar = principalMenuBar;
	}

	//Get MenuFile
	public JMenu getMenuFile() {
		return menuFile;
	}

	//Set MenuFile
	public void setMenuFile(JMenu menuFile) {
		this.menuFile = menuFile;
	}
	
	//Get MenuOptions
	public JMenu getMenuOptions() {
		return menuOptions;
	}

	//Set MenuOptions
	public void setMenuOptions(JMenu menuOptions) {
		this.menuOptions = menuOptions;
	}
	
	//Get MenuAbout
	public JMenu getMenuAbout() {
		return menuAbout;
	}

	//Set MenuAbout
	public void setMenuAbout(JMenu menuAbout) {
		this.menuAbout = menuAbout;
	}

	//Get ImportFile
	public JMenuItem getImportFile() {
		return importFile;
	}

	//Set ImportFile
	public void setImportFile(JMenuItem importFile) {
		this.importFile = importFile;
	}

	//Get Save
	public JMenuItem getSave() {
		return save;
	}

	//Set Save
	public void setSave(JMenuItem save) {
		this.save = save;
	}

	//Get SaveAs
	public JMenuItem getSaveAs() {
		return saveAs;
	}

	//Set SaveAs
	public void setSaveAs(JMenuItem saveAs) {
		this.saveAs = saveAs;
	}

	//Get Close
	public JMenuItem getClose() {
		return close;
	}

	//Set Close
	public void setClose(JMenuItem close) {
		this.close = close;
	}

	//Get OpenProject
	public JMenuItem getImportProject() {
		return openProject;
	}

	//Set OpenProject
	public void setImportProject(JMenuItem openProject) {
		this.openProject = openProject;
	}

	//Get Refresh
	public JMenuItem getRefresh() {
		return refresh;
	}

	//Set Refresh
	public void setRefresh(JMenuItem refresh) {
		this.refresh = refresh;
	}

	//Get AboutSoftware
	public JMenuItem getAboutSoftware() {
		return aboutSoftware;
	}

	//Set AboutSoftware
	public void setAboutSoftware(JMenuItem aboutSoftware) {
		this.aboutSoftware = aboutSoftware;
	}

	//Get AboutDeveloper
	public JMenuItem getAboutDeveloper() {
		return aboutDeveloper;
	}

	//Set AboutDeveloper
	public void setAboutDeveloper(JMenuItem aboutDeveloper) {
		this.aboutDeveloper = aboutDeveloper;
	}

	//Get ToolBar
	public JToolBar getToolBar() {
		return toolBar;
	}

	//Set ToolBar
	public void setToolBar(JToolBar toolBar) {
		this.toolBar = toolBar;
	}

	//Get SplitPane
	public JSplitPane getSplitPane() {
		return splitPane;
	}

	//Set SplitPane
	public void setSplitPane(JSplitPane splitPane) {
		this.splitPane = splitPane;
	}

	//Get ButtonNewState
	public JButton getButtonNewState() {
		return buttonNewState;
	}

	//Set ButtonNewState
	public void setButtonNewState(JButton buttonNewState) {
		this.buttonNewState = buttonNewState;
	}
	
	//Get ButtonDeleteState
	public JButton getButtonDeleteState() {
		return buttonDeleteState;
	}

	//Set ButtonDeleteState
	public void setButtonDeleteState(JButton buttonDeleteState) {
		this.buttonDeleteState = buttonDeleteState;
	}

	//Get ButtonVerify
	public JButton getButtonVerify() {
		return buttonVerify;
	}

	//Set ButtonVerify
	public void setButtonVerify(JButton buttonVerify) {
		this.buttonVerify = buttonVerify;
	}

	//Set Graph
	public static void setGraph(mxGraph graph) {
		PrincipalView.graph = graph;
	}

	//Get ButtonClear
	public JButton getButtonClear() {
		return buttonClear;
	}

	//Set ButtonClear
	public void setButtonClear(JButton buttonClear) {
		this.buttonClear = buttonClear;
	}

	//Get LabelDescription
	public JLabel getLabelDescription() {
		return labelDescription;
	}

	//Set LabelDescription
	public void setLabelDescription(JLabel labelDescription) {
		this.labelDescription = labelDescription;
	}
	
	//Get LabelNameDescription
	public JLabel getLabelNameDescription() {
		return labelNameDescription;
	}

	//Set LabelNameDescription
	public void setLabelNameDescription(JLabel labelNameDescription) {
		this.labelNameDescription = labelNameDescription;
	}

	//Get State Name
	public String getState_name() {
		return state_name;
	}

	//Set State Name
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	//Get Button Transition
	public JButton getButtonTransition() {
		return buttonTransition;
	}

	//Set Button Transition
	public void setButtonTransition(JButton buttonTransition) {
		this.buttonTransition = buttonTransition;
	}
}
	
	
