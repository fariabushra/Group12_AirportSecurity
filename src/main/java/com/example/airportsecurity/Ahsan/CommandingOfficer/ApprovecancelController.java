package com.example.airportsecurity.Ahsan.CommandingOfficer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
public class ApprovecancelController
{
    @FXML private TextField flightIdField;
    @FXML private Label statusLabel;

    @FXML private TableView<FlightsControl> flightTable;
    @FXML private TableColumn<FlightsControl, String> idColumn;
    @FXML private TableColumn<FlightsControl, String> destinationColumn;
    @FXML private TableColumn<FlightsControl, String> statusColumn;

    private final ObservableList<FlightsControl> flightList =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

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

        flightList.add(new FlightsControl("F101", "Dhaka", "Pending"));
        flightList.add(new FlightsControl("F102", "Chittagong", "Pending"));
    }

    @FXML
    private void handleApprove() {

        String id = flightIdField.getText().trim();

        if (id.isEmpty()) {
            statusLabel.setText("Enter Flight ID");
            return;
        }

        for (FlightsControl f : flightList) {
            if (f.getFlightId().equalsIgnoreCase(id)) {
                f.setStatus("Approved");
                flightTable.refresh();
                statusLabel.setText("Flight approved");
                return;
            }
        }

        statusLabel.setText("Flight not found");
    }

    @FXML
    private void handleCancel() {

        String id = flightIdField.getText().trim();

        if (id.isEmpty()) {
            statusLabel.setText("Enter Flight ID");
            return;
        }

        for (FlightsControl f : flightList) {
            if (f.getFlightId().equalsIgnoreCase(id)) {
                f.setStatus("Cancelled");
                flightTable.refresh();
                statusLabel.setText("Flight cancelled");
                return;
            }
        }

        statusLabel.setText("Flight not found");
    }
}