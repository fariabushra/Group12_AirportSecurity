package com.example.airportsecurity.Ahsan.ImmigrationOfficer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
public class OverstayDecisionController
{
    @FXML private TextField searchField;

    @FXML private TableView<OverstayRecord> table;

    @FXML private TableColumn<OverstayRecord, String> nameCol;
    @FXML private TableColumn<OverstayRecord, String> passportCol;
    @FXML private TableColumn<OverstayRecord, Integer> entryCol;
    @FXML private TableColumn<OverstayRecord, Integer> allowedCol;
    @FXML private TableColumn<OverstayRecord, Integer> currentCol;
    @FXML private TableColumn<OverstayRecord, String> statusCol;

    private ObservableList<OverstayRecord> list = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        passportCol.setCellValueFactory(new PropertyValueFactory<>("passportNo"));
        entryCol.setCellValueFactory(new PropertyValueFactory<>("entryDate"));
        allowedCol.setCellValueFactory(new PropertyValueFactory<>("allowedDays"));
        currentCol.setCellValueFactory(new PropertyValueFactory<>("currentDate"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        table.setItems(list);
    }

    @FXML
    public void loadData() {
        try {
            FileInputStream fis = new FileInputStream("overstay.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);

            ArrayList<OverstayRecord> data =
                    (ArrayList<OverstayRecord>) ois.readObject();

            list.clear();
            list.addAll(data);

            ois.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void search() {
        String pass = searchField.getText();

        for (OverstayRecord r : list) {
            if (r.getPassportNo().equals(pass)) {
                table.getSelectionModel().select(r);
                table.scrollTo(r);
                return;
            }
        }
    }

    @FXML
    public void approve() {
        OverstayRecord r = table.getSelectionModel().getSelectedItem();

        if (r != null && r.getStatus().equals("OK")) {
            r.setStatus("APPROVED");
            table.refresh();
        }
    }

    @FXML
    public void reject() {
        OverstayRecord r = table.getSelectionModel().getSelectedItem();

        if (r != null && r.getStatus().equals("OVERSTAY")) {
            r.setStatus("REJECTED");
            table.refresh();
        }
    }

    @FXML
    public void back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("dashboard2.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}