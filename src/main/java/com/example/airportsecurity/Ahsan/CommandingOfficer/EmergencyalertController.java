package com.example.airportsecurity.Ahsan.CommandingOfficer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EmergencyalertController
{
    @FXML private ComboBox<String> typeBox;
    @FXML private TextField messageField;
    @FXML private Label statusLabel;

    @FXML private TableView<EmergencyAlert> alertTable;
    @FXML private TableColumn<EmergencyAlert, String> typeColumn;
    @FXML private TableColumn<EmergencyAlert, String> messageColumn;

    private final ObservableList<EmergencyAlert> alertList =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        typeBox.setItems(FXCollections.observableArrayList(
                "Fire",
                "Security"
        ));

        typeColumn.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getType()
                )
        );

        messageColumn.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getMessage()
                )
        );

        alertTable.setItems(alertList);
    }

    @FXML
    private void handleSend() {

        String type = typeBox.getValue();
        String msg = messageField.getText().trim();

        if (type == null || msg.isEmpty()) {
            statusLabel.setText("Please fill all fields");
            return;
        }

        EmergencyAlert alert = new EmergencyAlert(type, msg);

        alertList.add(alert);

        statusLabel.setText("Alert sent successfully");

        messageField.clear();
        typeBox.setValue(null);
    }
}