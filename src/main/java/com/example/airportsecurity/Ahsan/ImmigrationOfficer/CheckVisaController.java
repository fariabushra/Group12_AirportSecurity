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

public class CheckVisaController
{
    @FXML
    private TextField nameField;

    @FXML
    private TextField typeField;

    @FXML
    private TextField expiryField;

    @FXML
    private TableView<Visa> visaTable;

    @FXML
    private TableColumn<Visa, String> nameCol;

    @FXML
    private TableColumn<Visa, String> typeCol;

    @FXML
    private TableColumn<Visa, String> expiryCol;

    @FXML
    private TableColumn<Visa, String> statusCol;

    private ObservableList<Visa> visaList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        nameCol.setCellValueFactory(new PropertyValueFactory<>("applicantName"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("visaType"));
        expiryCol.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        visaTable.setItems(visaList);
    }

    @FXML
    public void addVisa() {
        Visa v = new Visa(
                nameField.getText(),
                typeField.getText(),
                expiryField.getText()
        );

        visaList.add(v);
        clearFields();
    }

    @FXML
    public void approveVisa() {
        Visa selected = visaTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.approve();
            visaTable.refresh();
        }
    }

    @FXML
    public void rejectVisa() {
        Visa selected = visaTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.reject();
            visaTable.refresh();
        }
    }

    private void clearFields() {
        nameField.clear();
        typeField.clear();
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