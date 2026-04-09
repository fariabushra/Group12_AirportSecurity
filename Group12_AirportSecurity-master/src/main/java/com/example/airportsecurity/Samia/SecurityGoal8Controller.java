package com.example.airportsecurity.Samia;

import com.example.airportsecurity.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.*;

public class SecurityGoal8Controller {

    @FXML private TextField nameField;
    @FXML private ComboBox<String> shiftCombo;
    @FXML private TextArea notesArea;

    @FXML
    public void initialize() {
        shiftCombo.getItems().addAll("Morning (06:00-14:00)", "Evening (14:00-22:00)", "Night (22:00-06:00)");
    }

    @FXML
    void submitHandoverOnAction(ActionEvent event) {
        String name = nameField.getText();
        String shift = shiftCombo.getValue();
        String notes = notesArea.getText();

        if (name.isEmpty() || shift == null || notes.isEmpty()) {
            showAlert("Missing Info", "Please complete the handover report.");
            return;
        }

        File f = new File("HandoverReports.bin");
        try (FileOutputStream fos = new FileOutputStream(f, true);
             ObjectOutputStream oos = f.length() == 0 ? new ObjectOutputStream(fos) : new AppendableObjectOutputStream(fos)) {

            oos.writeObject(new Handover(name, shift, notes));
            showAlert("Success", "Handover submitted. You may now log out.");
            nameField.clear(); notesArea.clear();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setContentText(content);
        a.showAndWait();
    }

    @FXML
    void goBackOnAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(new FXMLLoader(HelloApplication.class.getResource("Samia/SecurityOfficer.fxml")).load()));
    }
}