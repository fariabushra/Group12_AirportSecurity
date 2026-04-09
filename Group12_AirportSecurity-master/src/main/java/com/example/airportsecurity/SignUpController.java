package com.example.airportsecurity;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static com.example.airportsecurity.SignUpModel.accountHolders;

public class SignUpController
{
    @javafx.fxml.FXML
    private Label showTextField;
    @javafx.fxml.FXML
    private TextField phoneNoField;
    @javafx.fxml.FXML
    private TextField nameField;
    @javafx.fxml.FXML
    private TextField emailField;
    @javafx.fxml.FXML
    private PasswordField passwordField;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backButton(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
            Scene nextScene = new Scene(fxmlLoader.load());
            Stage nextStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            nextStage.setScene(nextScene);
            nextStage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void createAccButton(ActionEvent actionEvent) {
        if((nameField.getText().isEmpty()) || emailField.getText().isEmpty() || passwordField.getText().isEmpty() || phoneNoField.getText().isEmpty()){
            showTextField.setText("Please fill all inputs");
            return;
        }

        SignUpModel accountHolder = new SignUpModel(
                nameField.getText(),
                passwordField.getText(),
                emailField.getText(),
                phoneNoField.getText()

        );
        accountHolders.add(accountHolder);
        showTextField.setText("Account Created");

    }
}