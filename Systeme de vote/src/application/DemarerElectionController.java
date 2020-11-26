package application;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.gui.GuiEvent;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import sma.CandidatAgent;

public class DemarerElectionController implements Initializable{
	
	@FXML private AnchorPane rootPane;

    @FXML private AnchorPane panelCompteur;

    @FXML private Text txtHeures;

    @FXML private Text txtMinutes;

    @FXML private Text txtSecondes;

    @FXML private JFXButton btnArreter;

    @FXML private AnchorPane panelReglage;

    @FXML private JFXButton btnDemarer;

    @FXML private JFXComboBox<Integer> cmbHeures;

    @FXML private JFXComboBox<Integer> cmbMinutes;

    @FXML private JFXComboBox<Integer> cmbSecondes;

    @FXML private JFXButton btnCloseButton;
    
    private double xOffset = 0;
	private double yOffset = 0;
	
	boolean publicationAgent=false;
    
    String parti1;

    String parti2;
    
    String parti3;
    /* pie*/
    @FXML
    private AnchorPane rootPane1;

    @FXML
    private Label lblRepublicain;

    @FXML
    private Label lblDemocrate;

    @FXML
    private Label lblAbstention;

    @FXML
    private PieChart pieChart;
    
    @FXML
    private Text txtNmbreVoixDemocrate;

    @FXML
    private Text txtNmbreVoixRepublicain;

    @FXML
    private Text txtNmbreVoixAbstention;
    
    int qtVoixDemocrate=0;
    int qtVoixRepublicain=0;
    int qtVoixAbsention=0;
    boolean tempsEcoules=false;
    @FXML
    private Text lblResultatElection;
    /*Sma variable*/
    private AgentContainer agentContainer;
    private CandidatAgent candidatAgent;
    ObservableList<String> listVoteDemocrate;
    ObservableList<String> listVoteRepublicain;
    ObservableList<String> listVoteAucun;
	
    
    
	Timer timer = new Timer();
    void scrollUp() {
    	TranslateTransition tr1=new TranslateTransition();
    	tr1.setDuration(Duration.millis(100));
    	tr1.setToX(0);
    	tr1.setToY(-230);
    	tr1.setNode(panelReglage);
    	TranslateTransition tr2=new TranslateTransition();
    	tr2.setDuration(Duration.millis(100));
    	tr2.setFromX(0);
    	tr2.setFromY(230);
    	tr2.setToX(0);
    	tr2.setToY(0);
    	tr2.setNode(panelCompteur);
    	ParallelTransition pt=new ParallelTransition(tr1,tr2);
    	pt.play();
    }
    void scrollDown() {
    	TranslateTransition tr1=new TranslateTransition();
    	tr1.setDuration(Duration.millis(100));
    	tr1.setToX(0);
    	tr1.setToY(260);
    	tr1.setNode(panelCompteur);
    	TranslateTransition tr2=new TranslateTransition();
    	tr2.setDuration(Duration.millis(100));
    	tr2.setFromX(0);
    	tr2.setFromY(-260);
    	tr2.setToX(0);
    	tr2.setToY(0);
    	tr2.setNode(panelReglage);
    	ParallelTransition pt=new ParallelTransition(tr1,tr2);
    	pt.play();
    }
    
    @FXML
    void btnArreterElectionAction(ActionEvent event) {
    	scrollDown();
    }

    @FXML
    void btnDemarerElectionAction(ActionEvent event) {
    	scrollUp();
    	txtHeures.setText(cmbHeures.getValue().toString());
    	txtMinutes.setText(cmbMinutes.getValue().toString());
    	txtSecondes.setText(cmbSecondes.getValue().toString());
    	
    	Demarer();
    	
    }

    public boolean tempsEcoule() {
    	return tempsEcoules;
    }
    
    public void Demarer()
    {

		//ElectionEnCoursStage();
    	int[] tableauEntier= {qtVoixAbsention,qtVoixDemocrate,qtVoixRepublicain};
    	TimerTask task = new TimerTask() {
    		int seconds=Integer.parseInt(cmbSecondes.getValue().toString());
    	    int minutes=Integer.parseInt(cmbMinutes.getValue().toString());
    	   int heures=Integer.parseInt(cmbHeures.getValue().toString());
    	  
			@Override
			public void run() {
				// TODO Auto-generated method stub
				seconds --;
				txtSecondes.setText(String.valueOf(seconds));
				if(seconds==0) {
					minutes=minutes-1;
					txtMinutes.setText(String.valueOf(minutes));
					seconds=60;
					if(minutes==0) {
						if(heures!=0) {
						heures=heures-1;
						txtHeures.setText(String.valueOf(heures));
						minutes=60;
						System.out.println("heures "+heures +"minutes "+minutes +"secondes "+seconds);
						txtMinutes.setText(String.valueOf(minutes));
						}else {
							tempsEcoules=true;
							IntSummaryStatistics result = Arrays.stream(tableauEntier)
						            .summaryStatistics();
							lblResultatElection.setText("L'election est terminee et le voix max est: "+result.getMax());
							
							timer.cancel();
							timer.purge();
							return;
						}
					}
				}
			}
    		
    	};
    	timer.scheduleAtFixedRate(task, 1000, 1000);
    	
    }

    public void getCandidats(String nom1,String nom2,String nom3) {
    	parti1=nom1;
    	parti2=nom2;
    	parti3=nom3;
    }

    @FXML
    void btnCloaseButonAction(ActionEvent event) {
    	Stage stage = (Stage) btnCloseButton.getScene().getWindow(); 
        stage.hide();
    }
    
    public void ElectionEnCoursStage() {
    	try {
			Stage secondStage=new Stage();
			FXMLLoader loader=new FXMLLoader();
			Parent root=loader.load(getClass().getResource("ElectionEnCour.fxml").openStream());
			Scene scene= new Scene(root);
			
			root.setOnMousePressed(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	                xOffset = event.getSceneX();
	                yOffset = event.getSceneY();
	            }
	        });
	        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	            	secondStage.setX(event.getScreenX() - xOffset);
	            	secondStage.setY(event.getScreenY() - yOffset);
	            }
	        });
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			secondStage.initStyle(StageStyle.UNDECORATED);
			secondStage.setScene(scene);
			secondStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ObservableList<Integer> listHeures=FXCollections.observableArrayList();
		ObservableList<Integer> listMinutesAndSecondes=FXCollections.observableArrayList();
		for(int i=0;i<=60;i++) {
			if(0<=i && i<=24) {
				listHeures.add(new Integer(i));
			}
			listMinutesAndSecondes.add(new Integer(i));
		}
		cmbMinutes.setItems(listMinutesAndSecondes);
		cmbMinutes.setValue(0);
		
		cmbSecondes.setItems(listMinutesAndSecondes);
		cmbSecondes.setValue(0);
		
		cmbHeures.setItems(listHeures);
		cmbHeures.setValue(0);
		
		SystemVote();
		
	}
	
	 
	public void SystemVote() {
		try { 
			Runtime runtime=Runtime.instance();
			Profile profile=new ProfileImpl(false);
			profile.setParameter(Profile.MAIN_HOST, "localhost");
			agentContainer=runtime.createAgentContainer(profile);
			AgentController agentController=agentContainer.createNewAgent("CE", "sma.CandidatAgent", new Object[] {this});
			agentController.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public CandidatAgent getCandidatAgent() {
		return candidatAgent;
	}
	public void setCandidatAgent(CandidatAgent candidatAgent) {
		this.candidatAgent = candidatAgent;
	}
	
	public void viewMessage(GuiEvent guiEvent) {
		String message=guiEvent.getParameter(0).toString();
		switch (message) {
		case "Democrate":
			qtVoixDemocrate=qtVoixDemocrate+1;
			txtNmbreVoixDemocrate.setText(" "+qtVoixDemocrate);
			break;
		case "Republicain":
			qtVoixRepublicain=qtVoixRepublicain+1;
			txtNmbreVoixRepublicain.setText(" "+qtVoixRepublicain);
			break;
		case "Abstention":
			qtVoixAbsention=qtVoixAbsention+1;
			txtNmbreVoixAbstention.setText(" "+qtVoixAbsention);
		break;
		default:
			break;
		}
	}
	
	public void sendMessage(String message) {
		GuiEvent guiEvent=new GuiEvent(this, 1);
		guiEvent.addParameter(message);
		candidatAgent.onGuiEvent(guiEvent);
	}
	
	
	public void genererChat() {
		
//		int maxVal = Integer.MAX_VALUE;
//		int nbreElecteur=qtVoixDemocrate+qtVoixRepublicain+qtVoixAbsention;
//		int pourcentageAbstention=(qtVoixAbsention*100)/nbreElecteur;
//		int pourcentageDemocrate=(qtVoixDemocrate*100)/nbreElecteur;
//		int pourcentageRepublicain=(qtVoixRepublicain*100)/nbreElecteur;
//		ObservableList<PieChart.Data> details= FXCollections.observableArrayList(
//				new PieChart.Data("Democrate", 50),
//				new PieChart.Data("Republicain", 40),
//				new PieChart.Data("Abstention", 10));
//		pieChart=new PieChart(details);
//		pieChart.setLegendSide(Side.BOTTOM);
//		pieChart.setLabelsVisible(true);
//		int tableauEntier[] = {qtVoixAbsention,qtVoixDemocrate,qtVoixRepublicain};
//		for(int i = 0; i < tableauEntier.length; i++){
//	         if(tableauEntier[i] < maxVal)
//	           maxVal = tableauEntier[i];
//	       }
//		String candidatGagnant=String.valueOf(maxVal);
		lblResultatElection.setText("L'election est terminee et le gagnat est: ");
		
	}
}
