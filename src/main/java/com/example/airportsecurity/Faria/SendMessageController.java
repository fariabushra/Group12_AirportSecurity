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

public class SendMessageController
{
    @javafx.fxml.FXML
    private ComboBox<String> receiverCombo;
    @javafx.fxml.FXML
    private Label feedbackLabel;
    @javafx.fxml.FXML
    private TextField subjectField;
    @javafx.fxml.FXML
    private TextArea detailsArea;

    @javafx.fxml.FXML
    public void initialize() {
        receiverCombo.getItems().addAll("Security", "Control Room");
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
    public void sendMessageButton(ActionEvent actionEvent) {
        String receiver = receiverCombo.getValue();
        String subject = subjectField.getText();
        String details = detailsArea.getText();

        if (receiver == null || subject.isEmpty() || details.isEmpty()) {
            feedbackLabel.setText("Error: All fields are required!");
            return;
        }

        MedicalMessage msg = new MedicalMessage(receiver, subject, details);
        saveMessage(msg);
    }

    private void saveMessage(MedicalMessage msg) {
        ArrayList<MedicalMessage> messageList = new ArrayList<>();
        File file = new File("communication.bin");

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                while (true) { messageList.add((MedicalMessage) ois.readObject()); }
            } catch (EOFException e) { } catch (Exception e) { e.printStackTrace(); }
        }

        messageList.add(msg);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (MedicalMessage m : messageList) {
                oos.writeObject(m);
            }

            feedbackLabel.setText("Message sent to " + msg.getReceiver() + "!");

            subjectField.clear();
            detailsArea.clear();
            receiverCombo.setValue(null);

        } catch (IOException e) {
            feedbackLabel.setText("Failed to send message.");
        }
    }
}