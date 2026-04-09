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

public class SecurityGoal6Controller {

    @FXML private ComboBox<String> typeCombo;
    @FXML private TextField locationField;
    @FXML private ComboBox<String> severityCombo;

    @FXML
    public void initialize() {
        typeCombo.getItems().addAll("Fire", "Security Breach", "Medical Emergency", "Unattended Item");
        severityCombo.getItems().addAll("Low", "Medium", "High", "Critical");
    }

    @FXML
    void triggerAlertOnAction(ActionEvent event) {
        String type = typeCombo.getValue();
        String loc = locationField.getText();
        String sev = severityCombo.getValue();

        if (type == null || loc.isEmpty() || sev == null) {
            showAlert(Alert.AlertType.WARNING, "Input Error", "Please fill all alert details!");
            return;
        }

        File f = new File("EmergencyAlerts.bin");
        try (FileOutputStream fos = new FileOutputStream(f, true);
             ObjectOutputStream oos = f.length() == 0 ? new ObjectOutputStream(fos) : new AppendableObjectOutputStream(fos)) {

            oos.writeObject(new EmergencyAlert(type, loc, sev));
            showAlert(Alert.AlertType.CONFIRMATION, "ALERT DISPATCHED", "Emergency services have been notified.");
            locationField.clear();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void goBackOnAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(new FXMLLoader(HelloApplication.class.getResource("Samia/SecurityOfficer.fxml")).load()));
    }
}