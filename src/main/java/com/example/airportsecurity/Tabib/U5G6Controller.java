package com.example.airportsecurity.Tabib;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class U5G6Controller
{
    @javafx.fxml.FXML
    private CheckBox broadcastCheckBox;
    @javafx.fxml.FXML
    private ChoiceBox<String> locationChoiceBox;
    @javafx.fxml.FXML
    private TextArea messageTextArea;
    @javafx.fxml.FXML
    private ChoiceBox<String> emergencyTypeChoiceBox;
    @javafx.fxml.FXML
    private Label statusLabel;

    @javafx.fxml.FXML
    public void initialize() {

        emergencyTypeChoiceBox.setItems(FXCollections.observableArrayList(
                "Fire",
                "Medical",
                "Threat"
        ));

        locationChoiceBox.setItems(FXCollections.observableArrayList(
                "Terminal 1",
                "Terminal 2",
                "Gate 3",
                "Gate 5",
                "Baggage Claim",
                "Security Checkpoint",
                "Runway Area",
                "Control Room"
        ));

        statusLabel.setVisible(true);
        statusLabel.setText("Select emergency type, location, and write a short message.");
        statusLabel.setStyle("-fx-text-fill: green;");
    }

    @javafx.fxml.FXML
    public void clearButton(ActionEvent actionEvent) {
        emergencyTypeChoiceBox.setValue(null);
        locationChoiceBox.setValue(null);
        messageTextArea.clear();
        broadcastCheckBox.setSelected(false);

        statusLabel.setVisible(true);
        statusLabel.setText("Form cleared.");
        statusLabel.setStyle("-fx-text-fill: blue;");
    }

    @javafx.fxml.FXML
    public void backButton(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/airportsecurity/Tabib/ControlRoomOperatorDashboard.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void confirmEmergencyButton(ActionEvent actionEvent) {
        String emergencyType = emergencyTypeChoiceBox.getValue();
        String location = locationChoiceBox.getValue();
        String message = messageTextArea.getText();

        if (emergencyType == null || location == null || message == null || message.trim().isEmpty()) {
            statusLabel.setVisible(true);
            statusLabel.setText("Please fill in all required fields.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if (!broadcastCheckBox.isSelected()) {
            statusLabel.setVisible(true);
            statusLabel.setText("Please check 'Notify All Units' before confirming.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        statusLabel.setVisible(true);
        statusLabel.setText("Emergency broadcast sent for " + emergencyType + " at " + location + ".");
        statusLabel.setStyle("-fx-text-fill: green;");
    }
}