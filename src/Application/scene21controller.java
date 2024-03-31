package Application;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class scene21controller {

    @FXML
    private Button backbt;

    @FXML
    private MenuItem saveim;

    @FXML
    private Button reloadbt;

    @FXML
    private Button pointButton;

    @FXML
    private Label pointsBox;

    @FXML
    private Pane scene2ap;

    @FXML
    private MenuItem submitim;

    @FXML
    private Label mlarea;

    @FXML
    private ImageView sptopwritten;

    @FXML
    private Stage st;
    private Scene sc;

    @FXML
    private Canvas canvas;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private TextField brushSize;

    @FXML
    private CheckBox eraser;

    @FXML
    private Label timerLabel;

    private long startTime;
    private final long duration = 45 * 1000000000L; // 45 seconds in nanoseconds
    private boolean timerRunning = false;
    private boolean drawingAllowed = true;

    private AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            long elapsedTime = now - startTime;
            long remainingTime = duration - elapsedTime;
    
            if (remainingTime <= 0) {
                stopTimer();
                timerLabel.setText("Time's up! Save your drawing.");
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
    @FXML
    private void handleSaveAction() {
        // Add code to save the drawing
    
        // Once saved, reset the canvas and allow drawing again
        canvas.setDisable(false);
        canvas.setStyle("-fx-background-color: transparent;"); // Reset canvas background
        drawingAllowed = true;
    }
    @FXML
    public void sw2to1(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("main1.fxml"));
        st = (Stage)((Node)event.getSource()).getScene().getWindow();
        sc = new Scene(root);
        st.setScene(sc);
        st.show();
    }

    @FXML
    void givePoints(ActionEvent event) {
        int currentPoints = Integer.parseInt(pointsBox.getText());
        int newPoints = currentPoints + 10;
        pointsBox.setText(String.valueOf(newPoints));
    }

    public void initialize() {

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
    }

    public void onSave() {
        try {
            Image snapshot = canvas.snapshot(null, null);
            File file = new File("paint.png"); 
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", file);
            System.out.println("Image saved successfully at: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Failed to save image: " + e.getMessage());
            e.printStackTrace();
        }
    }
    

    public void onExit() {
        Platform.exit();
    }
    @FXML
    private void handleReloadAction(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("main2.fxml"));
        st = (Stage)((Node)event.getSource()).getScene().getWindow();
        sc = new Scene(root);
        st.setScene(sc);
        st.show();
    }

    @SuppressWarnings("deprecation")
    @FXML
    private void handleSaveAction(ActionEvent event) {
        try {
            // Call the Python script
            Process process = Runtime.getRuntime().exec("python src\\Application\\test.py");

            // Read the output of the Python script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // Wait for the process to finish
            int exitCode = process.waitFor();

            // Check if the process exited successfully
            if (exitCode == 0) {
                // Set the output of the Python script to the mlarea label
                mlarea.setText(output.toString());
            } else {
                System.err.println("Error: Python script exited with non-zero exit code: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

