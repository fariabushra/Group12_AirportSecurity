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

public class CaseCloseController {
    @javafx.fxml.FXML
    private TextField idInput;
    @javafx.fxml.FXML
    private Label caseDetailsLabel;
    @javafx.fxml.FXML
    private ComboBox<String> finalStatusCombo;
    @javafx.fxml.FXML
    private Label confirmationLabel;

    @javafx.fxml.FXML
    public void initialize() {
        finalStatusCombo.getItems().addAll("Resolved", "Transferred");
    }

    @javafx.fxml.FXML
    public void dashboardButton(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Faria/medicalEmergencyStaff.fxml"));
            Scene nextScene = new Scene(fxmlLoader.load());
            Stage nextStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            nextStage.setTitle("Medical Emergency Staff  Dashboard");
            nextStage.setScene(nextScene);
            nextStage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void searchForCase(ActionEvent actionEvent) {
        String searchID = idInput.getText();
        boolean found = false;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("recordCase.bin"))) {
            while (true) {
                MedicalEmergency mc = (MedicalEmergency) ois.readObject();
                if (mc.getPatientID().equals(searchID)) {
                    caseDetailsLabel.setText("Found: " + mc.getEmergencyType() + " | Registered: " + mc.getSeverity());
                    found = true;
                    break;
                }
            }
        } catch (EOFException e) {
            if (!found) caseDetailsLabel.setText("Patient ID not found!");
        } catch (Exception e) {
            caseDetailsLabel.setText("Error accessing records.");
        }
    }


    @javafx.fxml.FXML
    public void closeCaseButton(ActionEvent actionEvent) {
        String id = idInput.getText();
        String status = finalStatusCombo.getValue();

        if (id.isEmpty() || status == null) {
            confirmationLabel.setText("Error: ID and Final Status are required!");
            return;
        }

        ClosedCase closingRecord = new ClosedCase(id, status);

        saveClosedCase(closingRecord);
    }

    private void saveClosedCase(ClosedCase cc) {
        ArrayList<ClosedCase> closedList = new ArrayList<>();
        File file = new File("closedCases.bin");

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                while (true) {
                    closedList.add((ClosedCase) ois.readObject());
                }
            } catch (EOFException e) {
            } catch (Exception e) {
            }
        }

        closedList.add(cc);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (ClosedCase c : closedList) {
                oos.writeObject(c);
            }
            confirmationLabel.setText("Case has been closed.");

            idInput.clear();
            finalStatusCombo.setValue(null);
            caseDetailsLabel.setText("Enter ID to search...");

        } catch (IOException e) {
            confirmationLabel.setText("System Error: Could not close case.");
        }
    }
}