package com.example.airportsecurity.Ahsan.ImmigrationOfficer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
public class CheckpassportController
{
    @FXML private TextField nameField;
    @FXML private TextField passportField;
    @FXML private TextField expiryField;

    @FXML private TableView<Passport> table;

    @FXML private TableColumn<Passport, String> nameCol;
    @FXML private TableColumn<Passport, String> passportCol;
    @FXML private TableColumn<Passport, String> expiryCol;
    @FXML private TableColumn<Passport, String> statusCol;

    private ObservableList<Passport> list = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        passportCol.setCellValueFactory(new PropertyValueFactory<>("passportNo"));
        expiryCol.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        table.setItems(list);
    }

    @FXML
    public void addPassport() {
        Passport p = new Passport(
                nameField.getText(),
                passportField.getText(),
                expiryField.getText()
        );

        list.add(p);
        clearFields();
    }

    @FXML
    public void markValid() {
        Passport selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.markValid();
            table.refresh();
        }
    }

    @FXML
    public void markInvalid() {
        Passport selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.markInvalid();
            table.refresh();
        }
    }

    private void clearFields() {
        nameField.clear();
        passportField.clear();
        expiryField.clear();
    }

    @FXML
    public void goBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("dashboard2.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource())
                    .getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}