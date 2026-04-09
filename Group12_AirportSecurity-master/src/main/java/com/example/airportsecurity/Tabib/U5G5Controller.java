package com.example.airportsecurity.Tabib;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class U5G5Controller
{
    @javafx.fxml.FXML
    private TableView<Incident> incidentTableView;
    @javafx.fxml.FXML
    private TableColumn<Incident, String> incidentIdColumn;
    @javafx.fxml.FXML
    private TableColumn<Incident, String> zoneColumn;
    @javafx.fxml.FXML
    private TableColumn<Incident, String> priorityColumn;
    @javafx.fxml.FXML
    private TableColumn<Incident, String> statusColumn;
    @javafx.fxml.FXML
    private TextArea timelineTextArea;
    @javafx.fxml.FXML
    private TextArea updateTextArea;
    @javafx.fxml.FXML
    private Label messageLabel;
    @javafx.fxml.FXML
    private Button viewTimelineButton;
    @javafx.fxml.FXML
    private Button addUpdateButton;
    @javafx.fxml.FXML
    private Button saveUpdateButton;

    private ObservableList<Incident> incidentList;

    @javafx.fxml.FXML
    public void initialize() {

        incidentIdColumn.setCellValueFactory(new PropertyValueFactory<>("incidentId"));
        zoneColumn.setCellValueFactory(new PropertyValueFactory<>("zone"));
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        incidentList = FXCollections.observableArrayList();

        incidentList.add(new Incident("INC-101", "Terminal A", "High", "Created"));
        incidentList.add(new Incident("INC-102", "Gate 4", "Medium", "Investigating"));
        incidentList.add(new Incident("INC-103", "Cargo Zone", "Low", "Resolved"));
        incidentList.add(new Incident("INC-104", "Parking Area", "High", "Unit Dispatched"));

        incidentTableView.setItems(incidentList);

        viewTimelineButton.setDisable(true);
        addUpdateButton.setDisable(true);
        saveUpdateButton.setDisable(true);

        timelineTextArea.setEditable(false);
        updateTextArea.setEditable(false);

        messageLabel.setText("");

        incidentTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null) {
                viewTimelineButton.setDisable(false);
                addUpdateButton.setDisable(false);
            }
            else {
                viewTimelineButton.setDisable(true);
                addUpdateButton.setDisable(true);
                saveUpdateButton.setDisable(true);
            }
        });
    }

    @javafx.fxml.FXML
    public void viewTimelineButton(ActionEvent actionEvent) {
        Incident selectedIncident = incidentTableView.getSelectionModel().getSelectedItem();

        if(selectedIncident != null) {
            timelineTextArea.setText(
                    "Created -> Unit Dispatched -> Investigating -> Resolved"
            );
            messageLabel.setText("Timeline loaded for " + selectedIncident.getIncidentId());
        }
    }

    @javafx.fxml.FXML
    public void addUpdateButton(ActionEvent actionEvent) {
        Incident selectedIncident = incidentTableView.getSelectionModel().getSelectedItem();

        if(selectedIncident != null) {
            updateTextArea.setEditable(true);
            saveUpdateButton.setDisable(false);
            messageLabel.setText("Write update note for " + selectedIncident.getIncidentId());
        }
    }

    @javafx.fxml.FXML
    public void saveUpdateButton(ActionEvent actionEvent) {
        Incident selectedIncident = incidentTableView.getSelectionModel().getSelectedItem();
        String updateText = updateTextArea.getText();

        if(selectedIncident != null && !updateText.isEmpty()) {
            timelineTextArea.appendText("\nUpdate: " + updateText);
            messageLabel.setText("Update saved for " + selectedIncident.getIncidentId());
            updateTextArea.clear();
            updateTextArea.setEditable(false);
            saveUpdateButton.setDisable(true);
        }
        else if(selectedIncident != null && updateText.isEmpty()) {
            messageLabel.setText("Write update first");
        }
    }

    @javafx.fxml.FXML
    public void backButton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/airportsecurity/Tabib/ControlRoomOperatorDashboard.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}