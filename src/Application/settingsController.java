package Application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class settingsController implements Initializable {

    private Stage st;
    private Scene sc;

    @FXML
    private TextArea customWords;

    @FXML
    private Button backButton;

    @FXML
    private CheckBox cwCheckBox;

    @FXML
    private Label roomCode;

    @FXML
    private ChoiceBox<String> roundChoice;

    @FXML
    private Button startGame;

    @FXML
    private ChoiceBox<String> timeChoice;

    public String[] systemWords = {"apple", "banana", "cat", "dog", "elephant", "fish", "giraffe"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeChoiceBox(roundChoice, "10", "7", "5", "3", "1");
        initializeChoiceBox(timeChoice, "60", "45", "30");
        generateRandomRoomCode();
    }

    private void initializeChoiceBox(ChoiceBox<String> choiceBox, String... items) {
        choiceBox.getItems().addAll(items);
        choiceBox.setValue(items[0]);
    }

    private void generateRandomRoomCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        roomCode.setText(sb.toString());
    }

    @FXML
    public void startGame(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("room.fxml"));
        Parent root = loader.load();
        roomController roomController = loader.getController();

        // Pass selected draw time, room code, and system words as parameters
        roomController.setDrawTime(Integer.parseInt(timeChoice.getValue()));
        roomController.setRoomCode(roomCode.getText());
        roomController.setSystemWords(systemWords);

        // Update the timer label based on the draw time
        roomController.updateTimerLabel();

        // Update room code label
        roomController.updateRoomCodeLabel(roomCode.getText());

        st = (Stage)((Node)event.getSource()).getScene().getWindow();
        sc = new Scene(root);
        st.setScene(sc);
        st.show();
    }

    @FXML
    void backToLobby(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("lobby.fxml"));
        st = (Stage)((Node)event.getSource()).getScene().getWindow();
        sc = new Scene(root);
        st.setScene(sc);
        st.show();
    }
}
