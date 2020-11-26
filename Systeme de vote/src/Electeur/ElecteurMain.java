package Electeur;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ElecteurMain extends Application{

	
	public static Stage primaryStage;
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	private double xOffset = 0;
	private double yOffset = 0;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			AnchorPane root = FXMLLoader.load(getClass().getResource("ElecteurVote.fxml"));
			
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
	                primaryStage.setX(event.getScreenX() - xOffset);
	                primaryStage.setY(event.getScreenY() - yOffset);
	            }
	        });
			
	        root.setOnDragDone((e)->{
	        	primaryStage.setOpacity(10.0f);
	        });
	        
	        root.setOnMouseReleased((e)->{
	        	primaryStage.setOpacity(1.0f);
	        });
	        
			Scene scene = new Scene(root);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
