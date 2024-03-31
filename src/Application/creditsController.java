package Application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class creditsController {

    @FXML
    private Stage st;
    private Scene sc;

    @FXML
    private Button backButton;

    @FXML
    private ImageView credit1;

    @FXML
    private ImageView credit2;

    @FXML
    void backToHome(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("main1.fxml"));
        st = (Stage)((Node)event.getSource()).getScene().getWindow();
        sc = new Scene(root);
        st.setScene(sc);
        st.show();
    }

}
