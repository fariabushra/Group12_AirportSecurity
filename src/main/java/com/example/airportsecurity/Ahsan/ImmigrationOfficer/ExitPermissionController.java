package com.example.airportsecurity.Ahsan.ImmigrationOfficer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
public class ExitPermissionController
{
    @FXML private TextField nameField;
    @FXML private TextField passportField;
    @FXML private TextField visaField;

    @FXML private TextArea outputArea;

    private ExitPermission exit;

    @FXML
    public void requestExit() {
        exit = new ExitPermission(
                nameField.getText(),
                passportField.getText(),
                visaField.getText()
        );

        outputArea.setText("Exit requested by: " + exit.getTravelerName());
    }

    @FXML
    public void checkPassport() {
        if (exit != null)
            outputArea.setText("Passport: " + exit.getPassportNo());
        else
            outputArea.setText("No request found.");
    }

    @FXML
    public void checkVisa() {
        if (exit != null)
            outputArea.setText("Visa Status: " + exit.getVisaStatus());
        else
            outputArea.setText("No request found.");
    }

    @FXML
    public void checkIssues() {
        outputArea.setText("No violations found (demo check).");
    }

    @FXML
    public void allowExit() {
        if (exit != null) {
            exit.allowExit();
            outputArea.setText("EXIT APPROVED ✔");
        }
    }

    @FXML
    public void denyExit() {
        if (exit != null) {
            exit.denyExit();
            outputArea.setText("EXIT DENIED ✖");
        }
    }

    @FXML
    public void goBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("dashboard2.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource())
                    .getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            outputArea.setText("Error loading dashboard2.fxml");
        }
    }
}