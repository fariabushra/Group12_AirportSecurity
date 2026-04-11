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

public class CaseProgressController
{
    @javafx.fxml.FXML
    private TextField idInput;
    @javafx.fxml.FXML
    private ComboBox<String> caseProgressCombo;
    @javafx.fxml.FXML
    private Label caseInfoLabel;
    @javafx.fxml.FXML
    private Label statusLabel;

    @javafx.fxml.FXML
    public void initialize() {
        caseProgressCombo.getItems().addAll("In Progress", "Completed", "Pending");
    }

    @javafx.fxml.FXML
    public void dashboardButton(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Faria/medicalEmergencyStaff.fxml"));
            Scene nextScene = new Scene(fxmlLoader.load());
            Stage nextStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            nextStage.setTitle("Medical Emergency Staff");
            nextStage.setScene(nextScene);
            nextStage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void updateProgressButton(ActionEvent actionEvent) {
        String id = idInput.getText();
        String progress = caseProgressCombo.getValue();

        if (id.isEmpty() || progress == null) {
            statusLabel.setText("Error: ID and Progress required!");
            return;
        }

        CaseProgress newProgress = new CaseProgress(id, progress);
        saveProgress(newProgress);
    }

    private void saveProgress(CaseProgress cp) {
        ArrayList<CaseProgress> progressList = new ArrayList<>();
        File file = new File("caseProgress.bin");

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                while (true) { progressList.add((CaseProgress) ois.readObject()); }
            } catch (EOFException e) { } catch (Exception e) { }
        }

        progressList.add(cp);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (CaseProgress item : progressList) {
                oos.writeObject(item);
            }
            statusLabel.setText("Progress updated successfully!");
        } catch (IOException e) {
            statusLabel.setText("Failed to save progress.");
        }
    }

    @javafx.fxml.FXML
    public void searchCaseButton(ActionEvent actionEvent) {
        String searchID = idInput.getText();
        boolean found = false;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("recordCase.bin"))) {
            while (true) {
                MedicalEmergency mc = (MedicalEmergency) ois.readObject();
                if (mc.getPatientID().equals(searchID)) {
                    caseInfoLabel.setText("Case Found: " + mc.getEmergencyType() + " (" + mc.getSeverity() + ")");
                    found = true;
                    break;
                }
            }
        } catch (EOFException e) {
            if (!found) caseInfoLabel.setText("Case not found!");
        } catch (Exception e) {
            caseInfoLabel.setText("Error reading file.");
        }
    }
}