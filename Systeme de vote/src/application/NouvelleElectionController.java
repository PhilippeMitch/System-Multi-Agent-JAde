package application;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;

import controller.CandidatElectionController;
import controller.ElectionController;
import domaine.Election;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;
import jade.wrapper.ControllerException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class NouvelleElectionController implements Initializable{

	@FXML
	private TableView<Election> listElection;
	@FXML
	private TableColumn<Election,Integer> id;
	@FXML
	private TableColumn<Election,String> typeElection;
	@FXML
	private TableColumn<Election,String> dateElection;
	@FXML
	private TableColumn<Election,String> statusElection;
	
	@FXML private JFXButton btnSaveElection;
	
	@FXML JFXDatePicker txtDateElection;
	@FXML JFXComboBox<String> cmbTypeElection;
	private double xOffset = 0;
	private double yOffset = 0;
	
	private ObservableList<Election> data;
	private ObservableList<String> dataParti;
	private ElectionController t=new ElectionController();
	private CandidatElectionController cpC=new CandidatElectionController();

	String parti1;
	String parti2;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		cmbTypeElection.getItems().addAll("Présidentielle","Sénatoriales");
		loadData();
		
		listElection.setRowFactory( tv -> {
		    TableRow<Election> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		        	Election rowData = row.getItem();
		            System.out.println(rowData.getDateElection());
		    		getPartiCandidat(rowData.getDateElection().toString());
		            loadElectionForm();
		        }
		    });
		    return row ;
		});
	}
	
	private void loadElectionForm() {
		try {
			Stage secondStage=new Stage();
			FXMLLoader loader=new FXMLLoader();
			Parent root=loader.load(getClass().getResource("DemarerElection.fxml").openStream());
			DemarerElectionController dashboard=(DemarerElectionController)loader.getController();
			dashboard.getCandidats(parti1, parti2,"Abstention");
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
	
	 public void startMainContainer() {
	    	try {
				Runtime runtime=Runtime.instance();
				Properties properties=new ExtendedProperties();
				properties.setProperty(Profile.GUI, "true");
				Profile profile=new ProfileImpl(properties);
				AgentContainer mainContainer=runtime.createMainContainer(profile);
				mainContainer.start();
			} catch (ControllerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	 
	 public void getPartiCandidat(String date) {
		 dataParti=cpC.getPartiCandidat(date);
		 if(dataParti!=null) {
		 parti1=dataParti.get(0);
		 parti2=dataParti.get(1);
		System.out.println(""+dataParti.size());
		 System.out.println(""+parti1);
		 System.out.println(""+parti2);}
	 }
	
	public void loadData(){
		
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		typeElection.setCellValueFactory(new PropertyValueFactory<>("typeElection"));
		dateElection.setCellValueFactory(new PropertyValueFactory<>("dateElection"));
		statusElection.setCellValueFactory(new PropertyValueFactory<>("statusElection"));
		
		try {
			data=t.getAllElection();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("Erreur "+e);
		}
		
		listElection.setItems(null);
		listElection.setItems(data);
	
	}
	
	@FXML
	private void btnSaveElection(ActionEvent event){
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
        		EnregistrerElecteur();
			} catch (Exception e) {
				// TODO: handle exception
			}
           
        } else{
        	System.out.print("Button Cancel presser");
        }
	}
	
	public void EnregistrerElecteur(){
		
	int result=0;
	Election el=null;
	ElectionController cc = new ElectionController();
		
		try {
			
			el=new Election(txtDateElection.getValue().toString(),cmbTypeElection.getValue(),"En cours");
			result= cc.EnregistrerElection(el);
			if(result>0){
				System.out.print("Enregistrement effectuer");
				loadData();
			}
			else{
				System.out.print("Echec !");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	
}
