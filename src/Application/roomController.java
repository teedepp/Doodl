package Application;

import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea; 
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class roomController implements Initializable{

    @FXML
    private Stage st;
    private Scene sc;


    @FXML
    private TextArea guessArea; 

    @FXML
    private TextField guessBox;

    @FXML
    private Button backButton;

    @FXML
    private TextField brushSize;

    @FXML
    private Canvas canvas;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private CheckBox eraser;

    @FXML
    private Label fifth;

    @FXML
    private Label firstPlayer;

    @FXML
    private Label fourth;

    @FXML
    private VBox nameList;

    @FXML
    private Label roomCodeLabel;

    @FXML
    private Label second;

    @FXML
    private Label sixth;

    @FXML
    private AnchorPane stage;

    @FXML
    private VBox chatArea;

    @FXML
    private Label third;

    @FXML
    private Label timerLabel;

    @FXML
    private Label wordDraw;

    @FXML
    private ScrollPane chatScrollPane;

    public String[] systemWords;

    private long startTime;
    private long duration;
    private boolean timerRunning = false;
    private boolean drawingAllowed = true;

    private int drawTime;
    @SuppressWarnings("unused")
    private String roomCode;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        brushSize.setText("7");
        colorPicker.setValue(Color.BLACK);

        canvas.setOnMousePressed(event -> {
            if (!timerRunning) {
                if (drawingAllowed) {
                    startTime = System.nanoTime();
                    timer.start();
                    timerRunning = true;
                }
            }
        });
        GraphicsContext g = canvas.getGraphicsContext2D();

        canvas.setOnMouseDragged(e -> {
            double size = Double.parseDouble(brushSize.getText());
            double x = e.getX() - size / 2;
            double y = e.getY() - size / 2;

            if (eraser.isSelected()) {
                g.clearRect(x, y, size, size);
            } else {
                g.setFill(colorPicker.getValue());
                g.fillRect(x, y, size, size);
            }
        });

        guessBox.setOnAction(event -> {
            String guess = guessBox.getText().trim();
            if (!guess.isEmpty()) {
                guessArea.appendText(guess + "\n");
                guessBox.clear();
            }
        });

        guessArea.textProperty().addListener((observable, oldValue, newValue) -> {
            chatScrollPane.setVvalue(1.0);
        });

        displayRandomWord();

        // Set draw time
        duration = drawTime * 1000000000L; // Convert seconds to nanoseconds

    }

    private AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            long elapsedTime = now - startTime;
            long remainingTime = duration - elapsedTime;

            if (remainingTime <= 0) {
                stopTimer();
                timerLabel.setText("Time's up!");
                drawingAllowed = false;

                // Lower the opacity of the canvas
                canvas.setOpacity(0.5); // Adjust the opacity value as needed

                // Disable drawing
                canvas.setDisable(true);

                // Set canvas background to gray with opacity
                canvas.setStyle("-fx-background-color: rgba(128, 128, 128, 0.5);");
            } else {
                long seconds = remainingTime / 1000000000L;
                timerLabel.setText("Time left: " + seconds + " seconds");
            }
        }
    };

    private void stopTimer() {
        timer.stop();
        timerRunning = false;
    }

    public void setSystemWords(String[] systemWords) {
        this.systemWords = systemWords;
    }

    public void displayRandomWord() {
        if (systemWords != null && systemWords.length > 0) {
            Random random = new Random();
            int index = random.nextInt(systemWords.length);
            wordDraw.setText(systemWords[index]);
        } else {
            wordDraw.setText("No words available");
        }
    }

    // Method to set the draw time
    public void setDrawTime(int drawTime) {
        this.drawTime = drawTime;
    }

    // Method to set the room code
    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public void updateTimerLabel() {
        // Set draw time
        duration = drawTime * 1000000000L; // Convert seconds to nanoseconds
        timerLabel.setText("Time left: " + drawTime + " seconds");
    }

    public void updateRoomCodeLabel(String code) {
        roomCodeLabel.setText(code);
    }

    @FXML
    void backToSettings(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("settings.fxml"));
        st = (Stage)((Node)event.getSource()).getScene().getWindow();
        sc = new Scene(root);
        st.setScene(sc);
        st.show();

    }
}
