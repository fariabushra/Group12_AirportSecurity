package com.example.airportsecurity.Ahsan.ImmigrationOfficer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
public class EntryPermissionController
{
    @FXML private TextField nameField;
    @FXML private TextField passportField;
    @FXML private TextField visaField;

    @FXML private TextArea outputArea;

    private EntryPermission entry;

    @FXML
    public void arrive() {
        entry = new EntryPermission(
                nameField.getText(),
                passportField.getText(),
                visaField.getText()
        );

        outputArea.setText("Traveler Arrived: " + entry.getTravelerName());
    }

    @FXML
    public void showPassport() {
        if (entry != null)
            outputArea.setText("Passport No: " + entry.getPassportNo());
        else
            outputArea.setText("No traveler data.");
    }

    @FXML
    public void checkVisa() {
        if (entry != null)
            outputArea.setText("Visa Status: " + entry.getVisaStatus());
        else
            outputArea.setText("No traveler data.");
    }

    @FXML
    public void askQuestions() {
        outputArea.setText("Basic screening questions completed.");
    }

    @FXML
    public void allowEntry() {
        if (entry != null) {
            entry.allow();
            outputArea.setText("ENTRY ALLOWED ✔");
        }
    }

    @FXML
    public void denyEntry() {
        if (entry != null) {
            entry.deny();
            outputArea.setText("ENTRY DENIED ✖");
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