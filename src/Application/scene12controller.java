package Application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class scene12controller {
    @FXML
    private Button credits;

    @FXML
    private Button mpbt11;

    @FXML
    private AnchorPane scene1ap;

    @FXML
    private Button spbt;
    private Stage st;
    private Scene sc;

    @FXML
    public void sw1to2(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("main2.fxml"));
        st = (Stage)((Node)event.getSource()).getScene().getWindow();
        sc = new Scene(root);
        st.setScene(sc);
        st.show();
        // new sceneswitch(scene1ap, "main2.fxml");
    } 
    
    @FXML
    public void swlobby(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("lobby.fxml"));
        st = (Stage)((Node)event.getSource()).getScene().getWindow();
        sc = new Scene(root);
        st.setScene(sc);
        st.show();

    } 

    @FXML
    public void showCredits(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("credits.fxml"));
        st = (Stage)((Node)event.getSource()).getScene().getWindow();
        sc = new Scene(root);
        st.setScene(sc);
        st.show();
    }
}

