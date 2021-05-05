package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {		
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Flight.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("bootstrap3.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Flights");
			primaryStage.show(); 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}