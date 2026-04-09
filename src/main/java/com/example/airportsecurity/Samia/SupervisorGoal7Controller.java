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

public class SupervisorGoal7Controller {

    @FXML private TableView<BaggageLog> baggageTable;
    @FXML private TableColumn<BaggageLog, String> ownerCol;
    @FXML private TableColumn<BaggageLog, String> flightCol;
    @FXML private TableColumn<BaggageLog, String> outcomeCol;

    @FXML
    public void initialize() {
        ownerCol.setCellValueFactory(new PropertyValueFactory<>("ownerName"));
        flightCol.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
        outcomeCol.setCellValueFactory(new PropertyValueFactory<>("outcome"));
        loadBaggageData();
    }

    private void loadBaggageData() {
        ObservableList<BaggageLog> list = FXCollections.observableArrayList();
        File f = new File("BaggageLogs.bin");
        if (!f.exists() || f.length() == 0) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            while (true) {
                try {
                    list.add((BaggageLog) ois.readObject());
                } catch (EOFException e) { break; }
            }
        } catch (Exception e) { e.printStackTrace(); }
        baggageTable.setItems(list);
    }

    @FXML
    void goBackOnAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(new FXMLLoader(HelloApplication.class.getResource("Samia/ShiftSupervisor.fxml")).load()));
    }
}