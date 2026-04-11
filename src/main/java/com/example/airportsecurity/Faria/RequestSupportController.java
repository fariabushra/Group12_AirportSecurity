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

public class RequestSupportController
{
    @javafx.fxml.FXML
    private TextField locationField;
    @javafx.fxml.FXML
    private TextArea reasonArea;
    @javafx.fxml.FXML
    private ComboBox<String> supportTypeCombo;
    @javafx.fxml.FXML
    private Label statusLabel;

    @javafx.fxml.FXML
    public void initialize() {
        supportTypeCombo.getItems().addAll("Ambulance", "Hospital Support");
    }

    @javafx.fxml.FXML
    public void sendRequestButton(ActionEvent actionEvent) {
        String type = supportTypeCombo.getValue();
        String loc = locationField.getText();
        String reason = reasonArea.getText();

        if (type == null || loc.isEmpty() || reason.isEmpty()) {
            statusLabel.setText("Error: Please fill in all fields!");
            return;
        }

        SupportRequest newRequest = new SupportRequest(type, loc, reason);

        saveRequestToFile(newRequest);
    }

    private void saveRequestToFile(SupportRequest req) {
        ArrayList<SupportRequest> requestList = new ArrayList<>();
        File file = new File("supportRequests.bin");

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                while (true) {
                    requestList.add((SupportRequest) ois.readObject());
                }
            } catch (EOFException e) {
                // End of file reached
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        requestList.add(req);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (SupportRequest s : requestList) {
                oos.writeObject(s);
            }

            statusLabel.setText("Request sent successfully!");

            locationField.clear();
            reasonArea.clear();
            supportTypeCombo.setValue(null);

        } catch (IOException e) {
            statusLabel.setText("System Error: Could not send request.");
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
}