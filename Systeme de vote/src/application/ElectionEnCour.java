package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ElectionEnCour implements Initializable{
	
	private ObservableList<PieChart.Data> details= FXCollections.observableArrayList();
	@FXML
	private PieChart pieChart;
	@FXML
    private JFXButton btnCloseButton;
	@FXML
    private Label lblRepublicain;

    @FXML
    private Label lblDemocrate;
    @FXML
    private Text lblResultatElection;

    @FXML
    private Label lblAbstention;
    int qtVoixDemocrate=0;
    int qtVoixRepublicain;
    int qtVoixAbsention;
	
	@FXML
	void btnCloaseButonAction(ActionEvent event) {
		Stage stage = (Stage) btnCloseButton.getScene().getWindow(); 
        stage.hide();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		details.addAll(new PieChart.Data("Democrate", 35),new PieChart.Data("Republicain", 40),new PieChart.Data("Abstention", 25));
		pieChart.setLegendSide(Side.BOTTOM);
		pieChart.setLabelsVisible(true);
		pieChart.setData(details);
	}

	public void viewMessage(String idVotant, String choixCandidat) {
		//String message=guiEvent.getParameter(0).toString();
		System.out.println("L'electeur "+idVotant+" a choisi "+choixCandidat);
		switch (choixCandidat) {
		case "Democrate":
			qtVoixDemocrate=qtVoixDemocrate+1;
			lblDemocrate.setText(lblDemocrate.getText()+" "+qtVoixDemocrate);
			break;
		case "Republicain":
			qtVoixRepublicain=qtVoixRepublicain+1;
			lblRepublicain.setText(lblRepublicain.getText()+" "+qtVoixRepublicain);
			break;
		case "Abstention":
			qtVoixAbsention=qtVoixAbsention+1;
			lblAbstention.setText(lblAbstention.getText()+" "+qtVoixAbsention);
		break;
		default:
			break;
		}
		
		//observableListDemocrate.add(guiEvent);
	}
	
	public void genererChat() {
		int maxVal = Integer.MAX_VALUE;
		int nbreElecteur=qtVoixDemocrate+qtVoixRepublicain+qtVoixAbsention;
		int pourcentageAbstention=(qtVoixAbsention*100)/nbreElecteur;
		int pourcentageDemocrate=(qtVoixDemocrate*100)/nbreElecteur;
		int pourcentageRepublicain=(qtVoixRepublicain*100)/nbreElecteur; 
		details.addAll(new PieChart.Data("Democrate", pourcentageDemocrate),new PieChart.Data("Republicain", pourcentageRepublicain),new PieChart.Data("Abstention", pourcentageAbstention));
		pieChart.setLegendSide(Side.BOTTOM);
		pieChart.setLabelsVisible(true);
		pieChart.setData(details);
		int tableauEntier[] = {pourcentageAbstention,pourcentageDemocrate,pourcentageRepublicain};
		for(int i = 0; i < tableauEntier.length; i++){
	         if(tableauEntier[i] < maxVal)
	           maxVal = tableauEntier[i];
	       }
		String candidatGagnant=String.valueOf(maxVal);
		lblResultatElection.setText("L'election est terminee et le gagnat est: "+candidatGagnant);
		
	}
	
}
