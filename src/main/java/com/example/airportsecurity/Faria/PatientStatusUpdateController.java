package com.example.airportsecurity.Faria;

import com.example.airportsecurity.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class PatientStatusUpdateController
{
    @javafx.fxml.FXML
    private Label displayStatusLabel;
    @javafx.fxml.FXML
    private Label systemMessageLabel;
    @javafx.fxml.FXML
    private TextField idSearchField;
    @javafx.fxml.FXML
    private CheckBox firstAidCheck;
    @javafx.fxml.FXML
    private ComboBox<String> statusCombo;

    @javafx.fxml.FXML
    public void initialize() {
        statusCombo.getItems().addAll("Stable", "Critical", "Unconscious");
        displayStatusLabel.setText("Current Status: Pending Search...");
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
    public void searchButton(ActionEvent actionEvent) {
        String searchID = idSearchField.getText();
        boolean found = false;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("recordCase.bin"))) {
            while (true) {
                MedicalEmergency mc = (MedicalEmergency) ois.readObject();
                if (mc.getPatientID().equals(searchID)) {
                    systemMessageLabel.setText("Patient: " + mc.getPatientID() + " | Type: " + mc.getEmergencyType());
                    found = true;
                    break;
                }
            }
        } catch (EOFException e) {
            if (!found) {
                systemMessageLabel.setText("Patient not found!");
                displayStatusLabel.setText("Current Status: N/A");
                return;
            }
        } catch (Exception e) { e.printStackTrace(); }

        loadLastKnownStatus(searchID);
    }


    @javafx.fxml.FXML
    public void updateButton(ActionEvent actionEvent) {
        String id = idSearchField.getText();
        String newStatus = statusCombo.getValue();
        String firstAid = firstAidCheck.isSelected() ? "Yes" : "No";

        if (id.isEmpty() || newStatus == null) {
            systemMessageLabel.setText("Error: Select ID and Status!");
            return;
        }

        displayStatusLabel.setText("Current Status: " + newStatus);

        Patient update = new Patient(id, firstAid, newStatus);
        saveToFile(update);

        systemMessageLabel.setText("Status updated successfully!");
    }
    private void saveToFile(Patient update) {
        ArrayList<Patient> list = new ArrayList<>();
        File file = new File("patientHistory.bin");

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                while (true) { list.add((Patient) ois.readObject()); }
            } catch (EOFException e) {

            } catch (Exception e) {

            }
        }
        list.add(update);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (Patient psu : list) oos.writeObject(psu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadLastKnownStatus(String id) {
        String lastStatus = "Initial (No updates yet)";
        File file = new File("patientHistory.bin");

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                while (true) {
                    Patient psu = (Patient) ois.readObject();
                    if (psu.getPatientID().equals(id)) {
                        lastStatus = psu.getUpdatedStatus();
                    }
                }
            } catch (EOFException e) {

            } catch (Exception e) {

            }
        }
        displayStatusLabel.setText("Current Status: " + lastStatus);
    }
}