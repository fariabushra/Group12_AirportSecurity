package com.example.airportsecurity;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static com.example.airportsecurity.SignUpModel.accountHolders;

public class LoginController {
    @javafx.fxml.FXML
    private PasswordField passwordTextField;
    @javafx.fxml.FXML
    private TextField usernameTextField;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void signUpOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("signUp.fxml"));
            Scene nextScene = new Scene(fxmlLoader.load());
            Stage nextStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            nextStage.setScene(nextScene);
            nextStage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void loginOnAction(ActionEvent actionEvent) {
        for (SignUpModel holders : accountHolders) {
            if ((usernameTextField.getText().equals(holders.getName())) && (passwordTextField.getText().equals(holders.getPassword()))) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("NewUserDashboard.fxml"));
                    Scene nextScene = new Scene(fxmlLoader.load());
                    Stage nextStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    nextStage.setScene(nextScene);
                    nextStage.show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if ((usernameTextField.getText().equals("Checkpost Officer")) && (passwordTextField.getText().equals("1234"))) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Faria/CheckpostOfficer.fxml"));
                Scene nextScene = new Scene(fxmlLoader.load());
                Stage nextStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                nextStage.setTitle(" Checkpost Officer Dashboard");
                nextStage.setScene(nextScene);
                nextStage.show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            usernameTextField.setText("Login Successful");
        } else if ((usernameTextField.getText().equals("Medical Emergency Staff")) && (passwordTextField.getText().equals("1234"))) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Faria/MedicalEmergencyStaff.fxml"));
                Scene nextScene = new Scene(fxmlLoader.load());
                Stage nextStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                nextStage.setTitle("Medical Emergency Staff Dashboard");
                nextStage.setScene(nextScene);
                nextStage.show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if ((usernameTextField.getText().equals("Security Officer")) && (passwordTextField.getText().equals("1234"))) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Samia/SecurityOfficer.fxml"));
                Scene nextScene = new Scene(fxmlLoader.load());
                Stage nextStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                nextStage.setTitle("Security Officer Dashboard");
                nextStage.setScene(nextScene);
                nextStage.show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            usernameTextField.setText("Login Successful");
        } else if ((usernameTextField.getText().equals("Shift Supervisor")) && (passwordTextField.getText().equals("1234"))) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Samia/ShiftSupervisor.fxml"));
                Scene nextScene = new Scene(fxmlLoader.load());
                Stage nextStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                nextStage.setTitle("Shift Supervisor Dashboard");
                nextStage.setScene(nextScene);
                nextStage.show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if ((usernameTextField.getText().equals("Control Room Operator")) && (passwordTextField.getText().equals("1234"))) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Tabib/ControlRoomOperatorDashboard.fxml"));
                Scene nextScene = new Scene(fxmlLoader.load());
                Stage nextStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                nextStage.setTitle("Control Room Operator Dashboard");
                nextStage.setScene(nextScene);
                nextStage.show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            usernameTextField.setText("Login Successful");
        } else if ((usernameTextField.getText().equals("IT Administrator")) && (passwordTextField.getText().equals("1234"))) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Tabib/ITAdministrator.fxml"));
                Scene nextScene = new Scene(fxmlLoader.load());
                Stage nextStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                nextStage.setTitle("IT Administrator Dashboard");
                nextStage.setScene(nextScene);
                nextStage.show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if ((usernameTextField.getText().equals("Commanding Officer")) && (passwordTextField.getText().equals("1234"))) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Habib/CommandingOfficer.fxml"));
                Scene nextScene = new Scene(fxmlLoader.load());
                Stage nextStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                nextStage.setTitle("Commanding Officer Dashboard");
                nextStage.setScene(nextScene);
                nextStage.show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            usernameTextField.setText("Login Successful");
        } else if ((usernameTextField.getText().equals("Immigration Officer")) && (passwordTextField.getText().equals("1234"))) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Habib/ImmigrationOfficer.fxml"));
                Scene nextScene = new Scene(fxmlLoader.load());
                Stage nextStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                nextStage.setTitle("Immigration Officer Dashboard");
                nextStage.setScene(nextScene);
                nextStage.show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}