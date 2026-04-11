package com.example.airportsecurity.Ahsan.CommandingOfficer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class AssignstaffController
{
    @FXML private TextField flightIdField;
    @FXML private TextField staffNameField;
    @FXML private Label statusLabel;

    @FXML private TableView<Assignment> assignmentTable;
    @FXML private TableColumn<Assignment, String> flightColumn;
    @FXML private TableColumn<Assignment, String> staffColumn;

    private final ObservableList<Assignment> assignmentList =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        flightColumn.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getFlightId()
                )
        );

        staffColumn.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getStaffName()
                )
        );

        assignmentTable.setItems(assignmentList);
    }

    @FXML
    private void handleAssign() {

        String flightId = flightIdField.getText().trim();
        String staffName = staffNameField.getText().trim();

        if (flightId.isEmpty() || staffName.isEmpty()) {
            statusLabel.setText("Fill all fields");
            return;
        }

        for (Assignment a : assignmentList) {
            if (a.getFlightId().equalsIgnoreCase(flightId)) {
                statusLabel.setText("Flight already assigned");
                return;
            }
        }

        assignmentList.add(new Assignment(flightId, staffName));

        flightIdField.clear();
        staffNameField.clear();

        statusLabel.setText("Staff assigned successfully");
    }
}