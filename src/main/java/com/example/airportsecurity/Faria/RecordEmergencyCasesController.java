package com.example.airportsecurity.Faria;

import com.example.airportsecurity.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RecordEmergencyCasesController
{
    @javafx.fxml.FXML
    private TableColumn<MedicalEmergency, String> typeCol;
    @javafx.fxml.FXML
    private TextField idField;
    @javafx.fxml.FXML
    private TextField observeField;
    @javafx.fxml.FXML
    private TableColumn<MedicalEmergency, String> severityCol;
    @javafx.fxml.FXML
    private Label validationLabel;
    @javafx.fxml.FXML
    private TextField nameField;
    @javafx.fxml.FXML
    private ComboBox<String> severityCombo;
    @javafx.fxml.FXML
    private TableColumn<MedicalEmergency, String> idCol;
    @javafx.fxml.FXML
    private Label showTextlabel;
    @javafx.fxml.FXML
    private TableColumn<MedicalEmergency, String> nameCol;
    @javafx.fxml.FXML
    private TableColumn<MedicalEmergency, String> observeCol;
    @javafx.fxml.FXML
    private ComboBox<String> typeCombo;
    @javafx.fxml.FXML
    private TableView<MedicalEmergency> caseTable;

    private List<MedicalEmergency> caseList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        typeCombo.getItems().addAll("Injury", "Accident", "Heart Attack", "Fainting", "Serious Health Hazard");
        severityCombo.getItems().addAll("High", "Medium", "Low");

        nameCol.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("emergencyType"));
        severityCol.setCellValueFactory(new PropertyValueFactory<>("severity"));
        observeCol.setCellValueFactory(new PropertyValueFactory<>("initialObservation"));

        try(ObjectInputStream stream = new ObjectInputStream(
                new FileInputStream("recordCase.bin")
        )) {
            while(true) {
                MedicalEmergency m = (MedicalEmergency) stream.readObject();
                caseTable.getItems().add(m);
            }
        } catch (EOFException e) {
            validationLabel.setText("Data loaded successfully!");
        } catch (IOException e) {
            validationLabel.setText("Could not load data from file!");
        } catch (ClassNotFoundException e) {
            validationLabel.setText("Invalid data in the file!");
        }
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
    public void recordCaseButton(ActionEvent actionEvent) {
        try(ObjectOutputStream stream = new ObjectOutputStream(
                new FileOutputStream("recordCase.bin")
        )) {

            for (MedicalEmergency m: caseTable.getItems()) {
                stream.writeObject(m);
            }

            validationLabel.setText("Emergency case recorded!");

            caseTable.getItems().clear();

        } catch (IOException e) {
            e.printStackTrace();
            validationLabel.setText("Could not record the case!");
        }
    }

    @javafx.fxml.FXML
    public void addCaseButton(ActionEvent actionEvent) {
        try{
            String name = nameField.getText();
            String ID = idField.getText();
            String type = typeCombo.getValue();
            String s = severityCombo.getValue();
            String o = observeField.getText();


            if (nameField.getText().isEmpty()){
                validationLabel.setText("Enter Patient Name");
                return;
            }

            else if (idField.getText().isEmpty()){
                validationLabel.setText("Enter Patient ID");
                return;
            }

            else if (typeCombo.getValue()==null) {
                validationLabel.setText("Enter Type");
                return;
            }


            else if (severityCombo.getValue()==null){
                validationLabel.setText("Enter Phone Number");
                return;
            }

            else if (observeField.getText().isEmpty()){
                validationLabel.setText("Enter Email");
                return;
            }



            MedicalEmergency m = new MedicalEmergency(name, ID, type, s, o);
            caseList.add(m);
            caseTable.getItems().add(m);

            validationLabel.setText("New person added to the list!");

        } catch (Exception e){
            validationLabel.setText("An unexpected error occurred!");
        }
    }
}