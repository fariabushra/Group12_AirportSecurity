package com.example.airportsecurity.Ahsan.CommandingOfficer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
public class MonitorstatusController
{
    @FXML private TextField flightIdField;
    @FXML private ComboBox<String> statusBox;
    @FXML private Label statusLabel;

    @FXML private TableView<FlightsControl> flightTable;
    @FXML private TableColumn<FlightsControl, String> idColumn;
    @FXML private TableColumn<FlightsControl, String> destinationColumn;
    @FXML private TableColumn<FlightsControl, String> statusColumn;

    private final ObservableList<FlightsControl> flightList =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        statusBox.setItems(FXCollections.observableArrayList(
                "On Time",
                "Delayed"
        ));

        idColumn.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getFlightId()
                )
        );

        destinationColumn.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getDestination()
                )
        );

        statusColumn.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getStatus()
                )
        );

        flightTable.setItems(flightList);

        flightList.add(new FlightsControl("F101", "Dhaka", "On Time"));
        flightList.add(new FlightsControl("F102", "Chittagong", "Delayed"));
    }

    @FXML
    private void handleUpdate() {

        FlightsControl selected = flightTable.getSelectionModel().getSelectedItem();

        String status = statusBox.getValue();

        if (selected == null) {
            statusLabel.setText("Select a flight");
            return;
        }

        if (status == null) {
            statusLabel.setText("Select a status");
            return;
        }

        selected.setStatus(status);

        flightTable.refresh();

        statusLabel.setText("Status updated");
    }
}