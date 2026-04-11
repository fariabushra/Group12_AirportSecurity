package com.example.airportsecurity.Ahsan.CommandingOfficer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
public class ManageflightsController
{
    @FXML private TextField flightIdField;
    @FXML private TextField destinationField;
    @FXML private Label statusLabel;

    @FXML private TableView<ManageFlights> flightTable;
    @FXML private TableColumn<ManageFlights, String> idColumn;
    @FXML private TableColumn<ManageFlights, String> destinationColumn;

    private final ObservableList<ManageFlights> flightList =
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

        flightTable.setItems(flightList);
    }

    @FXML
    private void handleAddFlight() {

        String id = flightIdField.getText().trim();
        String dest = destinationField.getText().trim();

        if (id.isEmpty() || dest.isEmpty()) {
            statusLabel.setText("Fill all fields");
            return;
        }

        for (ManageFlights f : flightList) {
            if (f.getFlightId().equalsIgnoreCase(id)) {
                statusLabel.setText("Flight ID already exists");
                return;
            }
        }

        flightList.add(new ManageFlights(id, dest));

        flightIdField.clear();
        destinationField.clear();

        statusLabel.setText("Flight added successfully");
    }

    @FXML
    private void handleRemoveFlight() {

        ManageFlights selected = flightTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            statusLabel.setText("Select a flight first");
            return;
        }

        flightList.remove(selected);

        statusLabel.setText("Flight removed successfully");
    }
}