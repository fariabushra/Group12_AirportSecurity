package com.example.airportsecurity.Faria;

import com.example.airportsecurity.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MedicalEmergencyStaffController
{
    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void updatePatientStatusButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void progressButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void recordCaseButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void closeCaseButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void requestSupportButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void communicationButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void logoutButton(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
            Scene nextScene = new Scene(fxmlLoader.load());
            Stage nextStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            nextStage.setTitle("Login");
            nextStage.setScene(nextScene);
            nextStage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void viewLocationButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void viewCaseButton(ActionEvent actionEvent) {
    }
}