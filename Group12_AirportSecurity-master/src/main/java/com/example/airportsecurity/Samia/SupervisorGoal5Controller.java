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

public class SupervisorGoal5Controller {

    @FXML private TableView<Visitor> visitorTable;
    @FXML private TableColumn<Visitor, String> timeCol;
    @FXML private TableColumn<Visitor, String> nameCol;
    @FXML private TableColumn<Visitor, String> purposeCol;

    @FXML
    public void initialize() {
        timeCol.setCellValueFactory(new PropertyValueFactory<>("entryTime"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        purposeCol.setCellValueFactory(new PropertyValueFactory<>("purpose"));
        loadVisitors();
    }

    private void loadVisitors() {
        ObservableList<Visitor> list = FXCollections.observableArrayList();
        File f = new File("VisitorLogs.bin");
        if (!f.exists() || f.length() == 0) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            while (true) {
                try {
                    list.add((Visitor) ois.readObject());
                } catch (EOFException e) { break; }
            }
        } catch (Exception e) { e.printStackTrace(); }
        visitorTable.setItems(list);
    }

    @FXML
    void goBackOnAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(new FXMLLoader(HelloApplication.class.getResource("Samia/ShiftSupervisor.fxml")).load()));
    }
}