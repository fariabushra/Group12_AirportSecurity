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

public class SupervisorGoal8Controller {

    @FXML private TableView<Handover> handoverTable;
    @FXML private TableColumn<Handover, String> timeCol;
    @FXML private TableColumn<Handover, String> officerCol;
    @FXML private TableColumn<Handover, String> notesCol;

    @FXML
    public void initialize() {
        timeCol.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        officerCol.setCellValueFactory(new PropertyValueFactory<>("officerName"));
        notesCol.setCellValueFactory(new PropertyValueFactory<>("keyNotes"));
        loadHandovers();
    }

    private void loadHandovers() {
        ObservableList<Handover> list = FXCollections.observableArrayList();
        File f = new File("HandoverReports.bin");
        if (!f.exists() || f.length() == 0) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            while (true) {
                try {
                    list.add((Handover) ois.readObject());
                } catch (EOFException e) { break; }
            }
        } catch (Exception e) { e.printStackTrace(); }
        handoverTable.setItems(list);
    }

    @FXML
    void goBackOnAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(new FXMLLoader(HelloApplication.class.getResource("Samia/ShiftSupervisor.fxml")).load()));
    }
}