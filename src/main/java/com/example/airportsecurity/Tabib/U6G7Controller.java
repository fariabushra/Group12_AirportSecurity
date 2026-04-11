package com.example.airportsecurity.Tabib;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class U6G7Controller
{
    @javafx.fxml.FXML
    private TableColumn<ServiceStatus, String> serviceTypeColumn;
    @javafx.fxml.FXML
    private Label alertLabel;
    @javafx.fxml.FXML
    private TableColumn<ServiceStatus, String> statusColumn;
    @javafx.fxml.FXML
    private TableColumn<ServiceStatus, String> serviceNameColumn;
    @javafx.fxml.FXML
    private TableView<ServiceStatus> serviceTableView;
    @javafx.fxml.FXML
    private TableColumn<ServiceStatus, String> serialNoColumn;
    @javafx.fxml.FXML
    private TableColumn<ServiceStatus, String> lastCheckedColumn;
    @javafx.fxml.FXML
    private Label statusLabel;

    private ObservableList<ServiceStatus> serviceList;

    @javafx.fxml.FXML
    public void initialize() {

        serialNoColumn.setCellValueFactory(new PropertyValueFactory<>("serialNo"));
        serviceNameColumn.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        serviceTypeColumn.setCellValueFactory(new PropertyValueFactory<>("serviceType"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        lastCheckedColumn.setCellValueFactory(new PropertyValueFactory<>("lastChecked"));

        serviceList = FXCollections.observableArrayList(
                new ServiceStatus("1", "CCTV Stream", "Monitoring", "Running", "10:15 AM"),
                new ServiceStatus("2", "Alert Engine", "Security Core", "Down", "10:18 AM"),
                new ServiceStatus("3", "Database Service", "Storage", "Running", "10:20 AM"),
                new ServiceStatus("4", "Access Control API", "Authentication", "Running", "10:22 AM")
        );

        serviceTableView.setItems(serviceList);

        statusLabel.setVisible(true);
        statusLabel.setText("Select a failing service and click Restart Service.");
        statusLabel.setStyle("-fx-text-fill: green;");
    }

    @javafx.fxml.FXML
    public void restartServiceButton(ActionEvent actionEvent) {
        ServiceStatus selectedService = serviceTableView.getSelectionModel().getSelectedItem();

        if (selectedService == null) {
            statusLabel.setVisible(true);
            statusLabel.setText("Please select a service first.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if (!selectedService.getStatus().equalsIgnoreCase("Down")) {
            statusLabel.setVisible(true);
            statusLabel.setText("Selected service is already running.");
            statusLabel.setStyle("-fx-text-fill: blue;");
            return;
        }

        selectedService.setStatus("Running");
        selectedService.setLastChecked("Just now");
        serviceTableView.refresh();

        alertLabel.setText("Alert: All critical services operational");
        alertLabel.setStyle("-fx-text-fill: green; -fx-font-size: 16px; -fx-font-weight: bold;");

        statusLabel.setVisible(true);
        statusLabel.setText(selectedService.getServiceName() + " restarted successfully.");
        statusLabel.setStyle("-fx-text-fill: green;");
    }

    @javafx.fxml.FXML
    public void backButton(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/airportsecurity/Tabib/ITAdministratorDashboard.fxml"));
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
    public void clearSelectionButton(ActionEvent actionEvent) {
        serviceTableView.getSelectionModel().clearSelection();

        statusLabel.setVisible(true);
        statusLabel.setText("Selection cleared.");
        statusLabel.setStyle("-fx-text-fill: blue;");
    }
}