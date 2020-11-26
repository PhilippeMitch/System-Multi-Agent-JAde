package Electeur;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.gui.GuiEvent;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import sma.ElecteurAgent;

public class ElecteurContainer implements Initializable{

    @FXML
    private ListView<String> listDemocrate;
    @FXML
    private JFXButton btnConfirmationVote;
    @FXML
    private JFXTextField txtIdEleclecteur;

	private ObservableList<String> observableListDemocrate;

    @FXML
    private JFXButton btnActiverPoste;
	
	@FXML
	private JFXTextField txtNumPoste;

	@FXML
	private JFXButton btnPublicationPoste;
	
	@FXML
    private MaterialDesignIconView iconSucces;
	
	private ElecteurAgent electeurAgent;
	
	boolean publish;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		observableListDemocrate=FXCollections.observableArrayList();
		listDemocrate.setItems(observableListDemocrate);
		btnConfirmationVote.setDisable(true);
		btnActiverPoste.setDisable(true);
		txtIdEleclecteur.setDisable(true);
		txtNumPoste.setText(null);
		iconSucces.setVisible(false);
	}
	    
	@FXML
	void listDemocrateCliked(MouseEvent event) {
		if(listDemocrate.getSelectionModel().getSelectedItem()!=null) {
		btnConfirmationVote.setDisable(false);
		txtIdEleclecteur.setDisable(false);
		iconSucces.setVisible(false);
		}
	}
		
		public void startElecteurContainer() {
			try {
				Runtime runtime=Runtime.instance();
				Profile profile=new ProfileImpl(false);
				profile.setParameter(Profile.MAIN_HOST, "localhost");
				AgentContainer agentContainer=runtime.createAgentContainer(profile);
				AgentController agentController=agentContainer.createNewAgent("Poste-"+txtNumPoste.getText(), "sma.ElecteurAgent", new Object[] {this});
				agentController.start();
			} catch (ControllerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void viewMessageDemocrate(String guiEvent) {
			//String message=guiEvent.getParameter(0).toString();
			System.out.println("Ce devrait etre ajouter dans la liste view: "+guiEvent);
			observableListDemocrate.add(guiEvent);
		}
		
		public void notificationVote(String guiEvent) {
			iconSucces.setVisible(true);
			btnConfirmationVote.setDisable(true);
			txtIdEleclecteur.setDisable(true);
		}
		
		public void notificationTempsVote(String guiEvent) {
			///String message=guiEvent.getParameter(0).toString();
			succesMessage(guiEvent.toString());
		}
		
		public void succesMessage(String sms) {
			Alert alert = new Alert(Alert.AlertType.ERROR, sms);
	        alert.setTitle("Erreur");
	        alert.setHeaderText("");
	        alert.showAndWait();
		}
		
		public ElecteurAgent getElecteurAgent() {
			return electeurAgent;
		}

		public void setElecteurAgent(ElecteurAgent electeurAgent) {
			this.electeurAgent = electeurAgent;
		}
	    
		@FXML
	    void btnConfirmerVoteAction(ActionEvent event) {
			GuiEvent guiEvent=new GuiEvent(this, 1);
			guiEvent.addParameter(listDemocrate.getSelectionModel().getSelectedItem());
			electeurAgent.onGuiEvent(guiEvent);
	    }
		
		@FXML
	    void ActiverPosteEvent(ActionEvent event) {
			GuiEvent guiEvent=new GuiEvent(this, 1);
			guiEvent.addParameter("Demande de liste");
			electeurAgent.onGuiEventAsk(guiEvent);
			btnActiverPoste.setDisable(true);
	    }
		
		@FXML
	    void btnPublierEbent(ActionEvent event) {
			
			if(txtNumPoste.getText()!=null) {
				startElecteurContainer();
				btnActiverPoste.setDisable(false);
				btnPublicationPoste.setDisable(true);
				txtNumPoste.setDisable(true);
			}else {
				Alert alert = new Alert(Alert.AlertType.ERROR, "Vous devez saisir le numero du poste");
		        alert.setTitle("Erreur");
		        alert.setHeaderText("");
		        alert.showAndWait();
			}
	    }
}
