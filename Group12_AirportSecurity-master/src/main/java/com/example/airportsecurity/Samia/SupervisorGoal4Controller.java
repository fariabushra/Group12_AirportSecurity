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

public class SupervisorGoal4Controller {

    @FXML private TableView<Equipment> equipTable;
    @FXML private TableColumn<Equipment, String> nameCol;
    @FXML private TableColumn<Equipment, String> serialCol;
    @FXML private TableColumn<Equipment, String> condCol;

    @FXML
    public void initialize() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        serialCol.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        condCol.setCellValueFactory(new PropertyValueFactory<>("condition"));
        loadEquipmentData();
    }

    private void loadEquipmentData() {
        ObservableList<Equipment> list = FXCollections.observableArrayList();
        File f = new File("EquipmentStatus.bin");
        if (!f.exists() || f.length() == 0) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            while (true) {
                try {
                    list.add((Equipment) ois.readObject());
                } catch (EOFException e) { break; }
            }
        } catch (Exception e) { e.printStackTrace(); }
        equipTable.setItems(list);
    }

    @FXML
    void goBackOnAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(new FXMLLoader(HelloApplication.class.getResource("Samia/ShiftSupervisor.fxml")).load()));
    }
}