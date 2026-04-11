package com.example.airportsecurity.Tabib;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class U6G2Controller
{
    @javafx.fxml.FXML
    private TableColumn<RolePermission, String> roleNameColumn;
    @javafx.fxml.FXML
    private CheckBox createIncidentCheckBox;
    @javafx.fxml.FXML
    private CheckBox exportReportCheckBox;
    @javafx.fxml.FXML
    private TableColumn<RolePermission, String> serialNoColumn;
    @javafx.fxml.FXML
    private CheckBox manageUsersCheckBox;
    @javafx.fxml.FXML
    private TableView<RolePermission> roleTableView;
    @javafx.fxml.FXML
    private CheckBox systemBackupCheckBox;
    @javafx.fxml.FXML
    private CheckBox viewCCTVCheckBox;
    @javafx.fxml.FXML
    private Label statusLabel;

    private ObservableList<RolePermission> roleList;

    @javafx.fxml.FXML
    public void initialize() {

        serialNoColumn.setCellValueFactory(new PropertyValueFactory<>("serialNo"));
        roleNameColumn.setCellValueFactory(new PropertyValueFactory<>("roleName"));

        roleList = FXCollections.observableArrayList(
                new RolePermission("1", "Operator", true, false, true, false, false),
                new RolePermission("2", "Supervisor", true, true, true, false, false),
                new RolePermission("3", "Admin", true, true, true, true, true)
        );

        roleTableView.setItems(roleList);

        roleTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedRole) -> {
            if (selectedRole != null) {
                viewCCTVCheckBox.setSelected(selectedRole.isViewCCTV());
                exportReportCheckBox.setSelected(selectedRole.isExportReport());
                createIncidentCheckBox.setSelected(selectedRole.isCreateIncident());
                manageUsersCheckBox.setSelected(selectedRole.isManageUsers());
                systemBackupCheckBox.setSelected(selectedRole.isSystemBackup());

                statusLabel.setVisible(true);
                statusLabel.setText("Role selected. You can now change permissions.");
                statusLabel.setStyle("-fx-text-fill: blue;");
            }
        });

        statusLabel.setVisible(true);
        statusLabel.setText("Select a role from the table.");
        statusLabel.setStyle("-fx-text-fill: green;");
    }

    @javafx.fxml.FXML
    public void clearButton(ActionEvent actionEvent) {
        roleTableView.getSelectionModel().clearSelection();

        viewCCTVCheckBox.setSelected(false);
        exportReportCheckBox.setSelected(false);
        createIncidentCheckBox.setSelected(false);
        manageUsersCheckBox.setSelected(false);
        systemBackupCheckBox.setSelected(false);

        statusLabel.setVisible(true);
        statusLabel.setText("Selection and permissions cleared.");
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
    public void saveRoleSettingsButton(ActionEvent actionEvent) {
        RolePermission selectedRole = roleTableView.getSelectionModel().getSelectedItem();

        if (selectedRole == null) {
            statusLabel.setVisible(true);
            statusLabel.setText("Please select a role first.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if (!viewCCTVCheckBox.isSelected() &&
                !exportReportCheckBox.isSelected() &&
                !createIncidentCheckBox.isSelected() &&
                !manageUsersCheckBox.isSelected() &&
                !systemBackupCheckBox.isSelected()) {

            statusLabel.setVisible(true);
            statusLabel.setText("At least one permission must be selected.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        selectedRole.setViewCCTV(viewCCTVCheckBox.isSelected());
        selectedRole.setExportReport(exportReportCheckBox.isSelected());
        selectedRole.setCreateIncident(createIncidentCheckBox.isSelected());
        selectedRole.setManageUsers(manageUsersCheckBox.isSelected());
        selectedRole.setSystemBackup(systemBackupCheckBox.isSelected());

        roleTableView.refresh();

        statusLabel.setVisible(true);
        statusLabel.setText("Permissions updated successfully.");
        statusLabel.setStyle("-fx-text-fill: green;");
    }
}