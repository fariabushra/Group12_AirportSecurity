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

public class AddPersonController
{
    @javafx.fxml.FXML
    private TableColumn<Person, String> addressCol;
    @javafx.fxml.FXML
    private TableView<Person> personTable;
    @javafx.fxml.FXML
    private TableColumn<Person, String> phoneNoCol;
    @javafx.fxml.FXML
    private TextField nameField;
    @javafx.fxml.FXML
    private TableColumn<Person, String> passportCol;
    @javafx.fxml.FXML
    private TextField emailField;
    @javafx.fxml.FXML
    private TextField nidField;
    @javafx.fxml.FXML
    private TableColumn<Person, String> nameCol;
    @javafx.fxml.FXML
    private TextField phoneNoField;
    @javafx.fxml.FXML
    private TableColumn<Person, String> emailCol;
    @javafx.fxml.FXML
    private TextField passportField;
    @javafx.fxml.FXML
    private TableColumn<Person, String> nidCol;
    @javafx.fxml.FXML
    private TableColumn<Person, String> nationalityCol;
    @javafx.fxml.FXML
    private TextField addressField;
    @javafx.fxml.FXML
    private TextField nationalityField;
    @javafx.fxml.FXML
    private Label textLabel;
    @javafx.fxml.FXML
    private TableColumn<Person, String> typeCol;
    @javafx.fxml.FXML
    private ComboBox<String> typeCombo;

    private List<Person> personList = new ArrayList<>();


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

        try(ObjectInputStream stream = new ObjectInputStream(
                new FileInputStream("person.bin")
        )) {
            while(true) {
                Person u = (Person) stream.readObject();
                personTable.getItems().add(u);
            }
        } catch (EOFException e) {
            textLabel.setText("Data loaded successfully!");
        } catch (IOException e) {
            textLabel.setText("Could not load data from file!");
        } catch (ClassNotFoundException e) {
            textLabel.setText("Invalid data in the file!");
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

    @javafx.fxml.FXML
    public void addDataButton(ActionEvent actionEvent) {
        try{
            String name = nameField.getText();
            String NID = nidField.getText();
            String passport = passportField.getText();
            String phoneNo = phoneNoField.getText();
            String email = emailField.getText();
            String nationality = nationalityField.getText();
            String address = addressField.getText();
            String type = typeCombo.getValue();

            if (nameField.getText().isEmpty()){
                textLabel.setText("Enter Name");
                return;
            }

            else if (nidField.getText().isEmpty()){
                textLabel.setText("Enter NID Number");
                return;
            }

            else if (passportField.getText().isEmpty()){
                textLabel.setText("Enter Passport Number");
                return;
            }

            else if (phoneNoField.getText().isEmpty()){
                textLabel.setText("Enter Phone Number");
                return;
            }

            else if (emailField.getText().isEmpty()){
                textLabel.setText("Enter Email");

                return;
            }

            else if (addressField.getText().isEmpty()){
                textLabel.setText("Enter Address");
                return;
            }

            else if (nationalityField.getText().isEmpty()) {
                textLabel.setText("Enter Nationality");
                return;
            }

            else if (typeCombo.getValue()==null) {
                textLabel.setText("Enter Type");
                return;
            }

            Person p = new Person(name, phoneNo, NID, passport, email, nationality, address, type);
            personList.add(p);
            personTable.getItems().add(p);

            textLabel.setText("New person added to the list!");

        } catch (Exception e){
            textLabel.setText("An unexpected error occurred!");
        }
    }

    @javafx.fxml.FXML
    public void saveButton(ActionEvent actionEvent) {
        try(ObjectOutputStream stream = new ObjectOutputStream(
                new FileOutputStream("person.bin")
        )) {

            for (Person p: personTable.getItems()) {
                stream.writeObject(p);
            }

            textLabel.setText("Person data saved to bin file!");

            personTable.getItems().clear();

        } catch (IOException e) {
            e.printStackTrace();
            textLabel.setText("Could not save data to bin file!");
        }

    }
}