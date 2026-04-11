package com.example.airportsecurity.Faria;

import com.example.airportsecurity.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ViewCasesController
{
    @javafx.fxml.FXML
    private TableColumn<MedicalEmergency, String> typeCol;
    @javafx.fxml.FXML
    private TextField idField;
    @javafx.fxml.FXML
    private TableView<MedicalEmergency> caseTable;
    @javafx.fxml.FXML
    private TableColumn<MedicalEmergency, String> idCol;
    @javafx.fxml.FXML
    private TableColumn<MedicalEmergency, String> severityCol;
    @javafx.fxml.FXML
    private TableColumn<MedicalEmergency, String> nameCol;
    @javafx.fxml.FXML
    private TableColumn<MedicalEmergency, String> observeCol;
    @javafx.fxml.FXML
    private Label showTextLabel;

    @javafx.fxml.FXML
    public void initialize() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("emergencyType"));
        severityCol.setCellValueFactory(new PropertyValueFactory<>("severity"));
        observeCol.setCellValueFactory(new PropertyValueFactory<>("initialObservation"));
    }

    @javafx.fxml.FXML
    public void dashboardButton(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Faria/medicalEmergencyStaff.fxml"));
            Scene nextScene = new Scene(fxmlLoader.load());
            Stage nextStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            nextStage.setTitle("Medical Emergency Staff Dashboard");
            nextStage.setScene(nextScene);
            nextStage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void viewCaseButton(ActionEvent actionEvent) {
        String inputID = idField.getText();

        if (inputID.isEmpty()) {
            showTextLabel.setText("Please enter patient ID!");
            return;
        }

        caseTable.getItems().clear();
        boolean found = false;


        try (ObjectInputStream stream = new ObjectInputStream(
                new FileInputStream("recordCase.bin")
        )) {
            while (true) {
                MedicalEmergency m = (MedicalEmergency) stream.readObject();

                if (m.getPatientID().equalsIgnoreCase(inputID)) {

                    caseTable.getItems().add(m);
                    showTextLabel.setText("Case Found!");
                    found = true;
                    break;
                }
            }
        } catch (EOFException e) {
            if (!found) {
                showTextLabel.setText("No case record found in the system.");
            }
        } catch (IOException | ClassNotFoundException e) {
            showTextLabel.setText("Error reading the case database.");
            e.printStackTrace();
        }

    }
}