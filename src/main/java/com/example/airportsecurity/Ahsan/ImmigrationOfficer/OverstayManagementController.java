package com.example.airportsecurity.Ahsan.ImmigrationOfficer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.*;
import java.util.ArrayList;

public class OverstayManagementController
{
    @FXML private TextField nameField;
    @FXML private TextField passportField;
    @FXML private TextField entryField;
    @FXML private TextField allowedField;
    @FXML private TextField currentField;

    @FXML private TextArea outputArea;

    private ObservableList<OverstayRecord> list = FXCollections.observableArrayList();

    @FXML
    public void add() {

        OverstayRecord r = new OverstayRecord(
                nameField.getText(),
                passportField.getText(),
                Integer.parseInt(entryField.getText()),
                Integer.parseInt(allowedField.getText()),
                Integer.parseInt(currentField.getText())
        );

        list.add(r);
        outputArea.setText("Record Added");
    }

    @FXML
    public void detect() {

        OverstayRecord r = list.get(list.size() - 1);

        int stay = r.getCurrentDate() - r.getEntryDate();

        if (stay > r.getAllowedDays()) {
            r.setStatus("OVERSTAY");
            outputArea.setText("OVERSTAY DETECTED");
        } else {
            r.setStatus("OK");
            outputArea.setText("NO OVERSTAY");
        }
    }

    @FXML
    public void save() {
        try {
            FileOutputStream fos = new FileOutputStream("overstay.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(new ArrayList<>(list));

            oos.close();
            fos.close();

            outputArea.setText("Saved to BIN");

        } catch (Exception e) {
            outputArea.setText("Save Error");
        }
    }

    @FXML
    public void load() {
        try {
            FileInputStream fis = new FileInputStream("overstay.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);

            ArrayList<OverstayRecord> data =
                    (ArrayList<OverstayRecord>) ois.readObject();

            list.clear();
            list.addAll(data);

            ois.close();
            fis.close();

            outputArea.setText("Loaded from BIN");

        } catch (Exception e) {
            outputArea.setText("Load Error");
        }
    }

    @FXML
    public void back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("dashboard2.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            outputArea.setText("Error loading dashboard2.fxml");
        }
    }
}