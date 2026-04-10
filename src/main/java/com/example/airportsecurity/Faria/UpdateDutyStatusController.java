package com.example.airportsecurity.Faria;

import com.example.airportsecurity.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

public class UpdateDutyStatusController {
    @javafx.fxml.FXML
    private Label currentStatusLabel;
    @javafx.fxml.FXML
    private ComboBox<String> statusComboBox;

    @javafx.fxml.FXML
    public void initialize() {
        statusComboBox.getItems().addAll("On Duty", "Off Duty", "Break");

        loadStatusFromDisk();
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
    public void updateStatusButton(ActionEvent actionEvent) {
        String selected = statusComboBox.getValue();

        if (selected != null) {
            currentStatusLabel.setText("Current Status: " + selected);

            saveStatusToDisk(selected);

            System.out.println("Status changed to " + selected);
        }
    }

    private void saveStatusToDisk(String status) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("duty_status.txt"))) {
            writer.print(status); // Overwrites the old status with the new one
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadStatusFromDisk() {
        File file = new File("duty_status.txt");
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                if (scanner.hasNextLine()) {
                    String savedStatus = scanner.nextLine();
                    // Set the label to what we found in the file
                    currentStatusLabel.setText("Current Status: " + savedStatus);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            currentStatusLabel.setText("Current Status: Off Duty");
        }
    }

}
