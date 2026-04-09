package com.example.airportsecurity.Samia;

import com.example.airportsecurity.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.*;

public class SecurityGoal4Controller {

    @FXML private TextField equipNameField;
    @FXML private TextField serialField;
    @FXML private ComboBox<String> conditionCombo;

    @FXML
    public void initialize() {
        conditionCombo.getItems().addAll("Good", "Service Needed", "Broken");
    }

    @FXML
    void submitRequestOnAction(ActionEvent event) {
        String name = equipNameField.getText();
        String serial = serialField.getText();
        String cond = conditionCombo.getValue();

        if (name.isEmpty() || serial.isEmpty() || cond == null) {
            showAlert("Error", "Please fill all fields.");
            return;
        }

        File f = new File("EquipmentStatus.bin");
        try (FileOutputStream fos = new FileOutputStream(f, true);
             ObjectOutputStream oos = f.length() == 0 ? new ObjectOutputStream(fos) : new AppendableObjectOutputStream(fos)) {

            oos.writeObject(new Equipment(name, serial, cond));
            showAlert("Success", "Maintenance request submitted!");
            equipNameField.clear(); serialField.clear();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(content);
        a.showAndWait();
    }

    @FXML
    void goBackOnAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(new FXMLLoader(HelloApplication.class.getResource("Samia/SecurityOfficer.fxml")).load()));
    }
}