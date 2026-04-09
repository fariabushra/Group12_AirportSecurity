package com.example.airportsecurity.Samia;

import com.example.airportsecurity.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.*;

public class SecurityGoal2Controller {

    @FXML private TextField itemNameField;
    @FXML private TextField categoryField;
    @FXML private TextField locationField;

    @FXML
    void saveItemOnAction(ActionEvent event) {
        String name = itemNameField.getText();
        String cat = categoryField.getText();
        String loc = locationField.getText();

        if (name.isEmpty() || cat.isEmpty() || loc.isEmpty()) {
            showAlert("Error", "Please fill all fields.");
            return;
        }

        File f = new File("LostAndFound.bin");
        try {
            FileOutputStream fos = new FileOutputStream(f, true);
            ObjectOutputStream oos;

            if (f.length() == 0) {
                oos = new ObjectOutputStream(fos);
            } else {
                oos = new AppendableObjectOutputStream(fos);
            }

            oos.writeObject(new LostItem(name, cat, loc));
            oos.close();
            fos.close();

            showAlert("Success", "Item registered successfully!");
            itemNameField.clear(); categoryField.clear(); locationField.clear();
        } catch (IOException e) {
            showAlert("File Error", "Could not save to bin file.");
        }
    }

    private void showAlert(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }

    @FXML
    void goBackOnAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(new FXMLLoader(HelloApplication.class.getResource("Samia/SecurityOfficer.fxml")).load()));
    }
}