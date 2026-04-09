package com.example.airportsecurity.Samia;

import com.example.airportsecurity.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.*;

public class SecurityGoal7Controller {

    @FXML private TextField ownerField;
    @FXML private TextField flightField;
    @FXML private TextField reasonField;
    @FXML private ComboBox<String> outcomeCombo;

    @FXML
    public void initialize() {
        outcomeCombo.getItems().addAll("Cleared", "Confiscated Item", "Detained Passenger");
    }

    @FXML
    void saveLogOnAction(ActionEvent event) {
        String owner = ownerField.getText();
        String flight = flightField.getText();
        String reason = reasonField.getText();
        String outcome = outcomeCombo.getValue();

        if (owner.isEmpty() || flight.isEmpty() || reason.isEmpty() || outcome == null) {
            showAlert("Error", "All fields are required.");
            return;
        }

        File f = new File("BaggageLogs.bin");
        try (FileOutputStream fos = new FileOutputStream(f, true);
             ObjectOutputStream oos = f.length() == 0 ? new ObjectOutputStream(fos) : new AppendableObjectOutputStream(fos)) {

            oos.writeObject(new BaggageLog(owner, flight, reason, outcome));
            showAlert("Success", "Baggage inspection logged.");
            ownerField.clear(); flightField.clear(); reasonField.clear();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(content);
        a.showAndWait();
    }

    @FXML
    void goBackOnAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(new FXMLLoader(HelloApplication.class.getResource("Samia/SecurityOfficer.fxml")).load()));
    }
}