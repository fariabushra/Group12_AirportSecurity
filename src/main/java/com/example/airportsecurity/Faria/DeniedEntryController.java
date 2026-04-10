package com.example.airportsecurity.Faria;

import com.example.airportsecurity.HelloApplication;
import com.example.airportsecurity.Samia.AppendableObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DeniedEntryController
{
    @javafx.fxml.FXML
    private TextField idField;
    @javafx.fxml.FXML
    private Label showText;
    @javafx.fxml.FXML
    private DatePicker datePicker;
    @javafx.fxml.FXML
    private TextArea reasonArea;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void dashboardButton(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Faria/checkpostOfficer.fxml"));
            Scene nextScene = new Scene(fxmlLoader.load());
            Stage nextStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            nextStage.setTitle("CheckpostOfficer Dashboard");
            nextStage.setScene(nextScene);
            nextStage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void submitButton(ActionEvent actionEvent) {
        String id = idField.getText();
        String reason = reasonArea.getText();
        String date = (datePicker.getValue() != null) ? datePicker.getValue().toString() : "";

        if (id.isEmpty() || reason.isEmpty() || date.isEmpty()) {
            showText.setText("Error: Fill all fields!");
            return;
        }

        ArrayList<DeniedEntryModel> entryList = new ArrayList<>();
        File file = new File("deniedEntry.bin");

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                while (true) {
                    entryList.add((DeniedEntryModel) ois.readObject());
                }
            } catch (EOFException e) {

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        entryList.add(new DeniedEntryModel(id, reason, date));

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (DeniedEntryModel entry : entryList) {
                oos.writeObject(entry);
            }
            showText.setText("Denied entry case recorded successfully!");

            idField.clear();
            reasonArea.clear();
        } catch (IOException e) {
            showText.setText("Save failed!");
            e.printStackTrace();
        }
    }
}

