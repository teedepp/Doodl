package Application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class lobbyController{

    @FXML
    private Button backButton;

    @FXML
    public TextField nameText;
    
    @FXML
    private Button crbt;

    @FXML
    private Button jrbt;

    @FXML
    private AnchorPane scene1ap;

    @FXML
    private Button spbt;
    private Stage st;
    private Scene sc;

    

    @FXML
    void joinRandom(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("room.fxml"));
        st = (Stage)((Node)event.getSource()).getScene().getWindow();
        sc = new Scene(root);
        st.setScene(sc);
        st.show();
    }
    @FXML
    public void roomSetup(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("settings.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the IOException here (e.g., show an error message to the user)
        }
    }

    @FXML
    void backToHome(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("main1.fxml"));
        st = (Stage)((Node)event.getSource()).getScene().getWindow();
        sc = new Scene(root);
        st.setScene(sc);
        st.show();
    }
}    
