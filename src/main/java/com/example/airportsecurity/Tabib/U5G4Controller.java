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

public class U5G4Controller
{
    @javafx.fxml.FXML
    private TableView<Unit> unitTableView;
    @javafx.fxml.FXML
    private TextArea messageTextArea;
    @javafx.fxml.FXML
    private TableColumn<Unit, String> unitIdColumn;
    @javafx.fxml.FXML
    private TableColumn<Unit, String> unitNameColumn;
    @javafx.fxml.FXML
    private Label statusLabel;
    @javafx.fxml.FXML
    private Button sendButton;

    private ObservableList<Unit> unitList;

    @javafx.fxml.FXML
    public void initialize() {

        unitIdColumn.setCellValueFactory(new PropertyValueFactory<>("unitId"));
        unitNameColumn.setCellValueFactory(new PropertyValueFactory<>("unitName"));

        unitList = FXCollections.observableArrayList();

        unitList.add(new Unit("U001", "Patrol-1"));
        unitList.add(new Unit("U002", "Patrol-2"));
        unitList.add(new Unit("U003", "Gate Team"));
        unitList.add(new Unit("U004", "K9 Team"));

        unitTableView.setItems(unitList);

        sendButton.setDisable(true);
        statusLabel.setText("");

        unitTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null) {
                sendButton.setDisable(false);
            }
            else {
                sendButton.setDisable(true);
            }
        });
    }

    @javafx.fxml.FXML
    public void backButton(ActionEvent actionEvent)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/airportsecurity/Tabib/ControlRoomOperatorDashboard.fxml"));
        Stage stage = (Stage) ((javafx.scene.control.Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void sendButton(ActionEvent actionEvent) {

        Unit selectedUnit = unitTableView.getSelectionModel().getSelectedItem();
        String message = messageTextArea.getText();

        if(selectedUnit != null && !message.isEmpty()) {
            statusLabel.setText("Sent to " + selectedUnit.getUnitName());
            messageTextArea.clear();
        }
        else if(selectedUnit != null && message.isEmpty()) {
            statusLabel.setText("Write message first");
        }
    }
}