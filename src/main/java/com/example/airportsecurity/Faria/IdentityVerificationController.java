package com.example.airportsecurity.Faria;

import com.example.airportsecurity.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class IdentityVerificationController
{
    @javafx.fxml.FXML
    private TableColumn<Person, String> typeCol;
    @javafx.fxml.FXML
    private TableColumn<Person, String> addressCol;
    @javafx.fxml.FXML
    private TableView<Person> personTable;
    @javafx.fxml.FXML
    private TableColumn<Person, String> phoneNoCol;
    @javafx.fxml.FXML
    private TableColumn<Person, String> passportCol;
    @javafx.fxml.FXML
    private Label loadLabel;
    @javafx.fxml.FXML
    private Label identifyLabel;
    @javafx.fxml.FXML
    private TableColumn<Person, String> nameCol;
    @javafx.fxml.FXML
    private TableColumn<Person, String> emailCol;
    @javafx.fxml.FXML
    private TextField passportField;
    @javafx.fxml.FXML
    private ComboBox<String> typeCombo;
    @javafx.fxml.FXML
    private TableColumn<Person, String> nidCol;
    @javafx.fxml.FXML
    private TableColumn<Person, String> nationalityCol;

    @javafx.fxml.FXML
    public void initialize() {
        typeCombo.getItems().addAll("Staff", "Visitor", "Contractor");

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nidCol.setCellValueFactory(new PropertyValueFactory<>("nidNo"));
        passportCol.setCellValueFactory(new PropertyValueFactory<>("passportNo"));
        phoneNoCol.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        nationalityCol.setCellValueFactory(new PropertyValueFactory<>("nationality"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
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
    public void allowEntryButton(ActionEvent actionEvent) {
        identifyLabel.setText("Entry Allowed!");
    }

    @javafx.fxml.FXML
    public void denyEntryButton(ActionEvent actionEvent) {
        identifyLabel.setText("Entry Denied!!! ");
    }

    @javafx.fxml.FXML
    public void loadButton(ActionEvent actionEvent) {
        String inputPassport = passportField.getText();
        String inputType = typeCombo.getValue();

        // Validation
        if (inputPassport.isEmpty() || inputType == null) {
            loadLabel.setText("Please provide both Passport and Type!");
            return;
        }

        personTable.getItems().clear(); // Clear previous search results
        boolean found = false;

        // 3. READ AND FILTER LOGIC
        // We open the file and read objects one by one to find a match
        try (ObjectInputStream stream = new ObjectInputStream(
                new FileInputStream("person.bin")
        )) {
            while (true) {
                Person p = (Person) stream.readObject();

                // Check if this person matches the search criteria
                if (p.getPassportNo().equalsIgnoreCase(inputPassport) &&
                        p.getType().equalsIgnoreCase(inputType)) {

                    personTable.getItems().add(p);
                    loadLabel.setText("Match Found! Identity Verified.");
                    found = true;
                    break; // Stop reading once we find the person
                }
            }
        } catch (EOFException e) {
            // This is reached if the loop finishes without finding a match (End of File)
            if (!found) {
                loadLabel.setText("No matching record found in the system.");
            }
        } catch (IOException | ClassNotFoundException e) {
            loadLabel.setText("Error reading the security database.");
            e.printStackTrace();
        }
    }
}

