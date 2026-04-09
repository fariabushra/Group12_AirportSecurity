package com.example.airportsecurity.Samia;

import com.example.airportsecurity.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import java.io.*;

public class SecurityGoal3Controller {

    @FXML private ComboBox<String> zoneComboBox;
    @FXML private ComboBox<String> statusComboBox;

    @FXML
    public void initialize() {
        zoneComboBox.getItems().addAll("Terminal A", "Terminal B", "Parking", "Runway Perimeter");
        statusComboBox.getItems().addAll("Clear", "Maintenance Needed", "Suspicious Activity");
    }

    @FXML
    void submitLogOnAction(ActionEvent event) {
        if (zoneComboBox.getValue() == null || statusComboBox.getValue() == null) {
            showAlert("Selection Error", "Please select both Zone and Status.");
            return;
        }

        File f = new File("PatrolLogs.bin");
        try (FileOutputStream fos = new FileOutputStream(f, true);
             ObjectOutputStream oos = f.length() == 0 ? new ObjectOutputStream(fos) : new AppendableObjectOutputStream(fos)) {

            oos.writeObject(new PatrolLog(zoneComboBox.getValue(), statusComboBox.getValue()));
            showAlert("Success", "Patrol Log Saved!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setContentText(content);
        a.showAndWait();
    }

    @FXML
    void goBackOnAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(new FXMLLoader(HelloApplication.class.getResource("Samia/SecurityOfficer.fxml")).load()));
    }
}