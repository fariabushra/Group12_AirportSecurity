package com.example.airportsecurity.Faria;

import com.example.airportsecurity.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CheckpostOfficerController
{
    @javafx.fxml.FXML
    public void initialize() {
    }


    @javafx.fxml.FXML
    public void dutyOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void messageOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void vehicleOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void reportActivityOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void dutyStatusOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void denyFormOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void logOut(ActionEvent actionEvent) {
    }


    @javafx.fxml.FXML
    public void identityVerificationOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void addPersonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Faria/addPerson.fxml"));
            Scene nextScene = new Scene(fxmlLoader.load());
            Stage nextStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            nextStage.setTitle("Add Person");
            nextStage.setScene(nextScene);
            nextStage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}