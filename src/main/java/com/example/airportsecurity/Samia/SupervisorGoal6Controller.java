package com.example.airportsecurity.Samia;

import com.example.airportsecurity.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.*;

public class SupervisorGoal6Controller {

    @FXML private TableView<EmergencyAlert> alertTable;
    @FXML private TableColumn<EmergencyAlert, String> timeCol;
    @FXML private TableColumn<EmergencyAlert, String> typeCol;
    @FXML private TableColumn<EmergencyAlert, String> locCol;
    @FXML private TableColumn<EmergencyAlert, String> sevCol;

    @FXML
    public void initialize() {
        timeCol.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("alertType"));
        locCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        sevCol.setCellValueFactory(new PropertyValueFactory<>("severity"));
        loadAlerts();
    }

    private void loadAlerts() {
        ObservableList<EmergencyAlert> list = FXCollections.observableArrayList();
        File f = new File("EmergencyAlerts.bin");
        if (!f.exists() || f.length() == 0) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            while (true) {
                try {
                    list.add((EmergencyAlert) ois.readObject());
                } catch (EOFException e) { break; }
            }
        } catch (Exception e) { e.printStackTrace(); }
        alertTable.setItems(list);
    }

    @FXML
    void goBackOnAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(new FXMLLoader(HelloApplication.class.getResource("Samia/ShiftSupervisor.fxml")).load()));
    }
}