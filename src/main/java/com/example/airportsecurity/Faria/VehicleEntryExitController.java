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
import java.util.ArrayList;
import java.util.List;

public class VehicleEntryExitController
{
    @javafx.fxml.FXML
    private TextField vehicleNoField;
    @javafx.fxml.FXML
    private ComboBox<String> entryCombo;
    @javafx.fxml.FXML
    private TextField driverIDField;
    @javafx.fxml.FXML
    private TextField driverNameField;
    @javafx.fxml.FXML
    private Label showLabel;
    @javafx.fxml.FXML
    private ComboBox<String> vehicleTypeCombo;
    @javafx.fxml.FXML
    private TableView<Vehicle> vehicleTable;
    @javafx.fxml.FXML
    private TableColumn<Vehicle, String> vehicleNoCol;
    @javafx.fxml.FXML
    private TableColumn<Vehicle, String> driverIDCol;
    @javafx.fxml.FXML
    private TableColumn<Vehicle, String> vehicleTypeCol;
    @javafx.fxml.FXML
    private TableColumn<Vehicle, String> entryExitCol;
    @javafx.fxml.FXML
    private TableColumn<Vehicle, String> driverNameCol;

    private List<Vehicle> vehicleList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {

        vehicleTypeCombo.getItems().addAll("Private", "Official", "Cargo");
        entryCombo.getItems().addAll("Entry", "Exit");

        vehicleNoCol.setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
        vehicleTypeCol.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        driverNameCol.setCellValueFactory(new PropertyValueFactory<>("driverName"));
        driverIDCol.setCellValueFactory(new PropertyValueFactory<>("driverID"));
        entryExitCol.setCellValueFactory(new PropertyValueFactory<>("entryExit"));

        try(ObjectInputStream stream = new ObjectInputStream(
                new FileInputStream("vehicle.bin")
        )) {
            while(true) {
                Vehicle v = (Vehicle) stream.readObject();
                vehicleTable.getItems().add(v);
            }
        } catch (EOFException e) {
            showLabel.setText("Data loaded successfully!");
        } catch (IOException e) {
            showLabel.setText("Could not load data from file!");
        } catch (ClassNotFoundException e) {
            showLabel.setText("Invalid data in the file!");
        }


    }

    @javafx.fxml.FXML
    public void saveButton(ActionEvent actionEvent) {
        try{
            String vehicleNo = vehicleNoField.getText();
            String type = vehicleTypeCombo.getValue();
            String driverName = driverNameField.getText();
            String driverID = driverIDField.getText();
            String entryExit = entryCombo.getValue();


            if (vehicleNoField.getText().isEmpty()){
                showLabel.setText("Enter Vehicle Number");
                return;
            }

            else if (vehicleTypeCombo.getValue()==null){
                showLabel.setText("Select Vehicle Type");
                return;
            }

            else if (driverNameField.getText().isEmpty()){
                showLabel.setText("Enter Passport Number");
                return;
            }

            else if (driverIDField.getText().isEmpty()){
                showLabel.setText("Enter Phone Number");
                return;
            }

            else if (entryCombo.getValue()==null){
                showLabel.setText("Select Vehicle Entry/Exit");
                return;
            }



            Vehicle v = new Vehicle(vehicleNo, type, driverName, driverID, entryExit);
            vehicleList.add(v);
            vehicleTable.getItems().add(v);

            showLabel.setText("Vehicle checked!!!");

        } catch (Exception e){
            showLabel.setText("An unexpected error occurred!");
        }


        try(ObjectOutputStream stream = new ObjectOutputStream(
                new FileOutputStream("vehicle.bin")
        )) {

            for (Vehicle v: vehicleTable.getItems()) {
                stream.writeObject(v);
            }

            showLabel.setText("Vehicle update saved!");

        } catch (IOException e) {
            e.printStackTrace();
            showLabel.setText("Could not save vehicle update!!!");
        }

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
}