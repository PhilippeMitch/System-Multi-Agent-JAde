package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class DashboardController implements Initializable {


	@FXML
	private Pane pane;
	AnchorPane homePane;
	
	public String idUser;
	
	@FXML public Label nomUser;

	@FXML Stage stage;;
	
	@FXML JFXButton btnListMembre;
	
	
	public void initialize(URL url, ResourceBundle rb) {
		try {
			this.createPage(homePane, "Main_flow.fxml");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.print(e);
		}
		
	}
	
	public void initialize(String user) {
		try {
			this.createPage(homePane, "Main_flow.fxml");
			nomUser.setText(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
	
	public void getUser(String user) {
		// TODO Auto-generated method stub
		nomUser.setText(user);
	}
	
	public void setNode(Node node){
		pane.getChildren().clear();
		pane.getChildren().add((Node)node);
		node.setLayoutX(100);
		node.setLayoutY(90);
	}
	
	public void createPage(AnchorPane home,String loc) throws IOException{
		home=FXMLLoader.load(getClass().getResource(loc));
		setNode(home);
	}
	
	@FXML
	void btnDashboardAction(ActionEvent event) throws IOException{
		this.createPage(homePane, "Main_flow.fxml");
		
	}
	
	@FXML
	void btnNewElectionAction(ActionEvent event) throws IOException{
		this.createPage(homePane, "NouvelleElection.fxml");
	}
	@FXML
	void btnListElectionAction(ActionEvent event) throws IOException{
		 this.createPage(homePane, "ListElection.fxml");
	 }
	
	@FXML
	void btnNewCandidatAction(ActionEvent event) throws IOException{
		 this.createPage(homePane, "AddCandidat.fxml");
	 }

	@FXML
	void btnListCandidatAction(ActionEvent event) throws IOException{
		 this.createPage(homePane, "ListCandidat.fxml");
	 }
	
	@FXML
	void btnBulletinAction(ActionEvent event) throws IOException{
		 this.createPage(homePane, "ListCandidat.fxml");
	 }
	 
	@FXML
	void btnDisconnectAction(ActionEvent event){
		 try {
				((Node)event.getSource()).getScene().getWindow().hide();
				
				Stage primaryStage=new Stage();
				FXMLLoader loader=new FXMLLoader();
				primaryStage.initStyle(StageStyle.UNDECORATED);
				Parent root;
				root = loader.load(getClass().getResource("/application/Login.fxml").openStream());
				Scene scene= new Scene(root);
				//System.exit(1);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	
}
