package com.example.airportsecurity.Ahsan.CommandingOfficer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ManageStaffController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField roleField;

    @FXML
    private TableView<Staff> staffTable;

    @FXML
    private TableColumn<Staff, String> nameColumn;

    @FXML
    private TableColumn<Staff, String> roleColumn;

    private ObservableList<Staff> staffList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set table columns
        nameColumn.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getName())
        );

        roleColumn.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getRole())
        );

        // Set data to table
        staffTable.setItems(staffList);
    }

    // Add Staff
    @FXML
    private void addStaff() {
        String name = nameField.getText();
        String role = roleField.getText();

        if (!name.isEmpty() && !role.isEmpty()) {
            staffList.add(new Staff(name, role));
            clearFields();
        }
    }

    // Remove Staff
    @FXML
    private void removeStaff() {
        Staff selected = staffTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            staffList.remove(selected);
        }
    }

    // Clear Fields
    @FXML
    private void clearFields() {
        nameField.clear();
        roleField.clear();
    }
}
