package com.example.airportsecurity.Faria;

import com.example.airportsecurity.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class MessageSupervisorController {
    @javafx.fxml.FXML
    private TextField subjectField;
    @javafx.fxml.FXML
    private TextArea detailsArea;
    @javafx.fxml.FXML
    private Label statusLabel;

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
    public void sendMessageButton(ActionEvent actionEvent) {
        String subject = subjectField.getText();
        String details = detailsArea.getText();


        if (subject.isEmpty() || details.isEmpty()) {
            statusLabel.setText("Enter both Subject and Details!");
            return;
        }


        EmergencyMessage newMessage = new EmergencyMessage(subject, details);

        saveMessage(newMessage);
    }

    private void saveMessage(EmergencyMessage msg) {
        ArrayList<EmergencyMessage> messageList = new ArrayList<>();
        File file = new File("sentMessages.bin");

        // Read existing messages if file exists
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                while (true) {
                    messageList.add((EmergencyMessage) ois.readObject());
                }
            } catch (EOFException e) {

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        messageList.add(msg);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (EmergencyMessage m : messageList) {
                oos.writeObject(m);
            }

            statusLabel.setText("Message sent to Supervisor successfully!");

            subjectField.clear();
            detailsArea.clear();

        } catch (IOException e) {
            statusLabel.setText("System Error: Could not send message.");
            e.printStackTrace();
        }
    }
}