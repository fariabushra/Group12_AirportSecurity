package com.example.airportsecurity.Faria;

import com.example.airportsecurity.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;


public class ViewAssignedLocationController
{
    @javafx.fxml.FXML
    private TableColumn<EmergencyLocations, String> locationCol;
    @javafx.fxml.FXML
    private Label showTextLabel;
    @javafx.fxml.FXML
    private TableColumn<EmergencyLocations, String> dateCol;
    @javafx.fxml.FXML
    private TextField staffIDField;
    @javafx.fxml.FXML
    private TableColumn<EmergencyLocations, String> staffIDCol;
    @javafx.fxml.FXML
    private TableColumn<EmergencyLocations, String> timeCol;
    @javafx.fxml.FXML
    private TableView<EmergencyLocations> locationTable;

    @javafx.fxml.FXML
    public void initialize() {
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        staffIDCol.setCellValueFactory(new PropertyValueFactory<>("staffID"));

        File file = new File("locations.bin");
        if (!file.exists()) {
            createDummyFile();
        }
    }

    private void createDummyFile() {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("locations.bin"))){
            oos.writeObject(new EmergencyLocations("01-05-2026", "10AM-2PM", "Ground Floor", "Staff-111"));
            oos.writeObject(new EmergencyLocations("01-05-2026", "2PM-6PM", "Ground Floor", "Staff-112"));
            oos.writeObject(new EmergencyLocations("01-05-2026", "6PM-10PM", "VIP Cabin Area", "Staff-111"));
            oos.writeObject(new EmergencyLocations("03-05-2026", "10AM-2PM", "Main Building", "Staff-113"));
            oos.writeObject(new EmergencyLocations("02-05-2026", "4PM-8PM", "Additional Wing", "Staff-116"));
            oos.writeObject(new EmergencyLocations("05-05-2026", "8AM-12PM", "Top Floor", "Staff-114"));
            oos.writeObject(new EmergencyLocations("03-05-2026", "6PM-10PM", "Building-03", "Staff-111"));

            System.out.println("Assigned Locations file created successfully");
        } catch (IOException e){
            e.printStackTrace();
        }
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
    public void viewLocationButton(ActionEvent actionEvent) {
        String searchID = staffIDField.getText();
        locationTable.getItems().clear();
        boolean found = false;

        if (searchID.isEmpty()) {
            showTextLabel.setText("Please enter your Staff ID");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("locations.bin"))) {
            while (true) {
                EmergencyLocations e = (EmergencyLocations) ois.readObject();
                if (e.getStaffID().equalsIgnoreCase(searchID)) {
                    locationTable.getItems().add(e);

                    found = true;
                }
            }
        } catch (EOFException e) {
            // End of file reached
            if (!found) showTextLabel.setText("No list found for this ID.");
            else showTextLabel.setText("Assigned location list loaded!");
        } catch (Exception e) {
            showTextLabel.setText("Error reading data.");
        }
    }
}