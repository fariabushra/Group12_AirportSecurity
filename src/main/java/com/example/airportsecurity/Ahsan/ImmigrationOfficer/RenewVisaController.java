package com.example.airportsecurity.Ahsan.ImmigrationOfficer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
public class RenewVisaController
{
    @FXML private TextField nameField;
    @FXML private TextField visaField;
    @FXML private TextField docField;
    @FXML private TextField daysField;

    @FXML private TextArea outputArea;

    private VisaRenewal renewal;

    // Step 1: Receive request
    @FXML
    public void receiveRequest() {
        renewal = new VisaRenewal(
                nameField.getText(),
                visaField.getText(),
                docField.getText(),
                Integer.parseInt(daysField.getText())
        );

        outputArea.setText("Request received for: " + renewal.getName());
    }

    // Step 2: Check old visa (dummy logic)
    @FXML
    public void checkVisa() {
        if (renewal != null) {
            outputArea.setText("Old Visa No: " + renewal.getOldVisaNo());
        } else {
            outputArea.setText("No request found.");
        }
    }

    // Step 3: Check documents
    @FXML
    public void checkDocs() {
        if (renewal != null) {
            outputArea.setText("Document Status: " + renewal.getDocumentStatus());
        }
    }

    // Step 4: Check stay time
    @FXML
    public void checkStay() {
        if (renewal != null) {
            if (renewal.getStayDays() > 90) {
                outputArea.setText("Stay exceeded limit!");
            } else {
                outputArea.setText("Stay within allowed limit.");
            }
        }
    }

    // Step 5: Approve
    @FXML
    public void approve() {
        if (renewal != null) {
            renewal.approve();
            outputArea.setText("VISA RENEWAL APPROVED ✔");
        }
    }

    // Reject
    @FXML
    public void reject() {
        if (renewal != null) {
            renewal.reject();
            outputArea.setText("VISA RENEWAL REJECTED ✖");
        }
    }

    // Back button
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