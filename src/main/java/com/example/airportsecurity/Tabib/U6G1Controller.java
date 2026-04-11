package com.example.airportsecurity.Tabib;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class U6G1Controller
{
    @javafx.fxml.FXML
    private TableView<SystemUser> userTableView;
    @javafx.fxml.FXML
    private TableColumn<SystemUser, String> accountStatusColumn;
    @javafx.fxml.FXML
    private TableColumn<SystemUser, String> nameColumn;
    @javafx.fxml.FXML
    private TableColumn<SystemUser, String> roleColumn;
    @javafx.fxml.FXML
    private TableColumn<SystemUser, String> employeeIdColumn;
    @javafx.fxml.FXML
    private TextField nameTextField;
    @javafx.fxml.FXML
    private PasswordField passwordField;
    @javafx.fxml.FXML
    private TableColumn<SystemUser, String> serialNoColumn;
    @javafx.fxml.FXML
    private ChoiceBox<String> roleChoiceBox;
    @javafx.fxml.FXML
    private Label statusLabel;
    @javafx.fxml.FXML
    private TextField employeeIdTextField;

    private ObservableList<SystemUser> userList;

    @javafx.fxml.FXML
    public void initialize() {

        serialNoColumn.setCellValueFactory(new PropertyValueFactory<>("serialNo"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        accountStatusColumn.setCellValueFactory(new PropertyValueFactory<>("accountStatus"));

        roleChoiceBox.setItems(FXCollections.observableArrayList(
                "IT Administrator",
                "Control Room Operator",
                "Security Supervisor",
                "System Monitor"
        ));

        userList = FXCollections.observableArrayList(
                new SystemUser("1", "Ahsan Rahman", "EMP101", "IT Administrator", "Active"),
                new SystemUser("2", "Nusrat Jahan", "EMP102", "Control Room Operator", "Active"),
                new SystemUser("3", "Mehedi Hasan", "EMP103", "Security Supervisor", "Inactive")
        );

        userTableView.setItems(userList);

        statusLabel.setVisible(true);
        statusLabel.setText("Fill in the form and click Create User.");
        statusLabel.setStyle("-fx-text-fill: green;");
    }

    @javafx.fxml.FXML
    public void clearButton(ActionEvent actionEvent) {
        nameTextField.clear();
        employeeIdTextField.clear();
        passwordField.clear();
        roleChoiceBox.setValue(null);

        statusLabel.setVisible(true);
        statusLabel.setText("Form cleared.");
        statusLabel.setStyle("-fx-text-fill: blue;");
    }

    @javafx.fxml.FXML
    public void backButton(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/airportsecurity/Tabib/ITAdministratorDashboard.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void createUserButton(ActionEvent actionEvent) {
        String name = nameTextField.getText();
        String employeeId = employeeIdTextField.getText();
        String role = roleChoiceBox.getValue();
        String password = passwordField.getText();

        if (name.isEmpty() || employeeId.isEmpty() || role == null || password.isEmpty()) {
            statusLabel.setVisible(true);
            statusLabel.setText("Please fill in all fields.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if (!employeeId.matches("EMP\\d{3}")) {
            statusLabel.setVisible(true);
            statusLabel.setText("Employee ID must be like EMP101.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if (password.length() < 6) {
            statusLabel.setVisible(true);
            statusLabel.setText("Password must be at least 6 characters.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        String serialNo = String.valueOf(userList.size() + 1);

        SystemUser newUser = new SystemUser(
                serialNo,
                name,
                employeeId,
                role,
                "Active"
        );

        userList.add(newUser);

        statusLabel.setVisible(true);
        statusLabel.setText("User created successfully.");
        statusLabel.setStyle("-fx-text-fill: green;");

        nameTextField.clear();
        employeeIdTextField.clear();
        passwordField.clear();
        roleChoiceBox.setValue(null);
    }
}