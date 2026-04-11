package com.example.airportsecurity.Tabib;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class U5G3Controller
{
    @javafx.fxml.FXML
    private TableColumn<SecurityAlert, String> timeColumn;
    @javafx.fxml.FXML
    private TableColumn<SecurityAlert, String> locationColumn;
    @javafx.fxml.FXML
    private ChoiceBox<String> actionChoiceBox;
    @javafx.fxml.FXML
    private TableView<SecurityAlert> alertTableView;
    @javafx.fxml.FXML
    private TableColumn<SecurityAlert, String> sensorTypeColumn;
    @javafx.fxml.FXML
    private TableColumn<SecurityAlert, String> statusColumn;
    @javafx.fxml.FXML
    private TableColumn<SecurityAlert, String> alertIdColumn;
    @javafx.fxml.FXML
    private TableColumn<SecurityAlert, String> cameraColumn;
    @javafx.fxml.FXML
    private Label statusLabel;

    private ObservableList<SecurityAlert> alertList;

    @javafx.fxml.FXML
    public void initialize() {

        alertIdColumn.setCellValueFactory(new PropertyValueFactory<>("alertId"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        cameraColumn.setCellValueFactory(new PropertyValueFactory<>("camera"));
        sensorTypeColumn.setCellValueFactory(new PropertyValueFactory<>("sensorType"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        alertList = FXCollections.observableArrayList(
                new SecurityAlert("A-101", "10:12 AM", "Gate-3", "Cam-12", "Motion Alarm", "Pending"),
                new SecurityAlert("A-102", "10:18 AM", "Baggage Area", "Cam-07", "Unauthorized Access", "Pending"),
                new SecurityAlert("A-103", "10:24 AM", "Checkpoint-2", "Cam-03", "Metal Detector Alert", "Pending"),
                new SecurityAlert("A-104", "10:31 AM", "Terminal Entry", "Cam-15", "Door Forced Open", "Pending")
        );

        alertTableView.setItems(alertList);

        actionChoiceBox.setItems(FXCollections.observableArrayList(
                "Dispatch Unit",
                "Call Supervisor",
                "Ignore"
        ));

        statusLabel.setVisible(true);
        statusLabel.setText("Select an alert and choose an action.");
        statusLabel.setStyle("-fx-text-fill: green;");
    }

    @javafx.fxml.FXML
    public void submitActionButton(ActionEvent actionEvent) {
        SecurityAlert selectedAlert = alertTableView.getSelectionModel().getSelectedItem();
        String selectedAction = actionChoiceBox.getValue();

        if (selectedAlert == null) {
            statusLabel.setVisible(true);
            statusLabel.setText("Please select an alert first.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if (selectedAction == null || selectedAction.isEmpty()) {
            statusLabel.setVisible(true);
            statusLabel.setText("Please select an action from the dropdown.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if (selectedAction.equals("Dispatch Unit")) {
            selectedAlert.setStatus("Unit Dispatched");
            statusLabel.setText("Security unit dispatched for " + selectedAlert.getLocation() + ".");
        }
        else if (selectedAction.equals("Call Supervisor")) {
            selectedAlert.setStatus("Supervisor Called");
            statusLabel.setText("Supervisor notified for " + selectedAlert.getLocation() + ".");
        }
        else if (selectedAction.equals("Ignore")) {
            selectedAlert.setStatus("Ignored");
            statusLabel.setText("Alert marked as ignored.");
        }

        alertTableView.refresh();
        statusLabel.setVisible(true);
        statusLabel.setStyle("-fx-text-fill: green;");
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
    public void viewButton(ActionEvent actionEvent) {
        SecurityAlert selectedAlert = alertTableView.getSelectionModel().getSelectedItem();

        if (selectedAlert == null) {
            statusLabel.setVisible(true);
            statusLabel.setText("Please select an alert to view.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        statusLabel.setVisible(true);
        statusLabel.setText(
                "Alert Details: " +
                        selectedAlert.getAlertId() + ", " +
                        selectedAlert.getLocation() + ", " +
                        selectedAlert.getSensorType() + ", Camera: " +
                        selectedAlert.getCamera()
        );
        statusLabel.setStyle("-fx-text-fill: blue;");
    }

    @javafx.fxml.FXML
    public void openCameraButton(ActionEvent actionEvent) {
        SecurityAlert selectedAlert = alertTableView.getSelectionModel().getSelectedItem();

        if (selectedAlert == null) {
            statusLabel.setVisible(true);
            statusLabel.setText("Please select an alert first.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        statusLabel.setVisible(true);
        statusLabel.setText("Opening camera feed: " + selectedAlert.getCamera() + " for " + selectedAlert.getLocation() + ".");
        statusLabel.setStyle("-fx-text-fill: blue;");
    }
}