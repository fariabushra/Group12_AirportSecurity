package com.example.airportsecurity.Tabib;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class U6G4Controller
{
    @javafx.fxml.FXML
    private TableColumn<AuditLog, String> timeColumn;
    @javafx.fxml.FXML
    private TableColumn<AuditLog, String> actionColumn;
    @javafx.fxml.FXML
    private DatePicker fromDatePicker;
    @javafx.fxml.FXML
    private TableColumn<AuditLog, String> statusColumn;
    @javafx.fxml.FXML
    private DatePicker toDatePicker;
    @javafx.fxml.FXML
    private ComboBox<String> logTypeComboBox;
    @javafx.fxml.FXML
    private TableColumn<AuditLog, String> moduleColumn;
    @javafx.fxml.FXML
    private TableView<AuditLog> logTableView;
    @javafx.fxml.FXML
    private Label statusLabel;
    @javafx.fxml.FXML
    private TableColumn<AuditLog, String> userColumn;

    private ObservableList<AuditLog> allLogs;
    private ObservableList<AuditLog> filteredLogs;

    @javafx.fxml.FXML
    public void initialize() {

        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        userColumn.setCellValueFactory(new PropertyValueFactory<>("user"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
        moduleColumn.setCellValueFactory(new PropertyValueFactory<>("module"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        logTypeComboBox.setItems(FXCollections.observableArrayList(
                "Login Logs",
                "Incident Logs",
                "Admin Changes"
        ));

        allLogs = FXCollections.observableArrayList(
                new AuditLog("2026-04-01 09:15 AM", "Ahsan Rahman", "Logged in successfully", "Authentication", "Success", "Login Logs", LocalDate.of(2026, 4, 1)),
                new AuditLog("2026-04-02 11:40 AM", "Nusrat Jahan", "Created incident report", "Incident Module", "Success", "Incident Logs", LocalDate.of(2026, 4, 2)),
                new AuditLog("2026-04-03 02:10 PM", "Mehedi Hasan", "Updated user role permissions", "Admin Panel", "Success", "Admin Changes", LocalDate.of(2026, 4, 3)),
                new AuditLog("2026-04-04 08:30 AM", "Ahsan Rahman", "Failed login attempt", "Authentication", "Failed", "Login Logs", LocalDate.of(2026, 4, 4)),
                new AuditLog("2026-04-05 03:20 PM", "Nusrat Jahan", "Closed incident case", "Incident Module", "Success", "Incident Logs", LocalDate.of(2026, 4, 5)),
                new AuditLog("2026-04-06 10:05 AM", "Admin User", "Changed backup schedule", "System Settings", "Success", "Admin Changes", LocalDate.of(2026, 4, 6))
        );

        filteredLogs = FXCollections.observableArrayList();
        filteredLogs.addAll(allLogs);

        logTableView.setItems(filteredLogs);

        statusLabel.setVisible(true);
        statusLabel.setText("Select filters and click Apply Filter.");
        statusLabel.setStyle("-fx-text-fill: green;");
    }

    @javafx.fxml.FXML
    public void clearButton(ActionEvent actionEvent) {
        logTypeComboBox.setValue(null);
        fromDatePicker.setValue(null);
        toDatePicker.setValue(null);

        filteredLogs.clear();
        filteredLogs.addAll(allLogs);
        logTableView.setItems(filteredLogs);

        statusLabel.setVisible(true);
        statusLabel.setText("Filters cleared. All logs are now displayed.");
        statusLabel.setStyle("-fx-text-fill: blue;");
    }

    @javafx.fxml.FXML
    public void exportLogsButton(ActionEvent actionEvent) {
        if (logTableView.getItems().isEmpty()) {
            statusLabel.setVisible(true);
            statusLabel.setText("There are no logs to export.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        ChoiceDialog<String> choiceDialog = new ChoiceDialog<>("CSV", "CSV", "PDF");
        choiceDialog.setTitle("Export Logs");
        choiceDialog.setHeaderText("Choose export format");
        choiceDialog.setContentText("Format:");

        Optional<String> result = choiceDialog.showAndWait();

        if (result.isPresent()) {
            statusLabel.setVisible(true);
            statusLabel.setText("Logs exported successfully as " + result.get() + ".");
            statusLabel.setStyle("-fx-text-fill: green;");
        }
    }

    @javafx.fxml.FXML
    public void applyFilterButton(ActionEvent actionEvent) {
        String selectedLogType = logTypeComboBox.getValue();
        LocalDate fromDate = fromDatePicker.getValue();
        LocalDate toDate = toDatePicker.getValue();

        if (fromDate != null && toDate != null && fromDate.isAfter(toDate)) {
            statusLabel.setVisible(true);
            statusLabel.setText("From Date cannot be after To Date.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        filteredLogs.clear();

        for (AuditLog log : allLogs) {
            boolean matchesType = true;
            boolean matchesDate = true;

            if (selectedLogType != null && !selectedLogType.equals(log.getLogType())) {
                matchesType = false;
            }

            if (fromDate != null && log.getLogDate().isBefore(fromDate)) {
                matchesDate = false;
            }

            if (toDate != null && log.getLogDate().isAfter(toDate)) {
                matchesDate = false;
            }

            if (matchesType && matchesDate) {
                filteredLogs.add(log);
            }
        }

        logTableView.setItems(filteredLogs);

        statusLabel.setVisible(true);

        if (filteredLogs.isEmpty()) {
            statusLabel.setText("No logs found for the selected filter.");
            statusLabel.setStyle("-fx-text-fill: red;");
        } else {
            statusLabel.setText("Filtered logs loaded successfully.");
            statusLabel.setStyle("-fx-text-fill: green;");
        }
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
}