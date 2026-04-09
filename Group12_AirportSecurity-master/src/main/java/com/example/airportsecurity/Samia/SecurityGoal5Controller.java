package com.example.airportsecurity.Samia;

import com.example.airportsecurity.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.*;

public class SecurityGoal5Controller {

    @FXML private TextField visitorNameField;
    @FXML private TextField purposeField;
    @FXML private TextField idField;

    @FXML
    void registerVisitorOnAction(ActionEvent event) {
        String name = visitorNameField.getText();
        String purpose = purposeField.getText();
        String id = idField.getText();

        if (name.isEmpty() || purpose.isEmpty() || id.isEmpty()) {
            showAlert("Error", "Please fill all fields.");
            return;
        }

        File f = new File("VisitorLogs.bin");
        try (FileOutputStream fos = new FileOutputStream(f, true);
             ObjectOutputStream oos = f.length() == 0 ? new ObjectOutputStream(fos) : new AppendableObjectOutputStream(fos)) {

            oos.writeObject(new Visitor(name, purpose, id));
            showAlert("Success", "Visitor registered successfully.");
            visitorNameField.clear(); purposeField.clear(); idField.clear();

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