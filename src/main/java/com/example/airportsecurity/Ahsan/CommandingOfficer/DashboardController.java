package com.example.airportsecurity.Ahsan.CommandingOfficer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DashboardController
{
    private void switchScene(String fxmlFile) {
        try {
            Stage stage = (Stage) new javafx.scene.control.Button().getScene().getWindow();
        } catch (Exception ignored) {}

    }

    private void load(String file, javafx.event.ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource())
                    .getScene().getWindow();

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goManageFlights(javafx.event.ActionEvent event) {
        load("manageflights.fxml", event);
    }

    @FXML
    private void goAssignStaff(javafx.event.ActionEvent event) {
        load("assignstaff.fxml", event);
    }

    @FXML
    private void goApproveCancel(javafx.event.ActionEvent event) {
        load("approvecancel.fxml", event);
    }

    @FXML
    private void goViewDetails(javafx.event.ActionEvent event) {
        load("viewdetails.fxml", event);
    }

    @FXML
    private void goAnnouncements(javafx.event.ActionEvent event) {
        load("announcement.fxml", event);
    }

    @FXML
    private void goMonitorStatus(javafx.event.ActionEvent event) {
        load("monitorstatus.fxml", event);
    }

    @FXML
    private void goEmergency(javafx.event.ActionEvent event) {
        load("emergencyalert.fxml", event);
    }
}