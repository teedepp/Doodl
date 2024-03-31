package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
 
public class App extends Application {
    
 
    public static void main(String[] args) {
        launch(args);
    }
 
@Override
    public void start(Stage stage) throws Exception {
    
        Parent root = FXMLLoader.load(getClass().getResource("main1.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("DOODL!");
        Image icon = new Image(getClass().getResourceAsStream("doodlIcon.jpeg"));
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
} 