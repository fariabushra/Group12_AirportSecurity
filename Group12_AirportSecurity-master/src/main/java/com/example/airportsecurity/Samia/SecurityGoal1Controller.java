package com.example.airportsecurity.Samia;

import com.example.airportsecurity.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.*;

public class SecurityGoal1Controller {

    @FXML private TextField incidentTitleField;
    @FXML private TextArea incidentDescriptionArea;

    @FXML
    void submitReportOnAction(ActionEvent event) {
        String title = incidentTitleField.getText();
        String desc = incidentDescriptionArea.getText();

        if(title.isEmpty() || desc.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill in all fields.");
            return;
        }

        File f = new File("IncidentReports.bin");
        try {
            FileOutputStream fos = new FileOutputStream(f, true);
            ObjectOutputStream oos;
            if (f.length() == 0) {
                oos = new ObjectOutputStream(fos);
            } else {
                oos = new AppendableObjectOutputStream(fos);
            }
            oos.writeObject(new Incident(title, desc));
            oos.close();

            showAlert(Alert.AlertType.INFORMATION, "Success", "Incident Reported!");
            incidentTitleField.clear();
            incidentDescriptionArea.clear();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "File Error", "Could not save.");
        }
    }

    @FXML
    void goBackOnAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Samia/SecurityOfficer.fxml"));
        stage.setScene(new Scene(loader.load()));
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}