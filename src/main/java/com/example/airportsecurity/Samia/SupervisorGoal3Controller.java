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

public class SupervisorGoal3Controller {

    @FXML private TableView<PatrolLog> patrolTable;
    @FXML private TableColumn<PatrolLog, String> zoneCol;
    @FXML private TableColumn<PatrolLog, String> statusCol;
    @FXML private TableColumn<PatrolLog, String> timeCol;

    @FXML
    public void initialize() {
        zoneCol.setCellValueFactory(new PropertyValueFactory<>("zone"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        loadLogs();
    }

    private void loadLogs() {
        ObservableList<PatrolLog> logs = FXCollections.observableArrayList();
        File f = new File("PatrolLogs.bin");
        if (!f.exists() || f.length() == 0) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            while (true) {
                try {
                    logs.add((PatrolLog) ois.readObject());
                } catch (EOFException e) { break; }
            }
        } catch (Exception e) { e.printStackTrace(); }
        patrolTable.setItems(logs);
    }

    @FXML
    void goBackOnAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(new FXMLLoader(HelloApplication.class.getResource("Samia/ShiftSupervisor.fxml")).load()));
    }
}