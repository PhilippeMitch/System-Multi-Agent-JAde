package application;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import controller.CandidatController;
import controller.CandidatElectionController;
import controller.ElectionController;
import domaine.Candidat;
import domaine.Candidat_Election;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.TextFormatter.Change;

public class AddCandidat implements Initializable{

	@FXML private JFXTextField txtNumSocial;
	@FXML private JFXComboBox<String> cmbPosteCnadidature;
	@FXML private JFXComboBox<String> cmbPartiPolitoque;
	@FXML JFXComboBox<String> cmbDateElection;
	
	private CandidatController t=new CandidatController();

	private ObservableList<String> listDate;
	
	private ElectionController ec=new ElectionController();

	CandidatElectionController cc = new CandidatElectionController();
	Candidat c;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		cmbPosteCnadidature.getItems().addAll("President","Vice-President","Senateur");
		cmbPartiPolitoque.getItems().addAll("Democrate","Republicain");
		
		UnaryOperator<Change> integerFilter = change -> {
		    String newText = change.getControlNewText();
		   
		    if (newText.matches("([1-9][0-9]*)?")) {
		        return change;
		    }
		    
		    return null;
		};
		
		//txtNumSocial.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(),0 ,integerFilter));
		
		loadDateElection();
		txtNumSocial.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	txtNumSocial.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
	}
	
	
	public void loadDateElection() {
		try {
			listDate=ec.getDateElection();
		} catch (Exception e) {
			// TODO: handle exception
		}
		cmbDateElection.setItems(null);
		cmbDateElection.setItems(listDate);
	}
	
	public void Enregistrer() {
		int result=0;
		Candidat_Election c=null;
			
			try {
				
				c=new Candidat_Election(Long.parseLong(txtNumSocial.getText()),cmbDateElection.getValue(),0,cmbPartiPolitoque.getValue(),cmbPosteCnadidature.getValue());
				result= cc.Enregistrer(c);
				if(result>0){
					Alert alert = new Alert(Alert.AlertType.INFORMATION, "Enregistrement effectue");
			        alert.setTitle("Success");
			        alert.setHeaderText("");
			        alert.showAndWait();
			        txtNumSocial.setText("");
				}
				else{
					System.out.print("Echec !");
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
	}
	
	public void VerifierNumeroSocial(long num) {
		if(String.valueOf(num)!="" || String.valueOf(num).length()>1 ||String.valueOf(num)!=null) {
			c=t.findCandidat(num);
			if(c!=null) {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		        alert.setTitle("Confirmation");
		        alert.setHeaderText(null);
		        alert.setContentText("Voulez vous Enregistrer ?");
		        ButtonType buttonEnregistrer = new ButtonType("Enregistrer");
		        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		       
		        alert.getButtonTypes().setAll(buttonEnregistrer, buttonTypeCancel);
		        Optional<ButtonType> result = alert.showAndWait();
		        
		        if (result.get() == buttonEnregistrer){
		        	try {
		        		VerifierCandidatEnregistrer();
					} catch (Exception e) {
						// TODO: handle exception
					}
		           
		        } else{
		        	System.out.print("Button Cancel presser");
		        }
			}else {
				Alert alert = new Alert(Alert.AlertType.ERROR, "Le numero social saisi ne correspond a aucune personne");
		        alert.setTitle("Erreur");
		        alert.setHeaderText("");
		        alert.showAndWait();
			}
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Vous devez saisir un Identifiant valide");
	        alert.setTitle("Erreur");
	        alert.setHeaderText("");
	        alert.showAndWait();
		}
	}
	
	@FXML
	private void btnSaveCandidat(ActionEvent event){
		VerifierNumeroSocial(Long.parseLong(txtNumSocial.getText()));
	}
	
	private void VerifierCandidatEnregistrer() {
		Candidat_Election cd=null;
		CandidatElectionController cce = new CandidatElectionController();
		cd=cce.findCandidatElection(Long.parseLong(txtNumSocial.getText()));
		if(cd!=null) {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Ce candidat est deja enregistrer");
	        alert.setTitle("Erreur");
	        alert.setHeaderText("");
	        alert.showAndWait();
		}else {
			Enregistrer();
		}
			
	}
}
